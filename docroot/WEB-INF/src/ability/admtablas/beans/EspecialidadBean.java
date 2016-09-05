package ability.admtablas.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ability.admtablas.controller.Controller;
import ability.admtablas.entities.Especialidad;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

@ManagedBean(name = "especialidadBean")
@ViewScoped
public class EspecialidadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log _log = LogFactoryUtil.getLog(EspecialidadBean.class);

	public EspecialidadBean(){
		super();
	}
	
	private Especialidad especialidadSeleccionada;	
	private List<Especialidad> listaEspecialidad;
	private Especialidad nuevaEspecialidad;
	private String idRegSeleccionado;
	
	@PostConstruct
	public void init(){
		nuevaEspecialidad = new Especialidad();
	}
	
	public Especialidad getEspecialidadSeleccionada() {
		return especialidadSeleccionada;
	}

	public void setEspecialidadSeleccionada(Especialidad especialidadSeleccionada) {
		this.especialidadSeleccionada = especialidadSeleccionada;
	}
	
	public Especialidad getNuevaEspecialidad() {
		return nuevaEspecialidad;
	}

	public void setNuevaEspecialidad(Especialidad nuevaEspecialidad) {
		this.nuevaEspecialidad = nuevaEspecialidad;
	}

	public void loadDatos(){
		_log.info("Load Datos Especialidad");
	}

	public List<Especialidad> getListaEspecialidad() throws SQLException {
		listaEspecialidad= new ArrayList<Especialidad>();
		
		ResultSet result = Controller.listEspecialidad();
		if (result != null){
			while(result.next()){
				Especialidad especialidad = new Especialidad(result.getString(1), result.getString(2));
				listaEspecialidad.add(especialidad);
			}
		}else {
			System.out.println("no ahy coincidencias de busqueda");
		}
		return listaEspecialidad;
	}

	public void setListaEspecialidad(List<Especialidad> listaEspecialidad) {
		this.listaEspecialidad = listaEspecialidad;
	}
	
	public String getIdRegSeleccionado() {
		return idRegSeleccionado;
	}

	public void setIdRegSeleccionado(String idRegSeleccionado) {
		this.idRegSeleccionado = idRegSeleccionado;
	}

	public void cargarObjeto() throws SQLException{
		String idRegSelec = idRegSeleccionado;
		if(idRegSeleccionado == null){
			 FacesContext facesContext = FacesContext.getCurrentInstance();
		     idRegSelec = facesContext.getExternalContext().getRequestParameterMap().get("idRegSeleccionado");		     
		}
		_log.info("id regseleccionado: "+idRegSelec);
		ResultSet result = Controller.itemEspecialidad(idRegSelec);
		if(result != null){
			while(result.next()){
				especialidadSeleccionada = new Especialidad(result.getString(1), result.getString(2));
			}
		}
	}
	
	public String crearRegistro() throws SQLException{
		String page = null;
		if(nuevaEspecialidad.getCodigo().length() > 2){
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_CODIGO_LARGO);
			return null;
		}
		ResultSet result = Controller.validarEspecialidad(nuevaEspecialidad.getCodigo());
		if(!result.next()){
			try {
				Controller.addEspecialidad(nuevaEspecialidad.getCodigo(), nuevaEspecialidad.getNombre());
				FacesMessageUtil.info(FacesContext.getCurrentInstance(), Constants.OPERACION_EXITOSA);
				page = "transaccionExitosa";
			} catch (Exception e) {
				FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
				e.printStackTrace();
			}
			return page;
		}
		else{
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_CODIGO_DUPLICADO);
			return null;
		}
	}
	
	public String actualizarRegistro(){
		String page = null;
		try {
			Controller.editEspecialidad(especialidadSeleccionada);
			FacesMessageUtil.info(FacesContext.getCurrentInstance(), Constants.OPERACION_EXITOSA);
			page = "transaccionExitosa";
		} catch (Exception e) {
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
			e.printStackTrace();
		}
		return page;
	}
	
	public String eliminarRegistro(){
		String page =null;
		try {
			Controller.deleteEspecialidad(especialidadSeleccionada);
			FacesMessageUtil.info(FacesContext.getCurrentInstance(), Constants.OPERACION_EXITOSA);
			page = "transaccionExitosa";
		} catch (Exception e) {
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
			e.printStackTrace();
		}
		return page;
	}
}
