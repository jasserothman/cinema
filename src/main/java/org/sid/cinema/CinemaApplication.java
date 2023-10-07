package org.sid.cinema;

import org.sid.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication  implements CommandLineRunner{

	@Autowired
	private ICinemaInitService iCinemaInitService ;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		iCinemaInitService.initVilles();
		iCinemaInitService.initCinema();
		iCinemaInitService.initSalles();
		iCinemaInitService.initPlaces();
		iCinemaInitService.initSeances();
		iCinemaInitService.initCategories();
		iCinemaInitService.initFilms();
		iCinemaInitService.initProjections();
		iCinemaInitService.initTickets();
		
		
	}

}
