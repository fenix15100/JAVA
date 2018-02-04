package local.fran.Spring;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
        
        
        
        InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		Statement sta=conn.createStatement();
		ResultSet rs=sta.executeQuery("SELECT password FROM users WHERE user='"+login.getUser()+"'");
        
		if(rs.next()) {
        	if(rs.getString("password").equals(login.getPassword())) {
        		 session.setAttribute("user", login);
        		 session.removeAttribute("error");
        		 session.removeAttribute("msgerrorlogin");
        		 return "home";
        		
        	}else {
        		session.setAttribute("error", "Usuario Correcto pero contraseña no");
        		return "home";
        	}
        	
        }else {
        	session.setAttribute("error", "El usuario no existe");
        	return "home";
        }
        
        
        
        //////
       
        
        
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
	
}
