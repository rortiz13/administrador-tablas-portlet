package ability.admtablas.entities;

import java.io.Serializable;
import java.util.Comparator;

public class UsuarioCiudad implements Serializable, Comparable<UsuarioCiudad>{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Usuario usuario;
	private Ciudad ciudad;
	
	public UsuarioCiudad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsuarioCiudad(String codigo, Ciudad ciudad, Usuario usuario) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.ciudad = ciudad;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	Comparator<UsuarioCiudad> comparador =  new Comparator<UsuarioCiudad>() {
        public int compare( UsuarioCiudad a, UsuarioCiudad b ) {
            int resultado =  a.usuario.getEmail().compareTo(b.usuario.getEmail());
            if ( resultado != 0 ) { return resultado; }
			return 0;
        }
    };

	@Override
	public int compareTo(UsuarioCiudad o) {
	    return usuario.getEmail().compareTo(o.usuario.getEmail());
	}
}
