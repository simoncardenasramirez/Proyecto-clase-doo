package co.edu.uco.business.facade;

public interface FacadeWithReturn<T, K>{
	
	K execute(T dto);
	
}
