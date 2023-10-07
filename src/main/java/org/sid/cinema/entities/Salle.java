package org.sid.cinema.entities;



import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@JsonProperty(access = Access.WRITE_ONLY)
	private Cinema cinema ;
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Place> places;
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Projection> projection;
	
}
