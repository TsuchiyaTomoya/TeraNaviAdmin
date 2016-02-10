package ttc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpSession;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;
import ttc.context.WebRequestContext;
import ttc.context.WebResponseContext;
import ttc.command.AbstractCommand;
import ttc.util.CommandFactory;
import ttc.exception.PresentationException;

import com.google.gson.Gson;

import java.io.PrintWriter;

public class WebApplicationController implements ApplicationController{

	public RequestContext getRequest(Object request)throws PresentationException{

		RequestContext reqc = new WebRequestContext();

		reqc.setRequest(request);

		return reqc;
	}

	public ResponseContext handleRequest(RequestContext reqc)throws PresentationException{

		AbstractCommand command = CommandFactory.getCommand(reqc);
		command.init(reqc);

		ResponseContext resc = command.execute( new WebResponseContext() );

		return resc;
	}

	public void handleResponse(RequestContext reqc, ResponseContext resc)
        throws PresentationException
    {

		HttpServletRequest req = (HttpServletRequest) reqc.getRequest();
		HttpServletResponse res = (HttpServletResponse) resc.getResponse();

		String path = reqc.getCommandPath();

		boolean flag = false;
		
		try{
			flag = req.getParameter("ajax").equals("true");
		}catch(Exception e){
			
		}
		
		if(flag){
			try{
				res.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.print(new Gson().toJson(resc.getResult()));
			}catch(IOException e){
				throw new PresentationException((e.getMessage()), e);
			}
		}else{

			if(path.equals("login") || path.equals("signup") || path.equals("basic") || path.equals("createcomm")){
				HttpSession session = req.getSession(true);
				session.setAttribute("loginUser",resc.getResult());
			}else if(path.equals("logout")){
				HttpSession session = req.getSession(true);
				session.removeAttribute("loginUser");

			}else{

				req.setAttribute("result",resc.getResult());

			}

			RequestDispatcher rd = req.getRequestDispatcher(resc.getTarget());

			try{
				rd.forward(req,res);
			} catch(ServletException e){
				throw new PresentationException(e.getMessage(), e);
			} catch(IOException e){
				throw new PresentationException(e.getMessage(), e);
			}
		}
	}
}
