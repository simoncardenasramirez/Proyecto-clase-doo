package co.edu.uco.crosscutting.ecxeptions.messageCatalog.implement;

import java.util.HashMap;
import java.util.Map;

import co.edu.uco.crosscutting.ObjectHelper;
import co.edu.uco.crosscutting.ecxeptions.custom.CrosscuttingPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalog;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.Mensaje;
public final class MessageCatalogBase implements MessageCatalog {
	
	private final Map<String,Mensaje> mensajes = new HashMap<>();

	@Override
	public final void inicializar() {
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00001.getIdentificador(),
				new Mensaje(CodigoMensaje.M00001,"el codigo del mensaje que se quiere obtener del catalogo de mensajes llego nulo..."));
		mensajes.put(CodigoMensaje.M00002.getIdentificador(),
				new Mensaje(CodigoMensaje.M00002,"se a presentado un problema trantando de llevar acabo la operacion deseada..."));
		mensajes.put(CodigoMensaje.M00003.getIdentificador(),
				new Mensaje(CodigoMensaje.M00003,"el codigo de mensaje \"${1}\" que se intento obtener no esta en el catalogo de mensaje base..."));
		mensajes.put(CodigoMensaje.M00004.getIdentificador(),
				new Mensaje(CodigoMensaje.M00004,"el codigo de mensaje \"${1}\" que se intento obtener no esta configurado para recibir en el catalogo de mensajes base..."));
		mensajes.put(CodigoMensaje.M00005.getIdentificador(),
				new Mensaje(CodigoMensaje.M00005,"el codigo de mensaje \"${1}\" que se intento obtener no esta configurado para recibir en el catalogo de mensajes externo..."));
		mensajes.put(CodigoMensaje.M00006.getIdentificador(),
				new Mensaje(CodigoMensaje.M00006,"el codigo de mensaje \"${1}\" que se intento obtener no esta en el catalogo de mensaje externo..."));
	}

	@Override
	public final String obtenerContenidoMensaje(CodigoMensaje codigo,final  String... parametros) {
		// TODO Auto-generated method stub
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo,final  String... parametros) {
		if (ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00001);
			throw new CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		if (!codigo.isBase()) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00004,codigo.getIdentificador());
			throw new  CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		if (!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00003,codigo.getIdentificador());
			throw new  CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
			
		}
		
		// TODO: tarea: asegure que si tiene parametros, el contenido del mensaje se retorne  con los parametros reemplazados 
		return mensajes.get(codigo.getIdentificador());
	}



}