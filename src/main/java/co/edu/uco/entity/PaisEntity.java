package co.edu.uco.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.TextHelper;


public final class PaisEntity {
	private UUID id;
	private String nombre;
	
	public PaisEntity() {
		super();
	}
	
	public PaisEntity(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}
	
	public static final PaisEntity build() {
		return new PaisEntity();
	}
	
	public UUID getId() {
		return id;
	}
	
	public PaisEntity setId(final UUID id) {
		this.id = id;
		return this;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public PaisEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
}
