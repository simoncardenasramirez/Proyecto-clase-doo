package co.edu.uco.crosscutting.ecxeptions;

import co.edu.uco.crosscutting.ecxeptions.enums.Lugar;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;

public class PCHException extends RuntimeException{


	private static final long serialVersionUID = -1204292929766811976L;
	
	protected String mensajeUsuario;
	protected Lugar lugar;
	
	public PCHException(String mensajeTecnico,String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico,excepcionRaiz);
		setMensajeUsuario  (mensajeUsuario);
		setLugar (lugar);
	}
	
	public PCHException(final String mensajeUsuario,final Lugar lugar) {
		super(mensajeUsuario,new Exception());
		setMensajeUsuario  (mensajeUsuario);
		setLugar (lugar);
	}
	
	public PCHException(String mensajeTecnico,String mensajeUsuario, Lugar lugar) {
		super(mensajeTecnico);
		setMensajeUsuario  (mensajeUsuario);
		setLugar (lugar);
	}



	public static final long getSerialversionuid() {
		return serialVersionUID;
	}



	public final String getMensajeUsuario() {
		return mensajeUsuario;
	}



	public final Lugar getLugar() {
		return lugar;
	}



	private final void setMensajeUsuario(final String mensajeUsuario) {
		this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
	}


	private final void setLugar(final Lugar lugar) {
		this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
	}
	
	

}
