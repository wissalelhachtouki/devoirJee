package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Ticket;
import com.example.demo.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping("/{idClient}")
	public String liste(Model m, @PathVariable int idClient) {
		String url = idClient + "/newTicket";
		m.addAttribute("url", url);
		m.addAttribute("tickets", clientService.getTickets(idClient));
		return "client/liste";
	}

	@GetMapping("/{idClient}/newTicket")
	public String ajout(Model m, @PathVariable int idClient) {
		Ticket ticket = new Ticket();
		String url = idClient + "/newTicket";
		m.addAttribute("url", url);
		m.addAttribute("ticket", ticket);
		return "client/newTicket";
	}

	@PostMapping("/{idClient}/newTicket")
	public String enregistrer(@ModelAttribute("ticket") Ticket ticket, @PathVariable int idClient) {
		ticket.setStatut(-1);
		clientService.ajoutTicket(ticket, idClient);
		return "redirect:/client/" + idClient;
	}

}
