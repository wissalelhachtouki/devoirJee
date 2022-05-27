package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Developpeur {
	@Id
	@GeneratedValue
	private int idDev;
	private String username;
	private String password;
	@OneToMany
	private List<Ticket> ticketsDev;

	public int getIdDev() {
		return idDev;
	}

	public List<Ticket> getTicketsDev() {
		return ticketsDev;
	}

	public void setTicketsDev(List<Ticket> ticketsDev) {
		this.ticketsDev = ticketsDev;
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
