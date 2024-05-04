package co.edu.uco.crosscutting.ecxeptions.messageCatalog.data;

public class Mensaje {
	
	private CodigoMensaje codigo;
	private String contenido;
	
	
	public Mensaje(final CodigoMensaje codigo,final String contenido) {
		setCodigo(codigo);
		setContenido(contenido);
	}

	public final boolean esBase() {
		return getCodigo().isBase();
	}

	public final CodigoMensaje getCodigo() {
		return codigo;
	}
	
	private final void setCodigo(final CodigoMensaje codigo) {
		this.codigo = codigo;
	}
	
	private final void setContenido(final String contenido) {
		this.contenido = contenido;
	}

	public final CategoriaMensaje getCategoria() {
		return getCodigo().getCategoria();
	}

	public final TipoMensaje getTipo() {
		return getCodigo().getTipo();
	}

	public final String getContenido() {
		return contenido;
	}
	
	public final String getIdentificador() {
		return getCodigo().getIdentificador();
	}
	
}