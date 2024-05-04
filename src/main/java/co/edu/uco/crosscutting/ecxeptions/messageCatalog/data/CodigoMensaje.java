package co.edu.uco.crosscutting.ecxeptions.messageCatalog.data;

import static co.edu.uco.crosscutting.TextHelper.concatenate;
import static co.edu.uco.crosscutting.TextHelper.UNDERLINE;

public enum CodigoMensaje {
	
	M00001(TipoMensaje.TECNICO  ,CategoriaMensaje.ERROR,"00001",true),
	M00002(TipoMensaje.USUARIO  ,CategoriaMensaje.ERROR,"00002",true),
	M00003(TipoMensaje.TECNICO  ,CategoriaMensaje.ERROR,"00003",true),
	M00004(TipoMensaje.TECNICO  ,CategoriaMensaje.ERROR,"00004",true);
	
	
	private TipoMensaje tipo;
	private CategoriaMensaje categoria;
	private String codigo;
	private boolean base;
	
	
	private CodigoMensaje(final TipoMensaje tipo, final CategoriaMensaje categoria, final String codigo,
			final boolean base) {
		setTipo(tipo);
		setCategoria(categoria);
		setCodigo(codigo);
		setBase(base);
	}


	public final TipoMensaje getTipo() {
		return tipo;
	}


	private final void setTipo(final TipoMensaje tipo) {
		this.tipo = tipo;
	}


	public final CategoriaMensaje getCategoria() {
		return categoria;
	}


	private final void setCategoria(final CategoriaMensaje categoria) {
		this.categoria = categoria;
	}


	public String getCodigo() {
		return codigo;
	}


	private final void setCodigo(final String codigo) {
		this.codigo = codigo;
	}
	
	
	public final String getIdentificador() {
		return concatenate(getTipo().name(),UNDERLINE,getCategoria().name(),UNDERLINE,getCodigo());
	}


	public final boolean isBase() {
		return base;
	}


	private final void setBase(final boolean base) {
		this.base = base;
	}
	
	
	

}
