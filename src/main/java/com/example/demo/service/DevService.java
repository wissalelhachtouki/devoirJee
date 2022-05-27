package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Ticket;

public interface DevService  {
	List<Ticket> getTickets(int idDev);

	void editStatus(Ticket ticket, int idDev);


}
