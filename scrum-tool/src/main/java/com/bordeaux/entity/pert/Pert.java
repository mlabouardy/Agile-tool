package com.bordeaux.entity.pert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bordeaux.entity.pert.Edge;
import com.bordeaux.entity.pert.GraphToDot;
import com.bordeaux.entity.pert.Node;
import com.bordeaux.entity.pert.Task;
import com.google.gson.Gson;

/*
 * classe permettant de générer le graphe de pert en utilisant le format dot
 * 
 * il ne faut pas utiliser les mots suivants "dotted" et "end" dans les noms des taches
 * je vais corriger ca dans les prochaines versions si j'ai le temps...
 * le code est pourri mais il fonctionne bien :)
 * 
 * Pert pert = new Pert();
 * 
 * pert.addTask("A", 30);
 * pert.addTask("B", 90);
 * pert.addTask("D", 10);
 * pert.addTask("C", 30);
 * pert.addTask("F", 30);
 * pert.addTask("G", 60);
 * pert.addTask("E", 10);
 * pert.addTask("H", 10);
 * 
 * pert.getTask("A");
 * pert.getTask("B").addDependency(pert.getTask("A"));
 * pert.getTask("D").addDependency(pert.getTask("A"));
 * pert.getTask("C").addDependency(pert.getTask("B"));
 * pert.getTask("F").addDependency(pert.getTask("B"));
 * pert.getTask("G").addDependency(pert.getTask("F"));
 * pert.getTask("E").addDependency(pert.getTask("D"));
 * pert.getTask("H").addDependency(pert.getTask("G"), pert.getTask("C"), pert.getTask("E"));
 * 
 * pert.closeGraph();
 * 
 * System.out.println(pert.getGraph());
 * 
 * */
public class Pert {

	private Map<String, Edge> edges = new HashMap<String, Edge>();
	private Map<String, Task> tasks = new HashMap<String, Task>();

	public void addTask(String name, float duration) {

		if (tasks.get(name) == null) {
			Task task = new Task(name, duration);
			tasks.put(name, task);
		}

	}

	public Task getTask(String name) {
		return tasks.get(name);
	}
	
	public void closeGraph(){
		
		ArrayList<Task> dependencies = new ArrayList<Task>();
		
		for (Entry<String, Task> task : tasks.entrySet()){
			for (Entry<String, Task> tmp : tasks.entrySet()){
				if (tmp.getValue().hasDependency(task.getValue())!=null){
					dependencies.add(task.getValue());
				}
			}
		}

		ArrayList<Task> dependencies2 = new ArrayList<Task>();
		
		for (Entry<String, Task> task : tasks.entrySet()){
			if (!dependencies.contains(task.getValue())){
				dependencies2.add(task.getValue());
			}
		}
		
		Task[] stockArr = new Task[dependencies2.size()];
	
		addTask("end", 0);
		getTask("end").addDependency(dependencies2.toArray(stockArr));
	}
	
	private Task getFinal() {
		int max = 0;
		Task finalTask = null;

		for (Entry<String, Task> entry : tasks.entrySet()) {
			int currentLenght = new Gson().toJson(entry.getValue()).length();
			if (max < currentLenght) {
				max = currentLenght;
				finalTask = entry.getValue();
			}
		}

		return finalTask;
	}

	private void replaceDependencies(Task replace, Task by) {
		for (Entry<String, Task> entry : tasks.entrySet()) {

			Task tmp = entry.getValue().hasDependency(replace);

			if (tmp != null) {
				
				if (!entry.getValue().getName().equals(by.getName())) {
					
					if (by.getName().contains(entry.getKey()+"  dotted")){
						entry.getValue().getDependencies().remove(tmp);
						entry.getValue().getDependencies().add(by);
					}
					
				}
			}

		}
	}

	private ArrayList<Task> getTaskListWithSameDepencency(Task task){
		ArrayList<Task> result = new ArrayList<Task>();
		
		for (Entry<String, Task> entry : tasks.entrySet()){
			if (entry.getValue().hasDependency(task)!=null){
				result.add(entry.getValue());
			}
		}
		
		return result;
	}
	
	
	private void addEdge(String taskName, Node left, Node right) {
		if (edges.get(taskName) == null) {
			edges.put(taskName, new Edge(left, right));
		}
	}

	private Edge getEdge(String taskName) {
		return edges.get(taskName);
	}
		
	private Map<String, Edge> calcAllEdge() {

		ArrayList<Task[]> result = getPathFirstToLast();
		ArrayList<String> previous = new ArrayList<String>();

		for (Task[] array : result) {

			for (int index = 0; index < array.length; index++) {

				if (!previous.contains(array[index].getName())) {

					int size = array[index].getDependencies().size();

					for (Task task : array[index].getDependencies()) {
						if (previous.contains(task.getName())) {
							size--;
						}
					}

					if (size == 0) {

						previous.add(array[index].getName());

						Node newNode = new Node(previous.size());

						if (index - 1 >= 0) {
							if (getEdge(array[index - 1].getName()) != null) {
								addEdge(array[index].getName(), getEdge(array[index - 1].getName()).getRight(),
										newNode);
							} else {
								addEdge(array[index].getName(), new Node(0), newNode);
							}
						} else {

							addEdge(array[index].getName(), new Node(0), newNode);
						}

					}
				}
			}

		}

		for (Task[] array : result) {

			for (int index = 0; index < array.length; index++) {

				if (index + 1 < array.length) {
					Edge edge1 = getEdge(array[index].getName());
					Edge edge2 = getEdge(array[index + 1].getName());
					edge1.setRight(edge2.getLeft());
				}

			}

		}

		Map<String, Edge> tmpList = new HashMap<String, Edge>();

		for (Entry<String, Edge> entry : edges.entrySet()) {

			for (Entry<String, Edge> tmp : edges.entrySet()) {
				if (entry.getKey() != tmp.getKey()) {
					if (entry.getValue().getLeft().getNum() == tmp.getValue().getLeft().getNum()
							&& entry.getValue().getRight().getNum() == tmp.getValue().getRight().getNum()) {

						tmpList.put(entry.getKey(), new Edge(tmp.getValue().getLeft(), tmp.getValue().getRight()));

					}
				}
			}
		}

		int z = previous.size();

		for (Entry<String, Edge> entry : tmpList.entrySet()) {
			z++;
			Node newNode = new Node(z);
			getEdge(entry.getKey()).setRight(newNode);

			for (Task child : getTaskListWithSameDepencency(getTask(entry.getKey()))) {
				String dotted = entry.getKey() + " " + child.getName() + " " + " dotted";
				addEdge(dotted, newNode, getEdge(child.getName()).getLeft());
				addTask(dotted, 0);
				getTask(dotted).addDependency(getTask(entry.getKey()));
				replaceDependencies(getTask(entry.getKey()), getTask(dotted));
			}

		}

		for (Task[] tasks : getPathFirstToLast()) {
			float time = 0;
			for (int i = 0; i < tasks.length; i++) {
				time += tasks[i].getDuration();
				getEdge(tasks[i].getName()).getRight().setTot(time);
				if (i == tasks.length - 1) {
					getEdge(tasks[i].getName()).getRight().initTard();
				}
			}
		}

		ArrayList<Task[]> list = getPathLastToFirst();

		for (Task[] tasks : list) {

			float time = getEdge(tasks[0].getName()).getRight().getTard();

			for (int i = 0; i < tasks.length; i++) {

				time -= tasks[i].getDuration();

				if (i + 1 < tasks.length) {
					getEdge(tasks[i + 1].getName()).getRight().setTard(time);
				}

			}

		}

		return edges;
	}

	private ArrayList<Task[]> getPathLastToFirst() {
		ArrayList<Task[]> result = getPathFirstToLast();

		for (Task[] tasks : result) {
			List<Task> list = Arrays.asList(tasks);
			Collections.reverse(list);
			tasks = (Task[]) list.toArray();
		}

		return result;
	}

	private ArrayList<Task[]> getPathFirstToLast() {

		Task finalTask = getFinal();
		fifo.clear();
		fifo.add(getTask("end"));
		result.clear();
		process(finalTask.getDependencies());

		return result;
	}

	private LinkedList<Task> fifo = new LinkedList<Task>();
	private ArrayList<Task[]> result = new ArrayList<Task[]>();

	private String process(List<Task> listTask) {

		StringBuilder sb = new StringBuilder();

		for (Task task : listTask) {

			fifo.push(task);

			sb.append(process(task.getDependencies()));

			if (task.getDependencies().size() == 0) {
				result.add(fifo.toArray(new Task[fifo.size()]));
			}

			fifo.pop();
		}

		return sb.toString();
	}

	public String getGraph() {

		GraphToDot graph = new GraphToDot();

		for (Entry<String, Edge> entry : calcAllEdge().entrySet()) {
			graph.addRelation(entry.getValue().getLeft().getNum() + "", entry.getValue().getRight().getNum() + "",
					getTask(entry.getKey()), entry.getValue());
		}

		return graph.getDot();
	}

}
