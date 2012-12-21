package fi.eis.applications.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import fi.eis.applications.beans.FooInterface;
import fi.eis.applications.jboss.poc.compositeservice.gemini.api.InformationService;
import fi.eis.applications.jboss.poc.osgiservice.api.MessageService;

/**
 * Handles requests for the application home page.
 */
@Controller
@Lazy
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private FooInterface foo;
	private InformationService informationService;
	
	//@Autowired
	public HomeController(InformationService informationServiceParam,
			FooInterface fooParam) {
		this.foo = fooParam;
		
		informationService = informationServiceParam;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("messageFromLocalBean", (foo != null ? foo.getMessage() : "empty"));
		model.addAttribute("messageFromOSGIService", (informationService != null ? informationService.getMessage() : "empty"));

		return "home";
	}
	
}
