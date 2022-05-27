package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Attribution;
import com.example.demo.repository.DeveloppeurRepository;
import com.example.demo.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	DeveloppeurRepository developpeurRepository;
	
	@GetMapping
	public String liste(Model m) {
		m.addAttribute("ticketsN", adminService.getTicketsNonAttribuer());
		return "admin/liste";
	}
	
	@GetMapping("/attribuer")
	public String ajout(Model m) {
		Attribution attribution = new Attribution();
		m.addAttribute("attribution",attribution);
		m.addAttribute("devs", developpeurRepository.findAll());
		m.addAttribute("tickets", adminService.getTicketsNonAttribuer());
		return "admin/attribuer";
	}

	@PostMapping("/attribuer")
	public String enregistrer(@ModelAttribute("attribution") Attribution attribution ) {
		adminService.attribuerTicket(attribution.getIdDev(), attribution.getIdTicket());
		return "redirect:/admin";
	}

}
