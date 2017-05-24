package ch.unige.pinfo.ideas;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


import ch.unige.pinfo.backend.BackEndFacade;
import ch.unige.pinfo.cors.CORSFilter;
import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.login.LoginFacadeRest;
import ch.unige.pinfo.login.LoginJsonBuilder;
import ch.unige.pinfo.login.LoginService;
import ch.unige.pinfo.overview.service.OverviewFacadeRest;
import ch.unige.pinfo.user.dom.User;
import io.swagger.jaxrs.config.BeanConfig;
import ch.unige.pinfo.device.service.*;

/**
 * Hello world!
 *
 */
@ApplicationPath("/")
public class App extends Application 
{
    /*public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }*/
	
	 public App() {
		 
	       
	    }
	
	 	
	    	
}
