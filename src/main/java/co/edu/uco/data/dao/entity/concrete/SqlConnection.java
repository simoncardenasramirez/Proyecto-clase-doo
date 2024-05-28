package co.edu.uco.data.dao.entity.concrete;

import java.sql.Connection;

import co.edu.uco.crosscutting.ecxeptions.DataPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.helpers.SQLHelper;

public class SqlConnection {

	private Connection conexion;

	protected SqlConnection(final Connection conexion) {
		setConexion(conexion);
	}

	protected SqlConnection() {
		super();
	}

	protected final Connection getConexion() {
		return conexion;
	}

	protected final void setConexion(final Connection conexion) {

		if (!SQLHelper.isOpen(conexion)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var MensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);

			throw new DataPCHException(mensajeUsuario, MensajeTecnico);
		}

		this.conexion = conexion;
	}

}