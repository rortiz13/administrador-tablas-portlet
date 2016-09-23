package ability.admtablas.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

import ability.admtablas.entities.Ciudad;
import ability.admtablas.entities.Entidad;
import ability.admtablas.entities.Especialidad;
import ability.admtablas.entities.UsuarioCiudad;
import ability.admtablas.persistence.*;

public class Controller {
	
	private static Log _log = LogFactoryUtil.getLog(Controller.class);
	
//	Ciudad
	
	public static ResultSet listCiudad(){
		
		String sql ="select * from T065BACIUDGENE " ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
	
	public static ResultSet itemCiudad(String ciudadCodigo){
		
		String sql ="select * from T065BACIUDGENE where A065CODICIUD = '"+ ciudadCodigo +"'";
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	

	public static void addCiudad(String idciudad, String nombreciudad, String organizacion)
    {
			
        String sql = "";
        sql = "insert into T065BACIUDGENE(A065CODICIUD,A065DESCCIUD,DEPARTAMENTO) " +
              "values('"+idciudad.trim()+"','"+nombreciudad.trim()+"','"+organizacion.trim()+"')";
        System.out.println(sql);
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
	
	public static ResultSet validarCiudad(String ciudadCodigo){
		
		String sql ="select * from T065BACIUDGENE where A065CODICIUD = '"+ ciudadCodigo +"'";
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
	
	public static void deleteCiudad(Ciudad row) {
		String sql = "";
        sql = "delete from T065BACIUDGENE where A065CODICIUD = '"+row.getCodigo()+"'";       
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            FacesMessageUtil.error(FacesContext.getCurrentInstance(), e.getMessage());
        }
	}

	public static void editCiudad(Ciudad row) {
		String sql = "";
        sql = 	" update T065BACIUDGENE "+
        		" set A065CODICIUD = '"+row.getCodigo() +
        		"' ,A065DESCCIUD = '"+row.getNombre() +
        		"' ,DEPARTAMENTO = '"+row.getDepartamento() +
        		"' where A065CODICIUD = '"+row.getCodigo()+"'";	      
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
	}		
	
//	Entidad
	
	public static ResultSet listEntidad(){
		
		String sql ="select * from T051BAENTIGENE " ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
	
	public static ResultSet itemEntidad(String entidadCodigo){
		
		String sql ="select * from T051BAENTIGENE where A051CODIENTI = '"+ entidadCodigo +"'";
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	

	public static void addEntidad(String codigoentidad, String nombreentidad)
    {
			
        String sql = "";
        sql = "insert into T051BAENTIGENE(A051CODIENTI,A051DESCENTI) " +
              "values('"+codigoentidad.trim()+"','"+nombreentidad.trim()+"')";
        System.out.println(sql);
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            FacesMessageUtil.error(FacesContext.getCurrentInstance(), e.getMessage());
        }
    }
	
	public static ResultSet validarEntidad(String entidadCodigo){
		
		String sql ="select * from T051BAENTIGENE where A051CODIENTI = '"+ entidadCodigo +"'";
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
	
	public static void deleteEntidad(Entidad row) {
		String sql = "";
        sql = "delete from T051BAENTIGENE where A051CODIENTI = '"+row.getCodigo()+"'";       
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            FacesMessageUtil.error(FacesContext.getCurrentInstance(), e.getMessage());
        }
	}

	public static void editEntidad(Entidad row) {
		String sql = "";
        sql = 	" update T051BAENTIGENE "+
        		" set A051CODIENTI = '"+row.getCodigo() +
        		"' ,A051DESCENTI = '"+row.getNombre() +
        		"' where A051CODIENTI = '"+row.getCodigo()+"'";	      
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
	}
	
//	Especialidad
	
	public static ResultSet listEspecialidad(){
		
		String sql ="select * from T062BAESPEGENE " ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
	
	public static ResultSet itemEspecialidad(String especialidadCodigo){
		
		String sql ="select * from T062BAESPEGENE where A062CODIESPE = '"+ especialidadCodigo +"'";
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	


	public static void addEspecialidad(String codigoespecialidad, String nombreespecialidad)
    {
			
        String sql = "";
        sql = "insert into T062BAESPEGENE(A062CODIESPE,A062DESCESPE) " +
              "values('"+codigoespecialidad.trim()+"','"+nombreespecialidad.trim()+"')";
        System.out.println(sql);
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            FacesMessageUtil.error(FacesContext.getCurrentInstance(), e.getMessage());
        }
    }
	
	public static ResultSet validarEspecialidad(String especialidadCodigo){
		
		String sql ="select * from T062BAESPEGENE where A062CODIESPE = '"+ especialidadCodigo +"'";
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}
	
	public static void deleteEspecialidad(Especialidad row) {
		String sql = "";
        sql = "delete from T062BAESPEGENE where A062CODIESPE = '"+row.getCodigo()+"'";       
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            FacesMessageUtil.error(FacesContext.getCurrentInstance(), e.getMessage());
        }
	}

	public static void editEspecialidad(Especialidad row) {
		String sql = "";
        sql = 	" update T062BAESPEGENE "+
        		" set A062CODIESPE = '"+row.getCodigo() +
        		"' ,A062DESCESPE = '"+row.getNombre() +
        		"' where A062CODIESPE = '"+row.getCodigo()+"'";	      
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
	}
	
//	Usuario
	
	public static ResultSet listUsuarioCiudad(){
		
		String sql ="select * from UsuarioCiudad1" ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
		
	public static void saveUsuarioCiudad(String usuario, String ciudad){
		
		String sql = "";
		sql = "insert into UsuarioCiudad1(ciudad,userid) " +
			              "values('"+ciudad.trim()+"','"+usuario.trim()+"')";
        System.out.println(sql);
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            FacesMessageUtil.error(FacesContext.getCurrentInstance(), e.getMessage());
        }
	}	
	
	public static ResultSet itemDepartamento(String codigoCiudad){
	
		String sql ="select DEPARTAMENTO from T065BACIUDGENE where A065CODICIUD = '"+ codigoCiudad.trim() +"'";
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
	
	public static ResultSet itemUsuarioCiudad(String codigoCiudadusuario){
		
		String sql ="select * from UsuarioCiudad1 where id = "+ codigoCiudadusuario.trim();
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("No ahy  registro cargados en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}	
	
	public static void deleteUsuarioCiudad(UsuarioCiudad row) {
		String sql = "";
        sql = "delete from UsuarioCiudad1 where id = "+row.getCodigo();       
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            FacesMessageUtil.error(FacesContext.getCurrentInstance(), e.getMessage());
        }
	}

}
