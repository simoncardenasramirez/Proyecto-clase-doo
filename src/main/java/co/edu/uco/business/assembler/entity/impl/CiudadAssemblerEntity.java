package co.edu.uco.business.assembler.entity.impl;

import co.edu.uco.business.assembler.entity.AssemblerEntity;
import co.edu.uco.business.domain.CiudadDomain;
import co.edu.uco.business.domain.DepartamentoDomain;
import co.edu.uco.crosscutting.helpers.ObjectHelper;

import static co.edu.uco.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public final CiudadDomain toDomain(final CiudadEntity data) {
		var ciudadEntityTmp = getObjectHelper().getDefaultValue(data, CiudadEntity.build());
		var departamentoDomain = departamentoAssembler.toDomain(ciudadEntityTmp.getDepartamento());
		return CiudadDomain.build(ciudadEntityTmp.getId(),ciudadEntityTmp.getNombre(),departamentoDomain);
	}


	public final CiudadEntity toEntity(final CiudadDomain domain) {
		var ciudadDomainTmp = getObjectHelper().getDefaultValue(domain, CiudadDomain.build());
		var departamentoEntity = departamentoAssembler.toEntity(ciudadDomainTmp.getDepartamento());
		return CiudadEntity.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre()).setDepartamento(departamentoEntity);
	}

	@Override
	public final List<CiudadDomain> toDomainCollection(final List<CiudadEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<CiudadEntity>());
		

		return  entityCollectionTmp.stream().map(this::toDomain).toList();
		

	}

	@Override
	public List<CiudadEntity> toEntityCollection(List<CiudadDomain> domainCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<CiudadDomain>());
		

		return  entityCollectionTmp.stream().map(this::toEntity).toList();
	}
	
}
