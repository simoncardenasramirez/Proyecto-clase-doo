package co.edu.uco.crosscutting.ecxeptions;

import co.edu.uco.crosscutting.ecxeptions.enums.Lugar;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;

public class ControllerPCHException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	protected String mensajeUsuario;
	protected Lugar lugar = Lugar.CROSSCUTTING ;
	
	public ControllerPCHException(String mensajeTecnico, String mensajeUsuario,Lugar lugar, Throwable excepcionRaiz ) {
		super(mensajeTecnico,excepcionRaiz);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}
	
	public ControllerPCHException(final String mensajeUsuario, final Lugar lugar ) {
		super(mensajeUsuario, new Exception());
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}
	
	
	public ControllerPCHException(final String mensajeTecnico, final String mensajeUsuario, final Lugar lugar ) {
		super(mensajeUsuario, new Exception());
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}


	
	public void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
	}
	
	private final void setLugar(final Lugar lugar) {
		this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
	}

	public Lugar getLugar() {
		return lugar;
	}

	public String getMensajeUsuario() {
		return mensajeUsuario;
	}
	
	

	
	
	
	
	
	


}
