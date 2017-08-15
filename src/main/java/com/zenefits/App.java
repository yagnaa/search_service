package com.zenefits;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.filter.LoggingFilter;

import com.zenefits.resources.AutoSuggestResource;
import com.zenefits.service.AutoSuggestService;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<AppConfiguration>
{
	public static void main(final String[] args) throws Exception {
		new App().run(args);
	}
	
	@Override
	public String getName() {
		return "search";
	}
    
    @Override
	public void run(final AppConfiguration configuration, final Environment environment) throws FileNotFoundException, IOException {
    	
    	Properties prop = new Properties();
		String filename = "config.properties";
		prop.load(new FileInputStream(filename));		
		AutoSuggestService autoSuggestService = new AutoSuggestService();
		AutoSuggestResource autoSuggestResource = new AutoSuggestResource(autoSuggestService);
		

		
//		 // Enable CORS headers
//	    final FilterRegistration.Dynamic cors =
//	        environment.servlets().addFilter("CORS", CrossOriginFilter.class);
//
//	    // Configure CORS parameters
//	    cors.setInitParameter("allowedOrigins", "*");
//	    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
//	    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
//
//	    // Add URL mapping
//	    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//
//	    // DO NOT pass a preflight request to down-stream auth filters
//	    // unauthenticated preflight requests should be permitted by spec
//	    cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());
		
		environment.jersey().register(autoSuggestResource);
		
		environment.jersey().register(new LoggingFilter(Logger.getLogger("InboundRequestResponse"), true));
	}
}
