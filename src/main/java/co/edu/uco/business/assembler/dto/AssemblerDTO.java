package co.edu.uco.business.assembler.dto;

import co.edu.uco.business.assembler.Assembler;

public interface AssemblerDTO<D,K> extends Assembler<D, K>{
	
	K toDTO(D domain);

}
