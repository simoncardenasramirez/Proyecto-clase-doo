package co.edu.uco.business.usecase.impl.ciudad;

import java.util.UUID;

import org.springframework.boot.autoconfigure.kafka.SslBundleSslEngineFactory;

import co.edu.uco.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.business.domain.CiudadDomain;
import co.edu.uco.business.usecase.UseCaseWithOutReturn;
import co.edu.uco.crosscutting.ecxeptions.custom.BusinessPCHException;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.data.dao.factory.DAOFactory;
import co.edu.uco.entity.CiudadEntity;
import co.edu.uco.entity.DepartamentoEntity;

public final class RegistrarCiudad  implements UseCaseWithOutReturn<CiudadDomain>{
	
private DAOFactory factory;
	
	public RegistrarCiudad(final DAOFactory factory) {
		if (ObjectHelper.getObjectHelper().isNull(factory)) {
			var mensajeUsuario= "se ha presentado un problema tratando de llevar a cabo el registro de la ciudad";
			var mensajeTecnico = "el DAOfactoty para creear la ciudad llego nulo...";
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}
	
	
	@Override
	public void execute(final CiudadDomain data) {
		// validar que los datos requwridos por el caso de uso sean correctos
		//a nivel de tipo de dato, longitud,obligatoriedad,formato,rango
		
		
		// validar que no exista otra ciudad con el mismo nombre y departamento
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		
		// validar que no exista otra ciudad con el mismo identificador
		var ciudadEntity = CiudadEntity.build().setId(generarIdentificadorCiudad()).setNombre(data.getNombre())
															.setDepartamento(DepartamentoAssemblerEntity.getInstance().toEntity(data.getDepartamento()));
		
		
		// guardar la nueva ciudad
		factory.getCiudadDAO().crear(ciudadEntity);
		// TODO Auto-generated method stub
		
	}
	
	private final UUID generarIdentificadorCiudad() {
		UUID id = UUIDHelper.generate();
		boolean existeId = true;
		while (existeId) {
			id = UUIDHelper.generate();
			var ciudadEntity = CiudadEntity.build().setId(id);
		var resultados = factory.getCiudadDAO().consultar(null);
		existeId = !resultados.isEmpty();
		}
		return id;
	}
	
	private final void validarCiudadMismoNombreMismoDepartamento(final String nombreCiudad,final UUID idDepartamento) {
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad).setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		if (!resultados.isEmpty()){
			var mensajeUsuario = "ya existe una ciudad con el nombre \"${1}\"asociado al departamento";
			throw new BusinessPCHException(mensajeUsuario);
			
		}
	}
}
