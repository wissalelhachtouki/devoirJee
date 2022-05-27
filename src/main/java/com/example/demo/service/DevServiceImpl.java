package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Developpeur;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DeveloppeurRepository;
import com.example.demo.repository.TicketRepository;

@Service
public class DevServiceImpl implements DevService {

	@Autowired
	DeveloppeurRepository developpeurRepository;
	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> getTickets(int idDev) {
		Developpeur dev = developpeurRepository.getById(idDev);
		return dev.getTicketsDev();
	}

	@Override
	public void editStatus(Ticket ticket, int idTicket) {
		Ticket t = ticketRepository.getById(idTicket);
		t.setStatut(ticket.getStatut());
		ticketRepository.save(t);
	}

}
