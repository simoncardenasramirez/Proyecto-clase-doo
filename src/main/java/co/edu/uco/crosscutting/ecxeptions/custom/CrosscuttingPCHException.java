package co.edu.uco.crosscutting.ecxeptions.custom;

import co.edu.uco.crosscutting.ecxeptions.PCHException;
import co.edu.uco.crosscutting.ecxeptions.enums.Lugar;

public final class CrosscuttingPCHException extends PCHException {

    private static final long serialVersionUID = 1L;
    private static final Lugar lugar = Lugar.CROSSCUTTING;

    public CrosscuttingPCHException(final String mensajeUsuario) {
        super(mensajeUsuario, lugar);
    }

    public CrosscuttingPCHException(final String mensajeTecnico,final String mensajeUsuario) {
		super(mensajeTecnico,mensajeUsuario, lugar);

	}
	
	public CrosscuttingPCHException(final String mensajeTecnico,final String mensajeUsuario,final  Lugar lugar,Throwable excepcionRaiz) {
		super(mensajeTecnico,mensajeUsuario, lugar,excepcionRaiz);

	}
    
    
}