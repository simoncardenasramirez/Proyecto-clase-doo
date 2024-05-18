package co.edu.uco.data.dao.entity.concrete;

import java.sql.Connection;

import co.edu.uco.crosscutting.ecxeptions.DataPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.helpers.SQLHelper;

public  class SqlConnection {
	
	protected  Connection conexion;
	
	protected SqlConnection() {
		super();
	}
	
	protected SqlConnection(final Connection conexion) {
		setConexion(conexion);
	}

	protected final Connection getConexion() {
		return conexion;
	}
	
	protected final void setConexion(final Connection conexion) {
		
		if(!SQLHelper.isOpen(conexion)) {
			var mensajeUsaurio = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "No es posible crear el DAO deseado con la coneccion cerrada";
			
			throw new DataPCHException(mensajeTecnico, mensajeUsaurio);
		}
		
		this.conexion=conexion;
	}
	
	
}
