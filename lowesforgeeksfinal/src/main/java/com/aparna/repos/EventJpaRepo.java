package com.aparna.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aparna.entities.Event;

public interface EventJpaRepo extends JpaRepository<Event, Integer>{
	
	@Query("from events e where (e.eventType='Org' or e.eventType='Team') and e.startDate >=:start and e.startDate<=:end and e.recurring='false' order by e.numberOfLikes desc,e.numberOfWatches desc,e.numberOfViews desc")
	public List<Event> showTrending(LocalDate start, LocalDate end);
	
	@Query("from events e where (e.eventType='Org' or e.eventType='Team') and e.startDate >=:start and e.startDate<=:end and e.recurring='true' order by (e.numberOfLikes+e.numberOfViews) desc")
	public List<Event> viewPopular(LocalDate start, LocalDate end);
	
	@Query("from events e where e.startDate >=:start and e.startDate<=:end and e.recurring='true' order by e.numberOfViews desc")
	public List<Event> viewUpcoming(LocalDate start, LocalDate end);

	public Event findEventByeventId(Integer eventId);
	
	

}
