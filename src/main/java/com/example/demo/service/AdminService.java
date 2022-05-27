package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Ticket;

public interface AdminService {

	List<Ticket> getTicketsNonAttribuer();

	void attribuerTicket(int idDev, int idTicket);
}
