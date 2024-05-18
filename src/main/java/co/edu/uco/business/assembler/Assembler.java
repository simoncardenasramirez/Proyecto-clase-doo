package co.edu.uco.business.assembler;

public interface Assembler<D,K> {
	
	D toDomain(K data);
	

}
