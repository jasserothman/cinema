package org.sid.cinema.entities;



import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Salle {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int nombrePlaces;
	@ManyToOne
	private Cinema cinema ;
	@OneToMany(mappedBy = "salle")
	private Collection<Place> places;
	@OneToMany(mappedBy = "salle")
	private Collection<Projection> projection;
	
}
