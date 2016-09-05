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
import ability.admtablas.entities.Entidad;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

@ManagedBean(name = "entidadBean")
@ViewScoped
public class EntidadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log _log = LogFactoryUtil.getLog(EntidadBean.class);

	public EntidadBean() {
		super();
	}
	
	private Entidad entidadSeleccionada;		
	private List<Entidad> listaEntidad;	
	private Entidad nuevaEntidad;
	private String idRegSeleccionado;
	
	@PostConstruct
	public void init(){
		nuevaEntidad = new Entidad();
	}
	
	public Entidad getEntidadSeleccionada() {
		return entidadSeleccionada;
	}

	public void setEntidadSeleccionada(Entidad entidadSeleccionada) {
		this.entidadSeleccionada = entidadSeleccionada;
	}

	public void loadDatos(){

	}

	public List<Entidad> getListaEntidad() throws SQLException {
		listaEntidad= new ArrayList<Entidad>();
		
		ResultSet result = Controller.listEntidad();
		if (result != null){
			while(result.next()){
				Entidad entidad = new Entidad(result.getString(1), result.getString(2));
				listaEntidad.add(entidad);
			}
		}else {
			System.out.println("no ahy coincidencias de busqueda");
		}
		return listaEntidad;
	}

	public void setListaEntidad(List<Entidad> listaEntidad) {
		this.listaEntidad = listaEntidad;
	}
	
	public Entidad getNuevaEntidad() {
		return nuevaEntidad;
	}

	public void setNuevaEntidad(Entidad nuevaEntidad) {
		this.nuevaEntidad = nuevaEntidad;
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
		ResultSet result = Controller.itemEntidad(idRegSelec);
		if(result != null){
			while(result.next()){
				entidadSeleccionada = new Entidad(result.getString(1), result.getString(2));
			}
		}
	}
	
	public String crearRegistro() throws SQLException{
		String page =null;
		if(nuevaEntidad.getCodigo().length() > 2){
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_CODIGO_LARGO);
			return null;
		}
		ResultSet result = Controller.validarEntidad(nuevaEntidad.getCodigo());
		if(!result.next()){
			try {
				Controller.addEntidad(nuevaEntidad.getCodigo(), nuevaEntidad.getNombre());
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
			Controller.editEntidad(entidadSeleccionada);
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
			Controller.deleteEntidad(entidadSeleccionada);
			FacesMessageUtil.info(FacesContext.getCurrentInstance(), Constants.OPERACION_EXITOSA);
			page = "transaccionExitosa";
		} catch (Exception e) {
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
			e.printStackTrace();
		}
		return page;
	}
}
