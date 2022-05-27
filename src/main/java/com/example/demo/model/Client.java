package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Inheritance
public class Client {
	@Id
	@GeneratedValue
	private int idClient ;
	private String username;
	private String password;
	@OneToMany
	private List<Ticket> ticketsClt;

	
	
	public int getIdClient() {
		return idClient;
	}

	public List<Ticket> getTicketsClt() {
		return ticketsClt;
	}
	public void seTticketsClt(List<Ticket> tickets) {
		this.ticketsClt = tickets;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
