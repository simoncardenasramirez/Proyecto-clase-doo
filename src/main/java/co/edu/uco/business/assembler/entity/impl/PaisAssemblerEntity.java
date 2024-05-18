package co.edu.uco.business.assembler.entity.impl;

import co.edu.uco.business.assembler.entity.AssemblerEntity;
import co.edu.uco.business.domain.PaisDomain;
import static co.edu.uco.crosscutting.helpers.ObjectHelper.getObjectHelper;
import co.edu.uco.entity.PaisEntity;

public class PaisAssemblerEntity implements AssemblerEntity<PaisDomain, PaisEntity> {
	
private final static AssemblerEntity<PaisDomain, PaisEntity> instance =  new PaisAssemblerEntity();
	
	private PaisAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<PaisDomain, PaisEntity> getInstance(){
		return instance;
	}
	public PaisDomain toDomain(PaisEntity data) {
		var paisEntityTmp = getObjectHelper().getDefaultValue(data, PaisEntity.build());
		return PaisDomain.build(data.getId(), paisEntityTmp.getNombre());
	}

	public PaisEntity toEntity(PaisDomain domain) {
		var paisDomainTmp = getObjectHelper().getDefaultValue(domain, PaisDomain.build());
		return PaisEntity.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

}
