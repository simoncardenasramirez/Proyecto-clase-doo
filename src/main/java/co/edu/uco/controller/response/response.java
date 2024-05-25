package co.edu.uco.controller.response;

import java.util.ArrayList;
import java.util.List;

public class response<T> {
	
	private List<String> mensajes = new ArrayList<String>();
	private List<T> datos;
	
	
	public List<String> getMensajes() {
		return mensajes;
	}
	public final void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
	public final List<T> getDatos() {
		return datos;
	}
	public final void setDatos(List<T> datos) {
		this.datos = datos;
	}

}
