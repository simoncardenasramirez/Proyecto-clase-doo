package co.edu.uco.crosscutting.ecxeptions;

import co.edu.uco.crosscutting.ecxeptions.enums.Lugar;

public final class DataPCHException extends PCHException{

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar=Lugar.DATA;

	public DataPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar );
	}
	
	public DataPCHException(final String mensajeTecnico, 
			final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}
	
	public DataPCHException(final String mensajeTecnico,final String mensajeUsuario,
			final Throwable exceptionRaiz) {
		super(mensajeTecnico,mensajeUsuario, lugar, exceptionRaiz);
	}
	
}
