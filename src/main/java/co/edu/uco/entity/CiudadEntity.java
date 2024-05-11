package co.edu.uco.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.TextHelper;

public final class CiudadEntity {
	private UUID id;
	private String nombre;
	private DepartamentoEntity departamento;
	
	public CiudadEntity() {
		super();
	}
	
	public CiudadEntity(final UUID id, final String nombre, final DepartamentoEntity departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}
	
	public static final CiudadEntity build() {
		return new CiudadEntity();
	}

	public UUID getId() {
		return id;
	}
	
	public CiudadEntity setId(final UUID id) {
		this.id = id;
		return this;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public CiudadEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	public DepartamentoEntity getDepartamento() {
		return departamento;
	}
	
	public CiudadEntity setDepartamento(final DepartamentoEntity departamento) {
		this.departamento = departamento;
		return this;
	}
	
	
	
}
