package ability.admtablas.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;

import ability.admtablas.controller.Controller;
import ability.admtablas.entities.Ciudad;
import ability.admtablas.entities.Usuario;
import ability.admtablas.entities.UsuarioCiudad;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

@ManagedBean (name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log _log = LogFactoryUtil.getLog(UsuarioBean.class);
	
	private String usuarioText;
	private String ciudadText;
    private List<UsuarioCiudad> listaUsuarioCiudad;
    private List<SelectItem> ciudadesItems;
    private String idRegSeleccionado;
    private UsuarioCiudad usuarioCiudadSeleccionada;

	public UsuarioBean() {
		super();
	}
    
    public List<String> cargarListaUsuario(String query) throws SQLException, SystemException {
        List<String> results = new ArrayList<String>();
        List<User> user_list = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for(User user: user_list){
        
        	if(user.getStatus() == 0){
				if(user.getEmailAddress().startsWith(query)){
					results.add(user.getEmailAddress());
				}
        	}
		}
         
        return results;
    }
    
    public List<UsuarioCiudad> getListaUsuarioCiudad() throws SQLException, NumberFormatException, PortalException, SystemException {
    	listaUsuarioCiudad = new ArrayList<UsuarioCiudad>();
    	ResultSet result = Controller.listUsuarioCiudad();
		if (result != null){
			while(result.next()){
				ResultSet resultCiudad = Controller.itemCiudad(result.getString(2).trim());
				if(resultCiudad != null){
					if(resultCiudad.next()){
						Ciudad ciudad = new Ciudad(resultCiudad.getString(1), resultCiudad.getString(2), resultCiudad.getString(3));
						Usuario usuario = new Usuario(UserLocalServiceUtil.getUser(Long.valueOf(result.getString(3).trim())).getUserId(), UserLocalServiceUtil.getUser(Long.valueOf(result.getString(3).trim())).getEmailAddress());
						UsuarioCiudad usuarioCiudad = new UsuarioCiudad(result.getString(1), ciudad, usuario);
						listaUsuarioCiudad.add(usuarioCiudad);
					}
				}
			}
		}else {
			System.out.println("no ahy coincidencias de busqueda");
		}
		Collections.sort(listaUsuarioCiudad);
		return listaUsuarioCiudad;
    }

	public void setListaUsuarioCiudad(List<UsuarioCiudad> listaUsuarioCiudad) {
		this.listaUsuarioCiudad = listaUsuarioCiudad;
	}

	public String getUsuarioText() {
		return usuarioText;
	}

	public void setUsuarioText(String usuarioText) {
		this.usuarioText = usuarioText;
	}
	
	public String getCiudadText() {
		return ciudadText;
	}

	public void setCiudadText(String ciudadText) {
		this.ciudadText = ciudadText;
	}

	public String guardarNuevoRegistro() throws SQLException, PortalException, SystemException{
		if(usuarioText != null && !usuarioText.equals("") && ciudadText != null && !ciudadText.equals("")){
			FacesContext context = FacesContext.getCurrentInstance();
			PortletRequest portletRequest = (PortletRequest) context.getExternalContext().getRequest();
			
			try{
				ThemeDisplay  themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
				long company;
				company = themeDisplay.getUser().getCompanyId();
				User usuario = UserLocalServiceUtil.getUserByEmailAddress(company, usuarioText);
				Controller.saveUsuarioCiudad(String.valueOf(usuario.getUserId()), ciudadText);
				FacesMessageUtil.info(FacesContext.getCurrentInstance(), Constants.OPERACION_EXITOSA);
				setUsuarioText("");
				setCiudadText("");
				return "agregar";
			} catch (Exception e) {
				FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
				e.printStackTrace();
			}
			return null;
		}else{
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
			return null;
		}
		
	}
	
	public void limpiar(){
		setUsuarioText("");
		setCiudadText("");
	}

	public List<SelectItem> getCiudadesItems() throws SQLException {
		ciudadesItems = new ArrayList<SelectItem>();
		ResultSet result = Controller.listCiudad();
		if (result != null) {
			for (; result.next(); ciudadesItems.add(new SelectItem(
					result.getString(1), result.getString(2)))) {
			}
		} else {
			System.out.println("no ahy coincidencias de busqueda");
		}
				
		return ciudadesItems;
	}

	public void setCiudadesItems(List<SelectItem> ciudadesItems) {
		this.ciudadesItems = ciudadesItems;
	}

	public String getIdRegSeleccionado() {
		return idRegSeleccionado;
	}

	public void setIdRegSeleccionado(String idRegSeleccionado) {
		this.idRegSeleccionado = idRegSeleccionado;
	}

	public UsuarioCiudad getUsuarioCiudadSeleccionada() {
		return usuarioCiudadSeleccionada;
	}

	public void setUsuarioCiudadSeleccionada(UsuarioCiudad usuarioCiudadSeleccionada) {
		this.usuarioCiudadSeleccionada = usuarioCiudadSeleccionada;
	}

	public void cargarObjeto() throws SQLException, NumberFormatException, PortalException, SystemException{
		String idRegSelec = idRegSeleccionado;
		if(idRegSeleccionado == null){
			 FacesContext facesContext = FacesContext.getCurrentInstance();
		     idRegSelec = facesContext.getExternalContext().getRequestParameterMap().get("idRegSeleccionado");		     
		}
		_log.info("id regseleccionado: "+idRegSelec);
		ResultSet result = Controller.itemUsuarioCiudad(idRegSelec);
		if(result != null){
			if(result.next()){
				ResultSet resultCiudad = Controller.itemCiudad(result.getString(2).trim());
				if(resultCiudad != null){
					if(resultCiudad.next()){
						Ciudad ciudad = new Ciudad(resultCiudad.getString(1), resultCiudad.getString(2), resultCiudad.getString(3));
						Usuario usuario = new Usuario(UserLocalServiceUtil.getUser(Long.valueOf(result.getString(3).trim())).getUserId(), UserLocalServiceUtil.getUser(Long.valueOf(result.getString(3).trim())).getEmailAddress());
						usuarioCiudadSeleccionada = new UsuarioCiudad(result.getString(1), ciudad, usuario);
					}
				}
			}
		}
	}
	
	public String eliminarRegistro(){
		String page = null;
		try {
			Controller.deleteUsuarioCiudad(usuarioCiudadSeleccionada);
			FacesMessageUtil.info(FacesContext.getCurrentInstance(), Constants.OPERACION_EXITOSA);
			page = "transaccionExitosa";
		} catch (Exception e) {
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), Constants.ERROR_OPERACION);
			e.printStackTrace();
		}
		return page;
	}
}
