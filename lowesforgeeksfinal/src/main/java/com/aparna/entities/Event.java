package com.aparna.entities;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer eventId;
	EventType eventType;
	String name;
	String description;
	Member createdBy;
	String location;
	LocalDate startDate;
	LocalDate endDate;
	Integer numberOfLikes;
	Integer numberOfWatches;
	Integer numberOfViews;
	boolean recurring;
	RecurringFrequency recurringFrequency;
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Member getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Member createdBy) {
		this.createdBy = createdBy;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getNumberOfLikes() {
		return numberOfLikes;
	}
	public void setNumberOfLikes(Integer numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}
	public Integer getNumberOfWatches() {
		return numberOfWatches;
	}
	public void setNumberOfWatches(Integer numberOfWatches) {
		this.numberOfWatches = numberOfWatches;
	}
	public Integer getNumberOfViews() {
		return numberOfViews;
	}
	public void setNumberOfViews(Integer numberOfViews) {
		this.numberOfViews = numberOfViews;
	}
	public boolean isRecurring() {
		return recurring;
	}
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}
	public RecurringFrequency getRecurringFrequency() {
		return recurringFrequency;
	}
	public void setRecurringFrequency(RecurringFrequency recurringFrequency) {
		this.recurringFrequency = recurringFrequency;
	}
	public Event(Integer eventId, EventType eventType, String name, String description, Member createdBy,
			String location, String startDate, String endDate, Integer numberOfLikes, Integer numberOfWatches,
			Integer numberOfViews, boolean recurring, RecurringFrequency recurringFrequency) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.name = name;
		this.description = description;
		this.createdBy = createdBy;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfLikes = numberOfLikes;
		this.numberOfWatches = numberOfWatches;
		this.numberOfViews = numberOfViews;
		this.recurring = recurring;
		this.recurringFrequency = recurringFrequency;
	}
	
	public Event()
	{
		
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [eventId=");
		builder.append(eventId);
		builder.append(", eventType=");
		builder.append(eventType);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", location=");
		builder.append(location);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", numberOfLikes=");
		builder.append(numberOfLikes);
		builder.append(", numberOfWatches=");
		builder.append(numberOfWatches);
		builder.append(", numberOfViews=");
		builder.append(numberOfViews);
		builder.append(", recurring=");
		builder.append(recurring);
		builder.append(", recurringFrequency=");
		builder.append(recurringFrequency);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
