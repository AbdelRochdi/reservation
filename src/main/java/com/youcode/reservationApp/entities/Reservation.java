package com.youcode.reservationApp.entities;
// default package
// Generated Mar 12, 2021, 10:08:26 AM by Hibernate Tools 5.4.27.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long reservationId;
	private long userId;
	private Date date;
	private String state;

	public Reservation() {
	}

	public Reservation(long userId, Date date, String state) {
		this.userId = userId;
		this.date = date;
		this.state = state;
	}

	public Long getReservationId() {
		return this.reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
