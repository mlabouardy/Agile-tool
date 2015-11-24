package com.bordeaux.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bordeaux.entity.user.User;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String Tag;
	private String Description;
	private String color;
	
	private int priority;

	private int difficulty;

	private boolean done;
	
	private Date beginning;
	
	private Date expectedEnd;
	
	private Date effectiveEnd;
	
	@ManyToOne
	private StatusTask status;
	
	@ManyToOne
	private User associatedDev;
	
	@ManyToOne
	private UserStory associatedUs;
	
	@ManyToOne
	@JoinColumn(name = "sprint_id")
	private Sprint sprint;

	
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
	
}
