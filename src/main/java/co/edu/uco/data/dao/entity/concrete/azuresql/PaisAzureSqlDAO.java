package co.edu.uco.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscutting.ecxeptions.DataPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.data.dao.entity.PaisDAO;
import co.edu.uco.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.entity.PaisEntity;

public class PaisAzureSqlDAO extends SqlConnection implements PaisDAO {

	public PaisAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<PaisEntity> consultar(PaisEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append("SELECT id, nombre FROM Pais WHERE 1=1");

		if (data != null) {
			if (data.getId() != null) {
				sentenciaSql.append(" AND id = ?");
			}
			if (!TextHelper.isNullOrEmpty(data.getNombre())) {
				sentenciaSql.append(" AND nombre = ?");
			}
		}

		final List<PaisEntity> paises = new ArrayList<>();

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			int index = 1;

			if (data != null) {
				if (data.getId() != null) {
					sentenciaSqlPreparada.setObject(index++, data.getId());
				}
				if (!TextHelper.isNullOrEmpty(data.getNombre())) {
					sentenciaSqlPreparada.setString(index++, data.getNombre());
				}
			}

			try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
				while (resultado.next()) {
					PaisEntity pais = new PaisEntity();
					pais.setId((UUID) resultado.getObject("id"));
					pais.setNombre(resultado.getString("nombre"));
					paises.add(pais);
				}
			}

		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return paises;
	}

}
