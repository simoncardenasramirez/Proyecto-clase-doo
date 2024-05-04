package co.edu.uco.crosscutting.ecxeptions.messageCatalog.implement;

import java.util.HashMap;
import java.util.Map;

import co.edu.uco.crosscutting.ObjectHelper;
import co.edu.uco.crosscutting.ecxeptions.custom.CrosscuttingPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalog;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.Mensaje;

public final class MessageCatalogBase implements MessageCatalog {

    private final Map<String, Mensaje> mensajes = new HashMap<>();

    @Override
    public final void inicializar() {
        mensajes.clear();
        mensajes.put(CodigoMensaje.M00001.getIdentificador(), new Mensaje(CodigoMensaje.M00001,
                "El codigo del mensaje que se quiere obtener del catalogo de mensajes llegó nulo"));

        mensajes.put(CodigoMensaje.M00002.getIdentificador(), new Mensaje(CodigoMensaje.M00002,
                "Se ha presentado un problema tratando de llevar a cabo una operacion deseada"));

        mensajes.put(CodigoMensaje.M00003.getIdentificador(), new Mensaje(CodigoMensaje.M00003,
                "El identificador del mensaje \"${1}\" que se intento obtener no esta en el catalogo de mensajes"));

        mensajes.put(CodigoMensaje.M00004.getIdentificador(), new Mensaje(CodigoMensaje.M00004,
                "El mensaje con el identificador \"${1}\" que se intento obtener, no esta configurado para recibir en el catalogo de mensajes base"));
    }

    @Override
    public final String obtenerContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
        return obtenerMensaje(codigo, parametros). getContenido();
    }
// Se ha registro el pais ... String permite ser dinamico, por ejemplo: Se
// ha registro del el pais colombia {1} de forma exitosa
    @Override
    public final Mensaje obtenerMensaje(final CodigoMensaje codigo, final String... parametros) {

        if (ObjectHelper.getObjectHelper().isNull(codigo)){
            var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00001);
            throw new CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
        }
        if (!codigo.isBase()){
            var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00004, codigo.getIdentificador());
            throw new CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
        }
        if (!mensajes.containsKey(codigo.getIdentificador())){
            var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00003, codigo.getIdentificador());
            throw new CrosscuttingPCHException(mensajeTecnico, mensajeUsuario);
        }

        //Tarea: asegure que si tiene parametros el contenido del mensaje, se retorne con los parametros reeemplazados
        // {1},{2},{3}

        //Lo hace TextHelper, tiene una libreria String format
        return mensajes.get(codigo.getIdentificador());
    }


}