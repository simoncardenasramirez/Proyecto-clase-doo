package co.edu.uco.data.dao.entity;

import java.util.UUID;

import co.edu.uco.entity.CiudadEntity;

public interface CiudadDAO 
		extends CreateDAO<CiudadEntity>, RetriveDAO<CiudadEntity>, UpdateDAO<CiudadEntity>, DeleteDAO<UUID> {

}
