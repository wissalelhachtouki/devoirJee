package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DevService;

@Controller
@RequestMapping("dev")
public class DevController {

	@Autowired
	DevService devService;

	@Autowired
	TicketRepository ticketRepository;

	@GetMapping("/{idDev}")
	public String liste(Model m, @PathVariable int idDev) {
		String url = idDev + "/updateTicket/";
		m.addAttribute("url", url);
		m.addAttribute("tickets", devService.getTickets(idDev));
		return "dev/liste";
	}

	@GetMapping("/{idDev}/updateTicket/{idTicket}")
	public String updateStatus(Model m, @PathVariable int idDev, @PathVariable int idTicket) {
		Ticket ticket = ticketRepository.getById(idTicket);
		String url = idDev + "/updateTicket/" + idTicket;
		m.addAttribute("url", url);
		m.addAttribute("ticket", ticket);
		return "dev/updateTicket";
	}

	@PostMapping("/{idDev}/updateTicket/{idTicket}")
	public String enregistrer(@ModelAttribute("ticket") Ticket ticket, @PathVariable int idTicket,
			@PathVariable int idDev) {
		devService.editStatus(ticket, idTicket);
		return "redirect:/dev/" + idDev;
	}

}
