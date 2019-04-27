package com.aparna.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aparna.entities.Event;
import com.aparna.entities.EventType;
import com.aparna.repos.EventJpaRepo;

@RestController
@RequestMapping("/lowesforgeeks")
public class EventRestController {

	@Autowired
	EventJpaRepo eventJpaRepo;

	@RequestMapping(path="/viewTrending",method=RequestMethod.GET)
	public List<Event> showTrending()
	{
		LocalDate start = convertCalendarToLocalDate(-2,"MONTH");
		LocalDate end = convertCalendarToLocalDate(1,"WEEK");
		return eventJpaRepo.showTrending(start, end);
	}
	
	@RequestMapping(path="/viewPopular", method=RequestMethod.GET)
	public List<Event> viewPopular()
	{
		LocalDate start = convertCalendarToLocalDate(-1, "MONTH");
		LocalDate end = convertCalendarToLocalDate(2, "DAY");
		return eventJpaRepo.viewPopular(start, end);
	}
	
	@RequestMapping(path="/viewUpcoming", method=RequestMethod.GET)
	public List<Event> viewUpcoming()
	{
		LocalDate start = convertCalendarToLocalDate(-1, "WEEK");
		LocalDate end = convertCalendarToLocalDate(1, "DAY");
		return eventJpaRepo.viewUpcoming(start, end);
	}
	
	
		
	public LocalDate convertCalendarToLocalDate(int duration, String type )
	{
		Calendar calendar = Calendar.getInstance();
		if(type.equalsIgnoreCase("MONTH"))
			calendar.add(Calendar.MONTH, duration);
		else if(type.equalsIgnoreCase("WEEK"))
			calendar.add(Calendar.WEEK_OF_MONTH, duration);
		else if(type.equalsIgnoreCase("DAY"))
			calendar.add(Calendar.DAY_OF_MONTH, duration);
		
		
		TimeZone tz = calendar.getTimeZone();
		ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		return LocalDateTime.ofInstant(calendar.toInstant(), zid).toLocalDate();
	}
	
	
	
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	@RequestMapping(path="/newEvent", method=RequestMethod.POST)
	public ResponseEntity putEvent(@RequestBody Event event){
		ResponseEntity responseEntity=null;
		if(event.getEventType().equals(EventType.Org)) {
			Date startDate=null;
			try {
				startDate = dateFormat.parse(event.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date currentDate= new Date();
			try {
				currentDate=dateFormat.parse(currentDate.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int m1=startDate.getYear()*12 + startDate.getMonth();
			int m2=currentDate.getYear() *12 + currentDate.getMonth();
			if(m1-m2>=2) {
				eventJpaRepo.save(event);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		if(event.getEventType().equals(EventType.Team)) {
			Date startDate=null;
			try {
				startDate = dateFormat.parse(event.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date currentDate= new Date();
			try {
				currentDate=dateFormat.parse(currentDate.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int m1=startDate.getYear()*52 + startDate.getMonth()*4 + (startDate.getDay()/7);
			int m2=currentDate.getYear() *52 + currentDate.getMonth()*4 + (currentDate.getDay()/7);
			if(m1-m2>1) {
				eventJpaRepo.save(event);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		if(event.getEventType().equals(EventType.Private)) {
			Date startDate=null;
			try {
				startDate = dateFormat.parse(event.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date currentDate= new Date();
			try {
				currentDate=dateFormat.parse(currentDate.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int m1=startDate.getYear()*364 + startDate.getMonth()*30 + startDate.getDay();
			int m2=currentDate.getYear() *364 + currentDate.getMonth()*30 + currentDate.getDay();
			if(m1-m2>2) {
				eventJpaRepo.save(event);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.CONFLICT);
	
	}
	
	@Transactional
	@RequestMapping(path="/viewEvent",method=RequestMethod.GET)
	public Event viewEvent(@RequestParam Integer eventId) {
		Event event=eventJpaRepo.findEventByeventId(eventId);
		event.setNumberOfViews(event.getNumberOfViews()+1);
		return event;
	}
	
	@Transactional
	@RequestMapping(path="/updateEvent",method=RequestMethod.POST)
	public ResponseEntity updateEvent(@RequestParam Event event) {
		ResponseEntity responseEntity=null;
		Event event1=eventJpaRepo.findEventByeventId(event.getEventId());
		Date startDate=null;
		try {
			startDate = dateFormat.parse(event.getStartDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date=new Date();
		if(date.before(startDate)) {
			
			
			
			if(event.getEventType().equals(EventType.Org)) {
				
				try {
					startDate = dateFormat.parse(event1.getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date newDate=null;
				try {
					 newDate=dateFormat.parse(event.getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int m1=startDate.getYear()*12 + startDate.getMonth();
				int m2=newDate.getYear() *12 + newDate.getMonth();
				if(m1-m2>=2) {
					
					eventJpaRepo.delete(event1);
					eventJpaRepo.save(event);
					return new ResponseEntity(HttpStatus.OK);
				}
			}
			if(event.getEventType().equals(EventType.Team)) {
				
				try {
					startDate = dateFormat.parse(event1.getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date newDate=null;
				try {
					newDate=dateFormat.parse(event.getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int m1=startDate.getYear()*52 + startDate.getMonth()*4 + (startDate.getDay()/7);
				int m2=newDate.getYear() *52 + newDate.getMonth()*4 + (newDate.getDay()/7);
				if(m1-m2>1) {
					eventJpaRepo.delete(event1);
					eventJpaRepo.save(event);
					return new ResponseEntity(HttpStatus.OK);
				}
			}
			if(event.getEventType().equals(EventType.Private)) {
				
				try {
					startDate = dateFormat.parse(event.getStartDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date newDate= null;
				try {
					newDate=dateFormat.parse(newDate.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int m1=startDate.getYear()*364 + startDate.getMonth()*30 + startDate.getDay();
				int m2=newDate.getYear() *364 + newDate.getMonth()*30 + newDate.getDay();
				if(m1-m2>2) {
					eventJpaRepo.delete(event1);
					eventJpaRepo.save(event);
					return new ResponseEntity(HttpStatus.OK);
				}
			}
//			return new ResponseEntity(HttpStatus.CONFLICT);
		
		}
		return new ResponseEntity(HttpStatus.CONFLICT);
	}
	
	
	
	
	
	@RequestMapping(path="/newRemove", method=RequestMethod.POST)
	public ResponseEntity removeEvent(@RequestBody Integer eventId){
		ResponseEntity responseEntity=null;
		Event event=eventJpaRepo.findEventByeventId(eventId);
		if(event.getEventType().equals(EventType.Org)) {
			Date startDate=null;
			try {
				startDate = dateFormat.parse(event.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date currentDate= new Date();
			try {
				currentDate=dateFormat.parse(currentDate.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int m1=startDate.getYear()*52 + startDate.getMonth()*4 + (startDate.getDay()/7);
			int m2=currentDate.getYear() *52 + currentDate.getMonth()*4 + (currentDate.getDay()/7);
			if(m1-m2>=2) {
				eventJpaRepo.delete(event);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		if(event.getEventType().equals(EventType.Team)) {
			Date startDate=null;
			try {
				startDate = dateFormat.parse(event.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date currentDate= new Date();
			try {
				currentDate=dateFormat.parse(currentDate.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int m1=startDate.getYear()*52 + startDate.getMonth()*4 + (startDate.getDay()/7);
			int m2=currentDate.getYear() *52 + currentDate.getMonth()*4 + (currentDate.getDay()/7);
			if(m1-m2>1) {
				eventJpaRepo.delete(event);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		if(event.getEventType().equals(EventType.Private)) {
			Date startDate=null;
			try {
				startDate = dateFormat.parse(event.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date currentDate= new Date();
			try {
				currentDate=dateFormat.parse(currentDate.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int m1=startDate.getYear()*364 + startDate.getMonth()*30 + startDate.getDay();
			int m2=currentDate.getYear() *364 + currentDate.getMonth()*30 + currentDate.getDay();
			if(m1-m2>2) {
				eventJpaRepo.delete(event);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.CONFLICT);
	
	}
}

	
