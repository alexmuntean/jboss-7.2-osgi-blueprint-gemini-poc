package fi.eis.applications.jboss.poc.gemini.spring.aop.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ServiceLocator {
    private static final Map<String, Object> services = new ConcurrentHashMap<String, Object>();  
    
    private static ServiceLocator instance = new ServiceLocator();  
  
    private static Context context;  
  
    static {  
        try {  
            context = new InitialContext();  
        } catch (Exception e) {  
            throw new ExceptionInInitializerError(e);  
        }  
    }  
  
    private ServiceLocator() {}
    
    public static ServiceLocator getInstance() {
    	return instance;
    }
    public DataSource getDataSource(String name) throws IllegalArgumentException  {  
        if (name == null || name.length() <= 0)  
            throw new IllegalArgumentException("name");  
  
        if (services.containsKey(name))  
            return (DataSource) services.get(name);  
  
        DataSource ds;
		try {
			ds = (DataSource) context.lookup(name);
		} catch (NamingException e) {
			throw new IllegalArgumentException("Problem with name " + name, e);
		}  
  
        services.put(name, ds);  
        return ds;  
    }  
}
