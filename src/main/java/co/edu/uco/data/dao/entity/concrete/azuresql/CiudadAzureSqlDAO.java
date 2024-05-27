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
import co.edu.uco.data.dao.entity.CiudadDAO;
import co.edu.uco.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.entity.CiudadEntity;
import co.edu.uco.entity.DepartamentoEntity;
import co.edu.uco.entity.PaisEntity;

public final class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO {

	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	public void crear(CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("INSERT INTO Ciudad (id, nombre, departamento) ");
		sentenciaSql.append("SELECT ?, ?, ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getDepartamento().getId());

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);

		} catch (final Exception exception) {

			var mensajeUsuario = "Se ha presentado un problema tratando de crear la ciudad \"${1}\".Por favor intente de nuevo y si el problema persiste contacte con el administrador...";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepcion  de tipo SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);

		}

	}

	public void modificar(CiudadEntity data) {

		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("UPDATE Ciudad SET nombre= ?,departamento= ? WHERE id= ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(3, data.getId());
			sentenciaSqlPreparada.setString(1, data.getNombre());
			sentenciaSqlPreparada.setObject(2, data.getDepartamento().getId());

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);

		} catch (final Exception exception) {

			var mensajeUsuario = "Se ha presentado un problema tratando de crear la ciudad \"${1}\".Por favor intente de nuevo y si el problema persiste contacte con el administrador...";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepcion  de tipo SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);

		}

	}

	public void eliminar(UUID id) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("DELETE FROM Ciudad WHERE id = ? ");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			sentenciaSqlPreparada.setObject(1, id);

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException exception) {
			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la ciudad \"${1}\".Por favor intente de nuevo y si el problema persiste contacte con el administrador...";
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQLException tratando de realizar el delete de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);

		} catch (final Exception exception) {

			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la ciudad \"${1}\".Por favor intente de nuevo y si el problema persiste contacte con el administrador...";
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQLException tratando de realizar el delete de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";

			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);

		}

	}

	public List<CiudadEntity> consultar(CiudadEntity data) {
		
		
		
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append("SELECT ciudad.id AS ciudad_id, ciudad.nombre AS nombre_ciudad, ");
		sentenciaSql.append("departamento.id AS departamento_id, departamento.nombre AS nombre_departamento, ");
		sentenciaSql.append("pais.id AS pais_id, pais.nombre AS nombre_pais ");
		sentenciaSql.append("FROM Ciudad ciudad ");
		sentenciaSql.append("INNER JOIN Departamento departamento ON ciudad.departamento = departamento.id ");
		sentenciaSql.append("INNER JOIN Pais pais ON departamento.pais = pais.id ");
		sentenciaSql.append("WHERE id=1");


	    final List<Object> parametros = new ArrayList<>();

	    if (!ObjectHelper.getObjectHelper().isNull(data.getId())) {
	        sentenciaSql.append(" AND id = ?");
	        parametros.add(data.getId());
	    }
	    if (!TextHelper.isNullOrEmpty(data.getNombre())) {
	        sentenciaSql.append(" AND nombre = ?");
	        parametros.add(data.getNombre());
	    }
	    if (!ObjectHelper.getObjectHelper().isNull(data.getDepartamento()) && !ObjectHelper.getObjectHelper().isNull(data.getDepartamento().getId())) {
	        sentenciaSql.append(" AND departamento = ?");
	        parametros.add(data.getDepartamento().getId());
	    }
	    final List<CiudadEntity> ciudades = new ArrayList<>();

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion()
	            .prepareStatement(sentenciaSql.toString())) {

	        for (int i = 0; i < parametros.size(); i++) {
	            sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
	        }

	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
	            while (resultado.next()) {
	            	CiudadEntity ciudad = new CiudadEntity();
                    ciudad.setId((UUID) resultado.getObject("id"));
                    ciudad.setNombre(resultado.getString("nombre_ciudad"));
                    DepartamentoEntity departamento = new DepartamentoEntity();
                    departamento.setNombre(resultado.getString("nombre_departamento"));
                    PaisEntity pais = new PaisEntity();
                    pais.setNombre(resultado.getString("nombre_pais"));
                    departamento.setPais(pais);
                    ciudad.setDepartamento(departamento);
                    ciudades.add(ciudad);
	            }
	        }
	        
	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de las ciudades en la tabla \"Ciudad\" de la base de datos Azure SQL.";

	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta de las ciudades en la tabla \"Ciudad\" de la base de datos Azure SQL.";

	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
	    }



		return ciudades;
		
		
	}
	
	
	
	
	
	

	
}
