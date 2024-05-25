package co.edu.uco.dto;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public final class CiudadDTO {
	private UUID id;
	private String nombre;
	private DepartamentoDTO departamento;
	
	public CiudadDTO() {
		super();
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
		setDepartamento(DepartamentoDTO.build());
	}
	
	public CiudadDTO(final UUID id, final String nombre, final DepartamentoDTO departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}
	
	public static final CiudadDTO build() {
		return new CiudadDTO();
	}

	public UUID getId() {
		return id;
	}
	
	public CiudadDTO setId(final UUID id) {
		this.id = id;
		return this;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public CiudadDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	public DepartamentoDTO getDepartamento() {
		return departamento;
	}
	
	public CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
		this.departamento = departamento;
		return this;
	}
	
	
	
}
