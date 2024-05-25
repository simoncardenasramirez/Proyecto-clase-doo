package co.edu.uco.dto;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;


public final class PaisDTO {
	private UUID id;
	private String nombre;
	
	public PaisDTO() {
		super();
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
	}
	
	public PaisDTO(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}
	
	public static final PaisDTO build() {
		return new PaisDTO();
	}
	
	public UUID getId() {
		return id;
	}
	
	public PaisDTO setId(final UUID id) {
		this.id = id;
		return this;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public PaisDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
}
