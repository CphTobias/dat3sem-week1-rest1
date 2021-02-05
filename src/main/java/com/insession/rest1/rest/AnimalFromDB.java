package com.insession.rest1.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insession.rest1.entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author tobiaszimmermann
 */
@Path("animals_db")
public class AnimalFromDB {

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of AnimalFromDB
	 */
	public AnimalFromDB() {
	}

	@Path("/animals")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnimals() {
		EntityManager em = emf.createEntityManager();
		List<Animal> animals;
		try {
			animals = em.createQuery("SELECT a from Animal a").getResultList();
		} finally {
			em.close();
		}
		return Response.ok(gson.toJson(animals)).build();
	}

	@GET
	@Path("/animalbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnimalById(
		@PathParam("id") int id
	) {
		EntityManager em = emf.createEntityManager();
		Animal animal;
		try {
			animal = em.find(Animal.class, id);
		} finally {
			em.close();
		}

		if (animal != null) {
			return Response.ok(gson.toJson(animal)).build();
		} else {
			return null;
		}
	}

	@GET
	@Path("/animalbytype/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnimalByType(
		@PathParam("type") String type
	) {
		EntityManager em = emf.createEntityManager();
		List<Animal> animals;
		try {
			animals = em.createQuery("SELECT a from Animal a WHERE a.type = :type", Animal.class)
				.setParameter("type", type)
				.getResultList();
		} finally {
			em.close();
		}

		if (animals != null) {
			return Response.ok(gson.toJson(animals)).build();
		} else {
			return null;
		}
	}

	@GET
	@Path("/random_animal")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findRandomAnimal() {
		EntityManager em = emf.createEntityManager();
		Animal animal;
		try {
			List<Animal> animals = em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
			Random random = new Random();
			int randomNumber = random.nextInt(animals.toArray().length);

			animal = em.createQuery("SELECT a FROM Animal a WHERE a.id = :id", Animal.class).setParameter("id", randomNumber + 1)
				.getSingleResult();
		} finally {
			em.close();
		}
		if (animal != null) {
			return Response.ok(gson.toJson(animal)).build();
		} else {
			return null;
		}
	}
}
