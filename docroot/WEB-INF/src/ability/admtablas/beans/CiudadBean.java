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

import ability.admtablas.beans.Constants;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

import ability.admtablas.controller.Controller;
import ability.admtablas.entities.Ciudad;

@ManagedBean(name = "ciudadBean")
@ViewScoped
public class CiudadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(CiudadBean.class);
	
	private Ciudad ciudadSeleccionada;		
	private List<Ciudad> listaCiudad;
	private Ciudad nuevaCiudad;
	private String idRegSeleccionado;
	
	@PostConstruct
	public void init(){
		nuevaCiudad = new Ciudad();
	}
	
	public Ciudad getNuevaCiudad() {
		return nuevaCiudad;
	}

	public void setNuevaCiudad(Ciudad nuevaCiudad) {
		this.nuevaCiudad = nuevaCiudad;
	}

	public Ciudad getCiudadSeleccionada() {
		return ciudadSeleccionada;
	}

	public void setCiudadSeleccionada(Ciudad ciudadSeleccionada) {
		this.ciudadSeleccionada = ciudadSeleccionada;
	}
	
	public CiudadBean() throws SQLException {		

	}
	
	public void limpiar() {
		ciudadSeleccionada = null;		
	}	
	
	public void loadDatos(){
		_log.info("Load Datos Ciudad");
	}

	public List<Ciudad> getListaCiudad() throws SQLException {
		listaCiudad= new ArrayList<Ciudad>();
		
		ResultSet result = Controller.listCiudad(); 
		if (result != null){
			while(result.next()){
				Ciudad ciudad=new Ciudad(result.getString(1), result.getString(2), result.getString(3));
				listaCiudad.add(ciudad);
			}
		}else {
			System.out.println("no ahy coincidencias de busqueda");
		}
		return listaCiudad;
	}

	public void setListaCiudad(List<Ciudad> listaCiudad) {
		this.listaCiudad = listaCiudad;
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
		ResultSet result = Controller.itemCiudad(idRegSelec);
		if(result != null){
			while(result.next()){
				ciudadSeleccionada = new Ciudad(result.getString(1), result.getString(2), result.getString(3));
			}
		}
	}
	
	public String crearRegistro() throws SQLException{
		String page = null;
		ResultSet result = Controller.validarCiudad(nuevaCiudad.getCodigo());
		if(!result.next()){
			try {
				Controller.addCiudad(nuevaCiudad.getCodigo(), nuevaCiudad.getNombre(), nuevaCiudad.getDepartamento());
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
			Controller.editCiudad(ciudadSeleccionada);
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
			Controller.deleteCiudad(ciudadSeleccionada);
			FacesMessageUtil.info(FacesContext.getCurrentInstance(), Constants.OPERACION_EXITOSA);
			page = "transaccionExitosa";
		} catch (Exception e) {
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
			e.printStackTrace();
		}
		return page;
	}
}
