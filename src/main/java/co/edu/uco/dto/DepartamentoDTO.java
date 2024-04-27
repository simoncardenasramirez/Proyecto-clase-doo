package co.edu.uco.dto;

import java.util.UUID;

import co.edu.uco.crosscutting.ObjectHelper;
import co.edu.uco.crosscutting.TextHelper;

public final class DepartamentoDTO {
	private UUID id;
	private String nombre;
	private PaisDTO pais;
	
	public DepartamentoDTO() {
		super();
	}
	
	public DepartamentoDTO(final UUID id, final String nombre, final PaisDTO pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
	}
	
	public static final CiudadDTO build() {
		return new CiudadDTO();
	}

	public UUID getId() {
		return id;
	}
	
	public DepartamentoDTO setId(final UUID id) {
		this.id = id;
		return this;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public DepartamentoDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	public PaisDTO getPais() {
		return pais;
	}
	
	public DepartamentoDTO setPais(final PaisDTO pais) {
		this.pais = pais;
		return this;
	}
	
	
	
}

