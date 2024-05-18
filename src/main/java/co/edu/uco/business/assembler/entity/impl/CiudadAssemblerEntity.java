package co.edu.uco.business.assembler.entity.impl;

import co.edu.uco.business.assembler.entity.AssemblerEntity;
import co.edu.uco.business.domain.CiudadDomain;
import co.edu.uco.business.domain.DepartamentoDomain;
import static co.edu.uco.crosscutting.helpers.ObjectHelper.getObjectHelper;
import co.edu.uco.entity.CiudadEntity;
import co.edu.uco.entity.DepartamentoEntity;

public class CiudadAssemblerEntity implements AssemblerEntity<CiudadDomain, CiudadEntity>{

	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoAssemblerEntity.getInstance();
	private static final AssemblerEntity<CiudadDomain, CiudadEntity> instance = new CiudadAssemblerEntity();
	
	private CiudadAssemblerEntity() {
		super();
	}

	public static final AssemblerEntity<CiudadDomain, CiudadEntity> getInstance(){
		return instance;
	}

	public CiudadDomain toDomain(CiudadEntity data) {
		var ciudadEntityTmp = getObjectHelper().getDefaultValue(data, CiudadEntity.build());
		var departamentoDomain = departamentoAssembler.toDomain(ciudadEntityTmp.getDepartamento());
		return CiudadDomain.build(ciudadEntityTmp.getId(),ciudadEntityTmp.getNombre(),departamentoDomain);
	}


	public CiudadEntity toEntity(CiudadDomain domain) {
		var ciudadDomainTmp = getObjectHelper().getDefaultValue(domain, CiudadDomain.build());
		var departamentoEntity = departamentoAssembler.toEntity(ciudadDomainTmp.getDepartamento());
		return CiudadEntity.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre()).setDepartamento(departamentoEntity);
	}
	
}
