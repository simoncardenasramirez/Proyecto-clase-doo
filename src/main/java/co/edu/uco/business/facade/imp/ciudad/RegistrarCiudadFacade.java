package co.edu.uco.business.facade.imp.ciudad;

import co.edu.uco.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.business.facade.FacadeWithOutReturn;
import co.edu.uco.business.usecase.impl.ciudad.RegistrarCiudad;
import co.edu.uco.crosscutting.ecxeptions.PCHException;
import co.edu.uco.crosscutting.ecxeptions.custom.BusinessPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.data.dao.factory.DAOFactory;
import co.edu.uco.dto.CiudadDTO;

public final class RegistrarCiudadFacade implements FacadeWithOutReturn<CiudadDTO>{
	
	private DAOFactory daoFactory;
	
	
	public RegistrarCiudadFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public void execute(final CiudadDTO dto) {
		daoFactory.iniciarTransaccion();
		try {
			var useCase = new RegistrarCiudad(daoFactory);
			var ciudadDomain = CiudadAssemblerDTO.getInstance().toDomain(dto);
			
			useCase.execute(ciudadDomain);
			
			//ejecutar caso de uso
			daoFactory.confirmarTransaccion();
		} catch (final PCHException excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		}catch (final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario=MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00026);
			var mensajeTecnico=MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);
			throw new BusinessPCHException(mensajeTecnico,mensajeUsuario, excepcion);
		}finally {
			daoFactory.cerrarConexion();
		}
		
	}
	
	

}
