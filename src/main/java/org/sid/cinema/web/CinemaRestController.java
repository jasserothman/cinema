package org.sid.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class CinemaRestController {

	@Autowired
	FilmRepository filmRepository;
	@Autowired
	TicketRepository ticketRepository;

	@GetMapping(path="/imageFilm/{id}",produces =MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id") Long id) throws Exception{
		Film f=filmRepository.findById(id).get();
		String photoName= f.getPhoto();
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
		Path path= Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	@PostMapping("/payerTickets")
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
		List<Ticket> listTickets=new ArrayList<>();
		ticketForm.getTicket().forEach(idTicket->{
			Ticket ticket= ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNameClient());
			ticket.setReservee(true);
			ticket.setCodePayement(ticketForm.getCodePayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
			
			
		});
		return listTickets;
	}
	
	
	
}
@Data
class TicketForm{
	private String nameClient;
	private int codePayement;
	private List<Long> ticket =new ArrayList<>();
	
}
