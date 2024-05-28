package co.edu.uco.business.usecase.impl.ciudad;

import java.util.UUID;

import org.springframework.boot.autoconfigure.kafka.SslBundleSslEngineFactory;

import co.edu.uco.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.business.domain.CiudadDomain;
import co.edu.uco.business.usecase.UseCaseWithOutReturn;
import co.edu.uco.crosscutting.ecxeptions.custom.BusinessPCHException;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.crosscutting.ecxeptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.data.dao.factory.DAOFactory;
import co.edu.uco.entity.CiudadEntity;
import co.edu.uco.entity.DepartamentoEntity;

public final class RegistrarCiudad implements UseCaseWithOutReturn<CiudadDomain> {

	private DAOFactory factory;

	private static final int MAX_LENGTH_NOMBRE_CIUDAD = 50;

	public RegistrarCiudad(final DAOFactory factory) {
		if (ObjectHelper.getObjectHelper().isNull(factory)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario);
		}
		this.factory = factory;
	}

	@Override
	public void execute(final CiudadDomain data) {
		// validar que los datos requeridos por el caso de uso sean correctos
		// a nivel de tipo de dato, longitud,obligatoriedad,formato,rango
		validarDatos(data);

		// validar que no exista otra ciudad con el mismo nombre y departamento
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());

		// validar que no exista otra ciudad con el mismo identificador
		var ciudadEntity = CiudadEntity.build().setId(generarIdentificadorCiudad()).setNombre(data.getNombre())
				.setDepartamento(DepartamentoAssemblerEntity.getInstance().toEntity(data.getDepartamento()));

		// guardar la nueva ciudad
		factory.getCiudadDAO().crear(ciudadEntity);
		

	}

	private final UUID generarIdentificadorCiudad() {
		UUID id = UUIDHelper.generate();
		boolean existeId = true;
		while (existeId) {
			id = UUIDHelper.generate();
			var ciudadEntity = CiudadEntity.build().setId(id);
			var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
			existeId = !resultados.isEmpty();
		}
		return id;
	}

	private final void validarCiudadMismoNombreMismoDepartamento(final String nombreCiudad, final UUID idDepartamento) {
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad)
				.setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		if (!resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032);
			throw new BusinessPCHException(mensajeUsuario);
		}

	}

	private void validarDatos(final CiudadDomain data) {

		if (TextHelper.isNull(data.getNombre())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00034);
			throw new BusinessPCHException(mensajeUsuario,mensajeTecnico);
		}

		if (ObjectHelper.getObjectHelper().isNull(data.getDepartamento())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
			throw new BusinessPCHException(mensajeTecnico,mensajeUsuario);
		}

		if (data.getNombre().length() > MAX_LENGTH_NOMBRE_CIUDAD) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00037);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038);
			throw new BusinessPCHException(mensajeTecnico,mensajeUsuario);
		}

		if (!isValidCityNameFormat(data.getNombre())) {
			var mensajeUsuario= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00039);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00040);
			throw new BusinessPCHException(mensajeTecnico,mensajeUsuario);
		}
	}

	private boolean isValidCityNameFormat(String nombre) {

		return nombre.matches("[a-zA-Z]+");
	}

}
