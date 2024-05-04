package co.edu.uco.crosscutting.ecxeptions.messageCatalog;

import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.Mensaje;

public interface MessageCatalog {
	
	void inicializar();
	
	String obtenerContenidoMensaje(final CodigoMensaje codigo, String...parametros);
	
	Mensaje obtenerMensaje(final CodigoMensaje codigo, String...parametros);
}
