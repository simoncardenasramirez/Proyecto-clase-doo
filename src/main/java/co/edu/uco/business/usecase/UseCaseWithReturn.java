package co.edu.uco.business.usecase;

public interface UseCaseWithReturn<T,R> {
	
	R execute(T data);

}
