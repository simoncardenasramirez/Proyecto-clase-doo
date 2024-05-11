package co.edu.uco.data.dao.factory;

import co.edu.uco.data.dao.entity.CiudadDAO;
import co.edu.uco.data.dao.entity.DepartamentoDAO;
import co.edu.uco.data.dao.entity.PaisDAO;
import co.edu.uco.data.dao.factory.concrete.AzureSQLDAOFActory;

public interface DAOFactory {
	
	default DAOFactory getFactory() {
		return new AzureSQLDAOFActory();
	}
	
	void abrirConexion();
	void cerrarConexion();
	void iniciarTransaccion();
	void confirmarTransaccion();
	void cancelarTransaccion();
	
	
	PaisDAO getPaisDAO();
	
	DepartamentoDAO getDepartamentoDAO();
	
	CiudadDAO getCiudadDAO();
	

}