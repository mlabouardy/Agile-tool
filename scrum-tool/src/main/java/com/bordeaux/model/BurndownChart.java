package com.bordeaux.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.Synthesizer;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.Task;

public class BurndownChart {

	private class Couple{
		private int remainingCost;
		private Date date;
		
		public Couple(){
			remainingCost = 0;
		}
		
		public Couple(int remainingCost, Date date){
			this.remainingCost = remainingCost;
			this.date = date;
		}

		public int getRemainingCost() {
			return remainingCost;
		}

		public void setRemainingCost(int remainingCost) {
			this.remainingCost = remainingCost;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		
		
	}
	private int totalCost = 0;
	private Collection<Couple> expected = null;
	private Collection<Couple> effective = null;
	
	public BurndownChart(Sprint s){
		
		if(s!=null){
			expected = new ArrayList<Couple>();
			effective = new ArrayList<Couple>();
			List<Task> tasks = (List<Task>) s.getTasks();
			Task[] orderedExpected = new Task[tasks.size()];
			Task[] orderedEffective = new Task[tasks.size()];
			Task task_tmp = null;
			int nbTasksCurrent = 0;
			for(Task task : tasks){
				int i = 0;
				int k = 0;
				nbTasksCurrent++;
				totalCost += task.getDifficulty();
				if(task.getEffectiveEnd() != null){
					while(i < tasks.size() && orderedEffective[i] != null && orderedEffective[i].getEffectiveEnd().before(task.getEffectiveEnd())){
						i++;
						
					}
					
					int iTmp = nbTasksCurrent-1;
					while(iTmp > i){
						orderedEffective[iTmp] = orderedEffective[iTmp-1];
						iTmp--;
					}
					orderedEffective[i]=task;

				}
				
				if(task.getExpectedEnd()!=null){
					while(k < tasks.size() && orderedExpected[k] != null && orderedExpected[k].getExpectedEnd().before(task.getExpectedEnd())){
						k++;
					}
					
					int iTmp = nbTasksCurrent-1;
					while(iTmp > k){
						orderedExpected[iTmp] = orderedExpected[iTmp-1];
						iTmp--;
					}
					orderedExpected[k]=task;
						
					
				}
				
			}
				
			int effectiveRemainingCost = totalCost;
			int expectedRemainingCost = totalCost;
			
			Couple couple_tmp = null;
			int i = 0; 
			while(i < orderedEffective.length && orderedEffective[i] != null){
				effectiveRemainingCost -= orderedEffective[i].getDifficulty();
				System.out.println(orderedEffective[i].toString());
				if(couple_tmp != null && orderedEffective[i].getEffectiveEnd().compareTo(couple_tmp.getDate()) == 0){
					
					couple_tmp.setRemainingCost(effectiveRemainingCost);
				}
				
				else{
					couple_tmp = new Couple(effectiveRemainingCost , (Date) orderedEffective[i].getEffectiveEnd().clone());
					effective.add(couple_tmp);
				}
				
				 i++;
			}
			
			i=0;
			couple_tmp = null;
			while( i < orderedExpected.length && orderedExpected[i] != null ){
				expectedRemainingCost -= orderedExpected[i].getDifficulty();
				if(couple_tmp != null && orderedExpected[i].getExpectedEnd().compareTo(couple_tmp.getDate()) == 0){
					couple_tmp.setRemainingCost(expectedRemainingCost);
					
				}
				else{
					couple_tmp = new Couple(expectedRemainingCost , (Date) orderedExpected[i].getExpectedEnd().clone());
					expected.add(couple_tmp);
				}
				
				i++;
			}
			
		}
	
		
	}
	
	public int getExpectedCostRemainingAt(Date d){
		
		int res = totalCost;
		
		Iterator<Couple> ite = expected.iterator();
		Couple couple_tmp;
		while(ite.hasNext() && (couple_tmp = ite.next()).getDate().before(d))
			res = couple_tmp.getRemainingCost();
		return res;
		
	}
	
	public int getEffectiveCostRemainingAt(Date d){
		
		int res = totalCost;
		
		Iterator<Couple> ite = effective.iterator();
		Couple couple_tmp;
		while(ite.hasNext() && (couple_tmp = ite.next()).getDate().before(d))
			res = couple_tmp.getRemainingCost();		
		return res;
		
	}
	
	public Collection<Integer> getEffectiveCostsFor(Date beginning , Date end, int intervalle){
		Couple couple_tmp = null;
		int cost = totalCost;
		Iterator<Couple> ite = effective.iterator();
		Collection<Integer> lres = new ArrayList<Integer>();
		Date d = (Date) beginning.clone();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		while(cal.getTime().before(end) && ite.hasNext() || cal.getTime().compareTo(end) == 0  ){
		
			couple_tmp = ite.next();
			System.out.println(couple_tmp.getDate().toString());
			while(cal.getTime().before(couple_tmp.getDate()) && cal.getTime().before(end)){
				System.out.println("Current Date : " + cal.getTime().toString());
				lres.add(cost);
				cal.add(Calendar.DAY_OF_MONTH, intervalle);
			}
			if(cal.getTime().before(end)){
				cost = couple_tmp.getRemainingCost();
				lres.add(cost);
				cal.add(Calendar.DAY_OF_MONTH, intervalle);
			}			
		
		}
		
		return lres;
	}
	
	public Collection<Integer> getExpectedCostsFor(Date beginning , Date end, int intervalle){
		Couple couple_tmp = null;
		int cost = totalCost;
		Iterator<Couple> ite = expected.iterator();
		Collection<Integer> lres = new ArrayList<Integer>();
		Date d = (Date) beginning.clone();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		while(cal.getTime().before(end) || cal.getTime().compareTo(end) == 0 ){
			if(ite.hasNext()){
				couple_tmp = ite.next();
				while(cal.getTime().before(couple_tmp.getDate()) && cal.getTime().before(end)){
					lres.add(cost);
					cal.add(Calendar.DAY_OF_MONTH, intervalle);
				}
				if(cal.getTime().before(end)){
					cost = couple_tmp.getRemainingCost();
					lres.add(cost);
					cal.add(Calendar.DAY_OF_MONTH, intervalle);
				}			
			}
			else{
				lres.add(cost);
				cal.add(Calendar.DAY_OF_MONTH, intervalle);
				
			}	
		}
		
		return lres;
	}
	
	public Collection<String> getDateStr(Date beginning , Date end ,int intervalle){
		Collection<String> res = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) beginning.clone());
		DateFormat formatDate = new SimpleDateFormat("EEEE, d MMM yyyy");
		while(cal.getTime().before(end)){
			res.add(formatDate.format( cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, intervalle);
		}
		res.add(formatDate.format(cal.getTime()));
		return res;
	}
	
}
