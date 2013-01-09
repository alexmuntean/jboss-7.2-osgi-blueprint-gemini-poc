package fi.eis.applications.controllers;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import fi.eis.applications.beans.FooInterfaceThrowingMyException;
import fi.op.jopo.aspect.JopoExceptionHandler;

/**
 * Handles requests for the application home page.
 */
@Controller
@Lazy
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private FooInterfaceThrowingMyException foo;
	private DataSource exampleDS;
	
	@Autowired
	public HomeController(FooInterfaceThrowingMyException fooParam, DataSource exampleDataSource) {
		this.foo = fooParam;
		this.exampleDS = exampleDataSource;
		logger.info("JopoExceptionHandler is actually " + JopoExceptionHandler.class
				+ " with class loader " + JopoExceptionHandler.class.getClassLoader());
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			@RequestParam(value = "exceptiontrigger", required = false)
			String exceptionTrigger ) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("messageFromLocalBean", (foo != null ? foo.getMessage() : "empty"));
		
		if (exceptionTrigger != null) {
			try {
				this.exampleDS.getConnection("ping", "pong");
			} catch (SQLException e) {
				foo.throwException(new IllegalStateException(e));
			}
			foo.throwException(); // unless the above didn't do it
		}

		return "home";
	}
	
	
}
