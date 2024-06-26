package co.edu.uco.crosscutting.ecxeptions.custom;

import co.edu.uco.crosscutting.ecxeptions.PCHException;
import co.edu.uco.crosscutting.ecxeptions.enums.Lugar;

public final class BusinessPCHException extends PCHException {

    private static final long serialVersionUID = 1L;
    private static final Lugar lugar = Lugar.BUSINESS;

    public BusinessPCHException(final String mensajeUsuario) {
        super(mensajeUsuario, lugar);
    }
    public BusinessPCHException(final String mensajeTecnico, final String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, lugar);
    }

    public BusinessPCHException(final String mensajeTecnico, final String mensajeUsuario, final Throwable excepcionRaiz) {
        super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
    }
}