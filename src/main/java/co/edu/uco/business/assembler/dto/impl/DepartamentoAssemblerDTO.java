package co.edu.uco.business.assembler.dto.impl;

import co.edu.uco.business.assembler.dto.AssemblerDTO;
import co.edu.uco.business.domain.DepartamentoDomain;
import co.edu.uco.business.domain.PaisDomain;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.dto.DepartamentoDTO;
import co.edu.uco.dto.PaisDTO;
import static co.edu.uco.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;


public final class DepartamentoAssemblerDTO implements AssemblerDTO <DepartamentoDomain, DepartamentoDTO>{
	
	private static final AssemblerDTO<PaisDomain, PaisDTO> paisAssembler =  PaisAssemblerDTO.getInstance();
	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> instance =
			new DepartamentoAssemblerDTO();
	private DepartamentoAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> getInstance(){
		return instance;
	}
	@Override
	public DepartamentoDomain toDomain(DepartamentoDTO data) {
		var departamentoDTOTmp = getObjectHelper().getDefaultValue(data, DepartamentoDTO.build());
		var paisDomain = paisAssembler.toDomain(departamentoDTOTmp.getPais());
		return DepartamentoDomain.build(departamentoDTOTmp.getId(),departamentoDTOTmp.getNombre(),paisDomain);
	}

	@Override
	public DepartamentoDTO toDTO(final DepartamentoDomain domain) {
		var departamentoDomainTmp = getObjectHelper().getDefaultValue(domain, DepartamentoDomain.build());
		var paisDTO = paisAssembler.toDTO(departamentoDomainTmp.getPais());
		return DepartamentoDTO.build().setId(departamentoDomainTmp.getId()).setNombre(departamentoDomainTmp.getNombre()).setPais(paisDTO);
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoDTO> dtoCollection) {
	var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<DepartamentoDTO>());
		
		return dtoCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<DepartamentoDTO> toDTOCollection(List<DepartamentoDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<DepartamentoDomain>());
		
		return domainCollection.stream().map(this::toDTO).toList();
	}
	
	
}