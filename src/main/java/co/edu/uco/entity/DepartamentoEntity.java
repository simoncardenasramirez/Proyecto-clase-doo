package co.edu.uco.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.ObjectHelper;
import co.edu.uco.crosscutting.TextHelper;

public final class DepartamentoEntity {
	private UUID id;
	private String nombre;
	private PaisEntity pais;
	
	public DepartamentoEntity() {
		super();
	}
	
	public DepartamentoEntity(final UUID id, final String nombre, final PaisEntity pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
	}
	
	public static final CiudadEntity build() {
		return new CiudadEntity();
	}

	public UUID getId() {
		return id;
	}
	
	public DepartamentoEntity setId(final UUID id) {
		this.id = id;
		return this;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public DepartamentoEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	public PaisEntity getPais() {
		return pais;
	}
	
	public DepartamentoEntity setPais(final PaisEntity pais) {
		this.pais = pais;
		return this;
	}
	
	
	
}

