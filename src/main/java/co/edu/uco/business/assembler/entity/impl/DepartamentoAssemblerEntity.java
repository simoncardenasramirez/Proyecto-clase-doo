package co.edu.uco.business.assembler.entity.impl;

import co.edu.uco.business.assembler.entity.AssemblerEntity;
import co.edu.uco.business.domain.DepartamentoDomain;
import co.edu.uco.business.domain.PaisDomain;
import co.edu.uco.crosscutting.helpers.ObjectHelper;

import static co.edu.uco.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;


import co.edu.uco.entity.DepartamentoEntity;
import co.edu.uco.entity.PaisEntity;

public class DepartamentoAssemblerEntity implements  AssemblerEntity<DepartamentoDomain, DepartamentoEntity> {
	
	private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler =  PaisAssemblerEntity.getInstance();
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> instance =
			new DepartamentoAssemblerEntity();
	private DepartamentoAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> getInstance(){
		return instance;
	}
	public DepartamentoDomain toDomain(DepartamentoEntity data) {
		var departamemtoEntityTmp = getObjectHelper().getDefaultValue(data, DepartamentoEntity.build());
		var paisDomain = paisAssembler.toDomain(departamemtoEntityTmp.getPais());
		return DepartamentoDomain.build(departamemtoEntityTmp.getId(),departamemtoEntityTmp.getNombre(),paisDomain);
	}

	public DepartamentoEntity toEntity(DepartamentoDomain domain) {
		var departamentoDomainTmp = getObjectHelper().getDefaultValue(domain, DepartamentoDomain.build());
		var paisEntity = paisAssembler.toEntity(departamentoDomainTmp.getPais());
		return DepartamentoEntity.build().setId(departamentoDomainTmp.getId()).setNombre(departamentoDomainTmp.getNombre()).setPais(paisEntity);
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<DepartamentoEntity>());
		

		return  entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<DepartamentoEntity> toEntityCollection(List<DepartamentoDomain> domainCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<DepartamentoDomain>());
		

		return  entityCollectionTmp.stream().map(this::toEntity).toList();
	}

}
