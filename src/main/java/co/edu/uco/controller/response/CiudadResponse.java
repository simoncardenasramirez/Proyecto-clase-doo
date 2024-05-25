package co.edu.uco.controller.response;



import java.util.ArrayList;

import co.edu.uco.dto.CiudadDTO;

public class CiudadResponse extends response<CiudadDTO>{
	
	public CiudadResponse() {
		setMensajes(new ArrayList<String>());
		setDatos(new ArrayList<>());
	}

}
