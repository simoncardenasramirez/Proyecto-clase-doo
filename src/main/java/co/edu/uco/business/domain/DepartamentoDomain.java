package co.edu.uco.business.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;



public class DepartamentoDomain {
	
	
	private UUID id;
	private String nombre;
	private PaisDomain pais;
	
	private DepartamentoDomain(final UUID id, final String nombre, final PaisDomain pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
		
	}
	
	public static DepartamentoDomain build(final UUID id, final String nombre, final PaisDomain pais) {
		return new DepartamentoDomain(id, nombre, pais);
	}
	
	public static DepartamentoDomain build(final UUID id) {
		return new DepartamentoDomain(id, TextHelper.EMPTY, PaisDomain.build());
	}
	
	
	public static DepartamentoDomain build() {
		return new DepartamentoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, PaisDomain.build());
	}
	
	private final void setId(UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}
	private final void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);;
	}
	private final void setPais(PaisDomain pais) {
		this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, PaisDomain.build());
	}
	public final UUID getId() {
		return id;
	}
	public final String getNombre() {
		return nombre;
	}
	public final PaisDomain getPais() {
		return pais;
	}
	
	

}