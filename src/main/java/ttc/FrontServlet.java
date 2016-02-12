package ttc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ttc.controller.ApplicationController;
import ttc.controller.WebApplicationController;
import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.exception.PresentationException;

public class FrontServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{

			req.setCharacterEncoding("UTF-8");
			try{
				ApplicationController app = new WebApplicationController();

				RequestContext reqc = app.getRequest(req);

				ResponseContext resc = null;
			
				resc = app.handleRequest(reqc);
				resc.setResponse(res);
				app.handleResponse(reqc, resc);

			}catch(PresentationException e){
				throw new ServletException(e.getMessage(),e);
			}


		}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
			doPost(req,res);
		}
}
