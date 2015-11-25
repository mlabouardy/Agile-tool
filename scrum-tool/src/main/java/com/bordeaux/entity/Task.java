package com.bordeaux.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bordeaux.entity.user.ScrumTeam;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.bordeaux.entity.user.User;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonProperty(value="title")
	private String Tag;
	
	private String Description;
	private String color;
	
	private int priority;

	private int difficulty;

	private boolean done;
	
	@JsonProperty(value="start")
	private Date beginning;
	
	@JsonProperty(value="end")
	private Date expectedEnd;
	
	private Date effectiveEnd;
	
	@JsonIgnore
	@ManyToOne
	private StatusTask status;
	
	@JsonIgnore
	@ManyToOne
	private User associatedDev;
	
	@JsonIgnore
	@ManyToOne
	private UserStory associatedUs;
	
	@ManyToOne
	@JoinColumn(name = "sprint_id")
	private Sprint sprint;


	@OneToOne
	private ScrumTeam scrumTeam;
	
	public ScrumTeam getScrumTeam() {
		return scrumTeam;
	}
	public void setScrumTeam(ScrumTeam scrumTeam) {
		this.scrumTeam = scrumTeam;
	}
	
	public Task() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public Date getBeginning() {
		return beginning;
	}
	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}
	public Date getExpectedEnd() {
		return expectedEnd;
	}
	public void setExpectedEnd(Date expectedEnd) {
		this.expectedEnd = expectedEnd;
	}
	public Date getEffectiveEnd() {
		return effectiveEnd;
	}
	public void setEffectiveEnd(Date effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}
	public StatusTask getStatus() {
		return status;
	}
	public void setStatus(StatusTask status) {
		this.status = status;
	}
	public User getAssociatedDev() {
		return associatedDev;
	}
	public void setAssociatedDev(User associatedDev) {
		this.associatedDev = associatedDev;
	}
	public UserStory getAssociatedUs() {
		return associatedUs;
	}
	public void setAssociatedUs(UserStory associatedUs) {
		this.associatedUs = associatedUs;
	}
	public Sprint getSprint() {
		return sprint;
	}
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	
	public String toString(){
		String res = "";
		res += this.Description + "\n";
		res += "Difficulty : " + this.difficulty + "\n";
		res += "Beginning : " + this.getBeginning().toString() + "\n";
		res += "ExpectedEnd : " + this.getExpectedEnd().toString() + "\n";
		res += "EffectiveEnd : " + this.getEffectiveEnd().toString() + "\n";
		return res;
		
	}
	
	public Object clone(){
		Task t = new Task();
		t.associatedDev = this.associatedDev;
		t.associatedUs = this.associatedUs;
		
		if(this.beginning != null)
			t.beginning = (Date) this.beginning.clone();
		else
			t.beginning = null;
		
		if(this.effectiveEnd != null)
			t.effectiveEnd = (Date) this.effectiveEnd.clone();
		else
			t.effectiveEnd = null;
		
		if(this.expectedEnd != null)
			t.expectedEnd = (Date) this.expectedEnd.clone();
		else
			t.expectedEnd = null;
		
		t.difficulty = this.difficulty;
		t.Description = this.Description;
		t.done = this.done;
		t.priority = this.priority;
		t.sprint = this.sprint;
		
		return t;
	}
	
}
