package co.edu.uco.crosscutting.ecxeptions.messageCatalog;

import co.edu.uco.crosscutting.ObjectHelper;
import co.edu.uco.crosscutting.ecxeptions.custom.CrosscuttingPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.Mensaje;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.implement.MessageCatalogBase;

public class MessageCatalogStrategy {
	
	private static final MessageCatalog base =new MessageCatalogBase();
	private static final MessageCatalog externalService = new MessageCatalogBase();
	
	
	private MessageCatalogStrategy() {
		super();
	}
	
	
	public static void inicializar() {
		base.inicializar();
		externalService.inicializar();
	}
	
	private static final MessageCatalog getStrategy(final boolean isBase) {
		return isBase ? base : externalService;
	}
	
	public static final Mensaje getMensaje(final CodigoMensaje codigo, final String...parametros) {
		
		if(ObjectHelper.getObjectHelper().isNull(codigo)) {
			throw new CrosscuttingPCHException(null,null,null);
		}
		
		return getStrategy(codigo.isBase())
				.obtenerMensaje(codigo, parametros);
	}

}
