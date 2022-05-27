package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Developpeur;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DeveloppeurRepository;
import com.example.demo.repository.TicketRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	DeveloppeurRepository developpeurRepository;
	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> getTicketsNonAttribuer() {
		List<Ticket> ticketsNonAttribuer = new ArrayList<Ticket>();
		for(Ticket t:ticketRepository.findAll()) {
			if(t.getStatut() == -1)
				ticketsNonAttribuer.add(t);
		}
		return ticketsNonAttribuer;
	}

	@Override
	public void attribuerTicket(int idDev, int idTicket) {
		Ticket ticket = ticketRepository.getById(idTicket);
		Developpeur dev = developpeurRepository.getById(idDev);
		dev.getTicketsDev().add(ticket);
		developpeurRepository.save(dev);
		ticket.setStatut(0);
		ticketRepository.save(ticket);
	}

}
