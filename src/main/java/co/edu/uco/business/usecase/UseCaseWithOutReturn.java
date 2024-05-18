package co.edu.uco.business.usecase;

public interface UseCaseWithOutReturn<T> {
	
	void execute(T data);

}
