package co.edu.uco.business.usecase.impl.ciudad;

import java.util.List;

import co.edu.uco.business.assembler.entity.impl.CiudadAssemblerEntity;
import co.edu.uco.business.domain.CiudadDomain;
import co.edu.uco.business.usecase.UseCaseWithReturn;
import co.edu.uco.crosscutting.ecxeptions.custom.BusinessPCHException;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.data.dao.factory.DAOFactory;

public class ConsultarCiudades  implements UseCaseWithReturn<CiudadDomain, List<CiudadDomain>>{
	
private DAOFactory factory;
	
	public ConsultarCiudades(final DAOFactory factory) {
		if (ObjectHelper.getObjectHelper().isNull(factory)) {
			var mensajeUsuario= "se ha presentado un problema tratando de llevar a cabo el registro de la consulta de las ciudad";
			var mensajeTecnico = "el DAOfactoty para creear consultar las  ciudad llego nulo...";
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}

	@Override
	public List<CiudadDomain> execute(CiudadDomain data) {
		var ciudadEntityFilter =
				CiudadAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getCiudadDAO().consultar(ciudadEntityFilter);
		
		var resultadosDomain =  CiudadAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
		
		
		return resultadosDomain;
	}

}
