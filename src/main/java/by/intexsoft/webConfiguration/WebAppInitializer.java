package by.intexsoft.webConfiguration;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import by.intexsoft.security.WebSecurityConfiguration;
import by.intexsoft.spring.SpringConfig;

/**
 * Class registers the DispatcherServlet, in the WebApplicationInitializer.
 * loads the root context of the application. And loads the context for
 * configuring the servlet contests responsible for the authentication and
 * access rights to the {@link WebSecurityConfiguration} components. Contest
 * responsible for the database JPA Repository and service {@link SpringConfig}.
 * Loading context in the dispatcherServlet of the rest controllers
 * {@link WebConfig}.
 * 
 * @see {@link AbstractAnnotationConfigDispatcherServletInitializer},
 *      {@link DispatcherServlet}
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class, SpringConfig.class, WebSecurityConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/*" };
	}

}
