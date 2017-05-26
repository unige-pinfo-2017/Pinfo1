package ch.unige.pinfo.ideas;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import io.swagger.jaxrs.config.BeanConfig;

public class Bootstrap extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5586530707000587337L;

	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/IDEAS");
        beanConfig.setResourcePackage("ch.unige.pinfo.login,ch.unige.pinfo.overview.service,ch.unige.pinfo.table");
        beanConfig.setTitle("IDEAS API");
        beanConfig.setScan(true);  
    }
}
