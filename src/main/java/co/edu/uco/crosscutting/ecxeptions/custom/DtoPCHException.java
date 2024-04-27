package co.edu.uco.crosscutting.ecxeptions.custom;

import co.edu.uco.crosscutting.ecxeptions.PCHException;
import co.edu.uco.crosscutting.ecxeptions.enums.Lugar;

public final class DtoPCHException extends PCHException {

	private static final long serialVersionUID = -1179319726524825522L;

	public DtoPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.DATA);

	}
	
	public DtoPCHException(final String mensajeTecnico,final String mensajeUsuario,final  Lugar lugar,Throwable excepcionRaiz) {
		super(mensajeTecnico,mensajeUsuario, Lugar.DATA,excepcionRaiz);

	}
	

}