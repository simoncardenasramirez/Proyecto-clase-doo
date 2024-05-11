package co.edu.uco.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.crosscutting.SQLHelper;
import co.edu.uco.data.dao.entity.CiudadDAO;
import co.edu.uco.data.dao.entity.DepartamentoDAO;
import co.edu.uco.data.dao.entity.PaisDAO;
import co.edu.uco.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.data.dao.entity.concrete.azuresql.CiudadAzureSqlDAO;
import co.edu.uco.data.dao.entity.concrete.azuresql.DepartamentoAzureSqlDAO;
import co.edu.uco.data.dao.entity.concrete.azuresql.PaisAzureSqlDAO;
import co.edu.uco.data.dao.factory.DAOFactory;

public final class AzureSQLDAOFActory extends SqlConnection implements DAOFactory{
	
	

	public AzureSQLDAOFActory() {
		super();
		abrirConexion();
	}

	@Override
	public void abrirConexion() {
		try {
			String connecString = "hsdhyrtssadtyrhg";
			setConexion(DriverManager.getConnection(connecString));
		} catch (final SQLException excepcion) {
			
		} catch (final Exception exception) {
			
		}
		
	}

	@Override
	public void cerrarConexion() {
		SQLHelper.close(getConexion());
		
	}

	@Override
	public void iniciarTransaccion() {
		SQLHelper.initTransaction(getConexion());
		
	}

	@Override
	public void confirmarTransaccion() {
		SQLHelper.commit(getConexion());
		
	}

	@Override
	public void cancelarTransaccion() {
		SQLHelper.rollback(getConexion());
		
	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisAzureSqlDAO(getConexion());
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoAzureSqlDAO(getConexion());
	}

	@Override
	public CiudadDAO getCiudadDAO() {
		return new CiudadAzureSqlDAO(getConexion());
	}

}