package by.intexsoft.webConfiguration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This class will be detected by the servlet container. since implements the
 * {@link WebAppInitializer} interface. Register in the application context a
 * chain of spring filters.
 * 
 * @see {@link AbstractSecurityWebApplicationInitializer}
 */
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
}
