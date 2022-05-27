package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Client;
import com.example.demo.model.Ticket;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.TicketRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> getTickets(int idClient) {
		Client client = clientRepository.getById(idClient);
		return client.getTicketsClt();
	}

	@Override
	public void ajoutTicket(Ticket ticket, int idClient) {
		ticketRepository.save(ticket);
		Client client = clientRepository.getById(idClient);
		client.getTicketsClt().add(ticket);
		clientRepository.save(client);
	}

}
