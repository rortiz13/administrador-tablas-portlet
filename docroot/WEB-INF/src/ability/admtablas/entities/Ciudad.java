package ability.admtablas.entities;

import java.io.Serializable;

public class Ciudad implements Serializable  {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private String departamento;	
	
	public Ciudad() {
		super();
	}

	public Ciudad(String codigo, String nombre, String departamento) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.departamento = departamento;
	}

	public String getCodigo() {
		return this.codigo;
	}	
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}		
	
	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}
