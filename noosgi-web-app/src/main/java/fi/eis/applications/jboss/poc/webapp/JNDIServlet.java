package fi.eis.applications.jboss.poc.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.CannotProceedException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.NoPermissionException;
import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JNDIServlet
 */
@WebServlet("/JNDIServlet")
public class JNDIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		writer.print("<html>");
		writer.print("<body>");
		writer.print("<table border=1>");
		
		Map<String,String> names = new HashMap<String,String>();
	    try {
			//traverse(new InitialContext(), "", names);
	    	//Context c = (Context)new InitialContext().lookup("java:comp/env");
	    	Context c = new InitialContext();
			listContext(c, "", names);
		    for (String name: names.keySet())
		    {
		    	writer.print("<tr><td>");
		    	writer.print(name);
		    	writer.print("</td><td>");
		    	writer.print(names.get(name));
		    	writer.print("</td></tr>");
		    }
		} catch (NamingException e) {
			throw new ServletException(e);
		}
		writer.print("</table>");
		writer.print("</body>");
		writer.print("</html>");
		
	}

	private static final void listContext(Context ctx, String indent,
			Map<String, String> names) {
		try {
		   NamingEnumeration<Binding> list = ctx.listBindings("");
		   while (list.hasMore()) {
		       Binding item = (Binding) list.next();
		       String className = item.getClassName();
		       String name = item.getName();
		       names.put(indent + className , name);
		       Object o = item.getObject();
		       if (o instanceof javax.naming.Context) {
		    	   listContext((Context) o, indent + " ", names);
		       }
		   }
		} catch (NamingException ex) {
			names.put(indent, "<font color=red>" + ex + "</font>");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private static void traverse(Context ctx, String root, Map<String,String> names)
	{
	    try
	    {
	        NamingEnumeration<NameClassPair> nameClassPairs = ctx.list(root);
	        
	        for (NameClassPair nameClassPair : Collections.list(nameClassPairs))
	        {
	        	System.out.println("Found root " + root);
	        	System.out.println("Found name " + nameClassPair.getName());
	            String name = root + (root.length() > 0 ? "." : "") + nameClassPair.getName();
	            try
	            {
		            Object jndiObject = ctx.lookup(name);
		            names.put(name, jndiObject.toString());
	            }
	            catch (CommunicationException ce)
	            {
		            names.put(name, "<font color=red><b>" + ce + "</b></font>");
	            }
	            catch (ServiceUnavailableException ce)
	            {
		            names.put(name, "<font color=red><b>" + ce + "</b></font>");
		        }
	            catch (NamingException ce)
	            {
		            names.put(name, "<font color=red><b>" + ce + "</b></font>");
		        }
	 
	            traverse(ctx, name, names);
	            System.out.println("Going for next");
	        }
	    }
	    catch (CannotProceedException cpe)
	    {
	        //names.add(root + ": " + cpe);
	    }
	    catch (NoPermissionException npe)
	    {
	        names.put(root, "<font color=red>" + npe + "</font>");
	    }
	    catch (NamingException ne)
	    {
	        names.put(root, "<font color=red>" + ne + "</font>");
	    }
	}
}
