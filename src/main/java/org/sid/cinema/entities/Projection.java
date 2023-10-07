package org.sid.cinema.entities;

import java.util.Collection;
import java.util.Date;

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
@Data @AllArgsConstructor @NoArgsConstructor @ToString 
public class Projection {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Date dateProjection;
private double prix;

@ManyToOne
private Salle salle;

@ManyToOne
private Film film;

@OneToMany(mappedBy = "projection")
private Collection<Ticket> tickets;
@ManyToOne
private Seance seance;


}
