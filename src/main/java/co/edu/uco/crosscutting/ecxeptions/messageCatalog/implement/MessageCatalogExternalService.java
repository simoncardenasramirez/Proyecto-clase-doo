package co.edu.uco.crosscutting.ecxeptions.messageCatalog.implement;

import java.util.HashMap;
import java.util.Map;


import co.edu.uco.crosscutting.ecxeptions.custom.CrosscuttingPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalog;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.Mensaje;
import co.edu.uco.crosscutting.helpers.ObjectHelper;


public final class MessageCatalogExternalService implements MessageCatalog {
	
	private final Map<String,Mensaje> mensajes = new HashMap<>();

	@Override
	public final void inicializar() {
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00007.getIdentificador(),
				new Mensaje(CodigoMensaje.M00007,"la transaccion se a completado de forma satisfactoria..."));
		
	}

	@Override
	public final  String obtenerContenidoMensaje(final CodigoMensaje codigo,final String... parametros) {
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo,final String... parametros) {
		if (ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			throw new CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		if (codigo.isBase()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005,codigo.getIdentificador());
			throw new  CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		if (!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico =  MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006,codigo.getIdentificador());
			throw new  CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
			
		}
		
		// TODO: tarea: asegure que si tiene parametros, el contenido del mensaje se retorne  con los parametros reemplazados 
		
		return mensajes.get(codigo.getIdentificador());
	}
	
	

}