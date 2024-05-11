package co.edu.uco.data.dao.entity;

import java.util.List;

public interface RetriveDAO<E> {
	
	List<E> consultar(E data);

}
