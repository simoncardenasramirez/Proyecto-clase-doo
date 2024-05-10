package co.edu.uco.crosscutting.ecxeptions.messageCatalog.data;

import static co.edu.uco.crosscutting.TextHelper.concatenate;
import static co.edu.uco.crosscutting.TextHelper.UNDERLINE;

public enum CodigoMensaje {
	
	M00001(TipoMensaje.TECNICO,CategoriaMensaje.ERROR,"00001",true),
	M00002(TipoMensaje.USUARIO,CategoriaMensaje.ERROR,"00002",true),
	M00003(TipoMensaje.TECNICO,CategoriaMensaje.ERROR,"00003",true),
	M00004(TipoMensaje.TECNICO,CategoriaMensaje.ERROR,"00004",true),
	M00005(TipoMensaje.TECNICO,CategoriaMensaje.ERROR,"00005",true),
	M00006(TipoMensaje.TECNICO,CategoriaMensaje.ERROR,"00006",true),
	M00007(TipoMensaje.USUARIO,CategoriaMensaje.EXITO,"00007",false),
	M00008(TipoMensaje.USUARIO,CategoriaMensaje.EXITO,"00008",true);
	
	

	
	
	private TipoMensaje tipo;
	private CategoriaMensaje categoria;
	private String codigo;
	private boolean base;
	
	private CodigoMensaje(final TipoMensaje tipo,
			final  CategoriaMensaje categoria,final String codigo,final boolean base) {
		setTipo(tipo);
		setCodigo(codigo);
		setCategoria(categoria);
		setBase(base);
	}
	
	
	private final void setBase(boolean base) {
		this.base = base;
	}


	public final boolean isBase() {
		return base;
	}


	public final TipoMensaje getTipo() {
		return tipo;
	}


	public final CategoriaMensaje getCategoria() {
		return categoria;
	}


	public final String getCodigo() {
		return codigo;
	}

	

	private final void setTipo(final TipoMensaje tipo) {
		this.tipo = tipo;
	}


	private final void setCategoria(final CategoriaMensaje categoria) {
		this.categoria = categoria;
	}

	private final void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getIdentificador() {
		return concatenate(getTipo().name(),UNDERLINE,getCategoria().name(),
				UNDERLINE,getCodigo());	
	}

}
