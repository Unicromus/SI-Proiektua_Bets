package domain;

import java.io.Serializable;
import java.util.Date;

import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Event implements Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer eventNumber;
	private String description; 
	private Date eventDate;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Vector<Question> questions=new Vector<Question>();


	public Event() {
		super();
	}

	public Event(Integer eventNumber, String description,Date eventDate) {
		this.eventNumber = eventNumber;
		this.description = description;
		this.eventDate=eventDate;
	}

	public Event( String description,Date eventDate) {
		this.description = description;
		this.eventDate=eventDate;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}
	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Vector<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}


	public String toString(){
		return eventNumber+";"+description;
	}

	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Question addQuestion(String question, float betMinimum)  {
		Question q=new Question(question,betMinimum, this);
		questions.add(q);
		return q;
	}
	
	public boolean removeQuestion(Question qu) {
		if (questions.contains(qu)) {
			questions.remove(qu);
			return true;
		}
		return false;
	}

	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean DoesQuestionExists(String question)  {	
		for (Question q:this.getQuestions()){
			if (q.getQuestion().compareTo(question)==0)
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + eventNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventNumber != other.eventNumber)
			return false;
		return true;
	}

	public Vector<Erabiltzailea> WhatPeopleParticipate(Question question){
		Vector<Erabiltzailea> erabs = new Vector<Erabiltzailea>();
		for(Result re: question.results) {
			erabs.addAll(re.WhatPeopleParticipate());
		}
		return erabs;
	
	}
	
}
