package com.youcode.reservationApp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;



@Entity
public class Users implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name")
	@NotNull(message = "Veuillez entrer un prenom")
	@Size(min = 1, message = "Veuillez entrer un prenom")
	private String firstName;
	@Column(name = "last_name")
	@NotNull(message = "Veuillez entrer un nom")
	@Size(min = 1, message = "Veuillez entrer un nom")
	private String lastName;
	@NotNull(message = "Veuillez entrer un Email")
	@Pattern(regexp = ".*(@student.youcode.ma$)",flags =Flag.CASE_INSENSITIVE , message = "veuillez entrer l'email de youcode")
	private String email;
	@NotNull(message = "Veuillez entrer un Mot de passe")
	@Size(min = 8, message = "Le mot de passe doit etre au minimum de 8 caracteres")
	private String password;
	private String role;
	private String state;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<Reservation> reservations;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private UserReputation userReputation;

	public Users() {
	}

	public Users(String firstName, String lastName, String email, String password, String role, String state) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.state = state;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addReservation(Reservation reservation) {
		if (reservations == null) {
			reservations = new ArrayList<Reservation>();
		}
		
		reservations.add(reservation);
		
		reservation.setUser(this);
	}

	public UserReputation getUserReputation() {
		return userReputation;
	}

	public void setUserReputation(UserReputation userReputation) {
		this.userReputation = userReputation;
		this.userReputation.setUser(this);
	}
	
	

}
