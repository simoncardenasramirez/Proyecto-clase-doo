package co.edu.uco.crosscutting.ecxeptions.custom;

import co.edu.uco.crosscutting.ecxeptions.PCHException;
import co.edu.uco.crosscutting.ecxeptions.enums.Lugar;

public final class CrosscuttingPCHException extends PCHException {

	private static final long serialVersionUID = -1179319726524825522L;

	public CrosscuttingPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.DATA);

	}
	
	public CrosscuttingPCHException(final String mensajeTecnico,final String mensajeUsuario,final  Lugar lugar,Throwable excepcionRaiz) {
		super(mensajeTecnico,mensajeUsuario, Lugar.DATA,excepcionRaiz);

	}
	

}