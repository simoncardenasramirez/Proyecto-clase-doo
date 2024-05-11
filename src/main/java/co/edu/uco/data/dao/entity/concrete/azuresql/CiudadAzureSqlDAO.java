package co.edu.uco.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscutting.ecxeptions.DataPCHException;
import co.edu.uco.data.dao.entity.CiudadDAO;
import co.edu.uco.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.entity.CiudadEntity;

public final class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO{

	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		
		sentenciaSql.append("INSERT INTO Ciudad (id, nombre, departamento) ");
		sentenciaSql.append("SELECT ?, ?, ?");
		
		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			
			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getDepartamento().getId());
			
			sentenciaSqlPreparada.executeUpdate();
			
		} catch (final SQLException exception) {
			var mensajeUsuario = "Se ha presentado un problema tratando de crear la ciudad \"${1}\".Por favor intente de nuevo y si el problema persiste contacte con el administrador...";
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";
			
			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);
			
		}catch (final Exception exception) {
			
			var mensajeUsuario = "Se ha presentado un problema tratando de crear la ciudad \"${1}\".Por favor intente de nuevo y si el problema persiste contacte con el administrador...";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepcion  de tipo SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL. Para mas detalles, revise de forma completa la excepcion raiz presentada..";
			
			throw new DataPCHException(mensajeTecnico, mensajeUsuario, exception);
			
		}
		
	}

	

	@Override
	public void modificar(CiudadEntity data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CiudadEntity> consultar(CiudadEntity data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
