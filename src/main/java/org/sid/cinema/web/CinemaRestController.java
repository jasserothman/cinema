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

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@Transactional
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

		ticketForm.getTickets().forEach(idTicket->{
			Ticket ticket= ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setReservee(true);
			ticket.setCodePayement(ticketForm.getCodePayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
			
			
		});
		return listTickets ;
	}
	
	
	
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class TicketForm{
	private String nomClient;
	private int codePayement;
	private List<Long> tickets =new ArrayList<>();
	
}
