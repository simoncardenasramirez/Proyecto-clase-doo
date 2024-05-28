package co.edu.uco.business.facade.imp.ciudad;

import java.util.List;

import co.edu.uco.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.business.facade.FacadeWithReturn;
import co.edu.uco.business.usecase.impl.ciudad.ConsultarCiudades;
import co.edu.uco.crosscutting.ecxeptions.PCHException;
import co.edu.uco.crosscutting.ecxeptions.custom.BusinessPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.data.dao.factory.DAOFactory;
import co.edu.uco.dto.CiudadDTO;

public final class ConsultarCiudadesFacade implements FacadeWithReturn<CiudadDTO, List<CiudadDTO>>{
	
	

	private DAOFactory daoFactory;
	
	
	public ConsultarCiudadesFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	

	 @Override
	    public final List<CiudadDTO> execute(final CiudadDTO dto) {
	        daoFactory.iniciarTransaccion();
	        try{

	            var useCase = new ConsultarCiudades(daoFactory);
	            var ciudadDomain = CiudadAssemblerDTO.getInstance().toDomain(dto);
	            var resultadosDomain = useCase.execute(ciudadDomain);
	            
	            return CiudadAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
	            
	        }catch (final PCHException exception){
	            
	            throw exception;
	        }catch (final Exception exception){
	            
	            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje( CodigoMensaje.M00024);
	            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);

	            throw new BusinessPCHException(mensajeTecnico, mensajeUsuario, exception);
	            
	        }finally {
	            daoFactory.cerrarConexion();
	        }
	    }

	
	
}
