/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insession.rest1.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insession.rest1.rest.model.AnimalNoDB;
import com.insession.rest1.rest.model.AnimalNoDB.AnimalType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author tobiaszimmermann
 */
@Path("animals")
public class AnimalDemo {

	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of AnimalDemo
	 */
	public AnimalDemo() {
	}

	/**
	 * Retrieves representation of an instance of
	 * com.insession.rest1.rest.AnimalDemo
	 *
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Vuf... (Message from a dog)";
	}

	@GET
	@Path("/animal_list")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnimals() {
		return "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";
	}

	@GET
	@Path("/sound")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnimalSounds() {
		AnimalNoDB animalNoDB = new AnimalNoDB(AnimalType.Dog, "Vuf");
		return gson.toJson(animalNoDB);
	}

	/**
	 * PUT method for updating or creating an instance of AnimalDemo
	 *
	 * @param content representation for the resource
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void putJson(String content) {
	}
}
