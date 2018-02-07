package local.fran.Spring;

import java.sql.Statement;
import java.util.Hashtable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import local.fran.Spring.models.LoginDto;

@Controller
public class AccountController {
	
	
	
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showFormlogin() {
		
        return new ModelAndView("login", "logindto", new LoginDto());
    }
	
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submit(@ModelAttribute("logindto")LoginDto login,BindingResult result,  HttpSession session) throws NamingException, SQLException {
        if (result.hasErrors()) {
            return "error";
        }
        
        
		
		System.out.println(authLdap(login));
		System.out.println(authSalika(login));
		if(authSalika(login)||authLdap(login)) {
			session.setAttribute("user", login);
   		 	session.removeAttribute("error");
   		 	session.removeAttribute("msgerrorlogin");
   		 	return "home";
			
		}else {
			session.setAttribute("error", "Fallo la utentificacion");
    		return "home";
		}
        
       
       
        
        
    }
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showFormregister() {
        return new ModelAndView("register", "logindto", new LoginDto());
    }
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
    public String createaccount(@ModelAttribute("logindto")LoginDto login,BindingResult result) throws NamingException, SQLException {
        if (result.hasErrors()) {
            return "error";
        }
        
        InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		PreparedStatement sta=conn.prepareStatement("INSERT INTO users (user,password) VALUES(?,?)");
		sta.setString(1, login.getUser());
		sta.setString(2, login.getPassword());
		sta.executeUpdate();
		
        return "Cuenta creada ir al login <br> <a href=\"./login\">Volver a la Home</a> ";
    }
	
	
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
    public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("error");
		session.removeAttribute("msgerrorlogin");
        return "Sesion Destruida <br> <a href=\"/Spring\">Volver a la Home</a>";
    }
	
	
	private boolean authSalika(LoginDto login) throws SQLException, NamingException {
		
		
        InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		Statement sta=conn.createStatement();
		ResultSet rs=sta.executeQuery("SELECT password FROM users WHERE user='"+login.getUser()+"'");
		
        
		if(rs.next()) {
        	if(rs.getString("password").equals(login.getPassword())) return true;
        		 
        	else return false;
        	
		}else return false;
		
	}
	
	private boolean authLdap(LoginDto login){
		
		Hashtable <String,String> env=new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL,"ldap://192.168.123.240:389/o=fran.local");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=pepe,dc=fran,dc=local");
		env.put(Context.SECURITY_CREDENTIALS, login.getPassword());
		
		for(String value:env.values()) {
			System.out.println(value);
		}
		
		try {
			@SuppressWarnings("unused")
			DirContext ctx=new InitialDirContext(env);
			
		}catch (AuthenticationException e) {
			
			return false;
		}catch (NamingException e) {
			e.printStackTrace();
		}
		return true;
		
		
		
	}
	
}
