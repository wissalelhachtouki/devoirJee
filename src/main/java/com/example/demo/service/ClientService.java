package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Ticket;

public interface ClientService {

	List<Ticket> getTickets(int idClient);

	void ajoutTicket(Ticket ticket, int idClient);
	
}
