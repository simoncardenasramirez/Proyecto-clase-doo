package co.edu.uco.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.business.facade.imp.ciudad.ConsultarCiudadesFacade;
import co.edu.uco.business.facade.imp.ciudad.RegistrarCiudadFacade;
import co.edu.uco.business.usecase.impl.ciudad.RegistrarCiudad;
import co.edu.uco.controller.response.CiudadResponse;
import co.edu.uco.crosscutting.ecxeptions.PCHException;
import co.edu.uco.dto.CiudadDTO;

@RestController
@RequestMapping("/api/v1/ciudades")
public final class CiudadController {
	
	@GetMapping("/dummy")
	public CiudadDTO dummy() {
		return CiudadDTO.build();
	}
	
	@GetMapping
public ResponseEntity<CiudadResponse> consultar(){
		
		var hhtpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();
		
		try {
			
			var ciudadDto = CiudadDTO.build();
			var facade = new ConsultarCiudadesFacade();
			
			ciudadResponse.setDatos(facade.execute(null));
			ciudadResponse.getMensajes().add("Ciudades consultadas exitosamente");
			
		} catch (final PCHException excepcion) {
			hhtpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
		} catch (final Exception excepcion) {
			hhtpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de las ciudades";
			ciudadResponse.getMensajes().add(mensajeUsuario);
			
			excepcion.printStackTrace();
		}
		
		
		return new ResponseEntity<>(ciudadResponse, hhtpStatusCode);
		
	}
	
	@PostMapping
public ResponseEntity<CiudadResponse> crear(@RequestBody CiudadDTO ciudad){
		
		var httpStatusCode = HttpStatus.ACCEPTED;
		var CiudadResponse = new CiudadResponse();
		
		try {		
			var facade = new RegistrarCiudadFacade();
			facade.execute(ciudad);
			CiudadResponse.getMensajes().add("Ciudades creadas exitosamente");
			
			
		} catch (final PCHException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			CiudadResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
		}catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			var mensajeUsuario ="Se ha presentado un problema tratando de registrar la nueva ciudad";
			CiudadResponse.getMensajes().add(mensajeUsuario);
		}
		
		return new ResponseEntity<>(CiudadResponse,httpStatusCode);
		
	}
	
	@DeleteMapping("/{id}")
public ResponseEntity<CiudadResponse> eliminar(@PathVariable UUID id){
		
		var httpStatusCode = HttpStatus.ACCEPTED;
		var CiudadResponse = new CiudadResponse();
		
		try {		
			//var facade = new EliminarCiudadFacade
		
			////facade.execute(id);
			
			CiudadResponse.getMensajes().add("Ciudad eliminada exitosamente");
			
			
		} catch (final PCHException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			CiudadResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
		}catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			var mensajeUsuario ="Se ha presentado un problema tratando de eliminar la informacion de la ciudad";
			CiudadResponse.getMensajes().add(mensajeUsuario);
		}
		
		return new ResponseEntity<>(CiudadResponse,httpStatusCode);
		
	}
	
	
	
	
	@PostMapping("/{id}")
public ResponseEntity<CiudadResponse> modificar(@PathVariable UUID id, @RequestBody CiudadDTO ciudadDto){
		
		var httpStatusCode = HttpStatus.ACCEPTED;
		var CiudadResponse = new CiudadResponse();
		
		try {		
			//var facade = new EliminarCiudadFacade();
		
			//facade.execute(id);
			
			CiudadResponse.getMensajes().add("Ciudad modificada exitosamente");
			
			
		} catch (final PCHException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			CiudadResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
		}catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			var mensajeUsuario ="Se ha presentado un problema tratando de modificar la informacion de la ciudad";
			CiudadResponse.getMensajes().add(mensajeUsuario);
		}
		
		return new ResponseEntity<>(CiudadResponse,httpStatusCode);
		
	}
	
}
