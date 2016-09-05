package ability.admtablas.entities;

import java.io.Serializable;

public class Especialidad implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	
	public Especialidad() {
		super();
	}

	public Especialidad(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getCodigo() {
		return this.codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
