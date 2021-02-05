package com.insession.rest1.rest.model;

public class AnimalNoDB {

	public enum AnimalType {
		Dog, Cat, Duck
	}

	private AnimalType type;
	private String sound;

	public AnimalNoDB(AnimalType type, String sound) {
		this.type = type;
		this.sound = sound;
	}

	public AnimalType getType() {
		return type;
	}

	public void setType(AnimalType type) {
		this.type = type;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}
}
