package co.edu.uco.business.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class CiudadDomain {
	private UUID id;
	private String nombre;
	private DepartamentoDomain departamento;
	
	
	private CiudadDomain(final UUID id, final String nombre, final DepartamentoDomain departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
		
	}

	public static CiudadDomain build(final UUID id, final String nombre, final DepartamentoDomain departamento) {
		return new CiudadDomain(id, nombre, departamento);
	}
	
	public static CiudadDomain build(final UUID id) {
		return new CiudadDomain(id, TextHelper.EMPTY, DepartamentoDomain.build());
	}
	
	
	public static CiudadDomain build() {
		return new CiudadDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, DepartamentoDomain.build());
	}



	public final String getNombre() {
		return nombre;
	}

	public final DepartamentoDomain getDepartamento() {
		return departamento;
	}

	private final void setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}

	private final void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);;
	}

	private final void setDepartamento(DepartamentoDomain departamento) {
		this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, DepartamentoDomain.build());
	}
	
	
	public final UUID getId() {
		return id;
	}
	
	
	

}