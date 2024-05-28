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
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.data.dao.entity.DepartamentoDAO;
import co.edu.uco.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.entity.DepartamentoEntity;
import co.edu.uco.entity.PaisEntity;

public class DepartamentoAzureSqlDAO extends SqlConnection implements DepartamentoDAO {

	public DepartamentoAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<DepartamentoEntity> consultar(DepartamentoEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append("SELECT id, nombre, pais FROM Departamento WHERE 1=1");

		if (!ObjectHelper.getObjectHelper().isNull(data.getId())) {
			sentenciaSql.append(" AND id = ?");
		}
		if (!TextHelper.isNullOrEmpty(data.getNombre())) {
			sentenciaSql.append(" AND nombre = ?");
		}
		if (data.getPais() != null && !ObjectHelper.getObjectHelper().isNull(data.getPais().getId())) {
			sentenciaSql.append(" AND pais = ?");
		}

		final List<DepartamentoEntity> departamentos = new ArrayList<>();

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			int index = 1;

			if (!ObjectHelper.getObjectHelper().isNull(data.getId())) {
				sentenciaSqlPreparada.setObject(index++, data.getId());
			}
			if (!TextHelper.isNullOrEmpty(data.getNombre())) {
				sentenciaSqlPreparada.setString(index++, data.getNombre());
			}
			if (data.getPais() != null && !ObjectHelper.getObjectHelper().isNull(data.getPais().getId())) {
				sentenciaSqlPreparada.setObject(index++, data.getPais().getId());
			}

			try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
				while (resultado.next()) {
					DepartamentoEntity departamento = new DepartamentoEntity();
					departamento.setId((UUID) resultado.getObject("id"));
					departamento.setNombre(resultado.getString("nombre"));
					// Asignar el país si se está seleccionando en la consulta
					PaisEntity pais = new PaisEntity();
					pais.setId((UUID) resultado.getObject("pais"));
					departamento.setPais(pais);
					departamentos.add(departamento);
				}
			}

		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return departamentos;
	}

}
