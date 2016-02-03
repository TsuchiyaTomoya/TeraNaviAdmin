/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import java.io.File;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import ttc.util.MySqlConnectionManager;
import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;
import ttc.bean.UserBean;
import ttc.exception.IntegrationException;

import java.util.Map;
import java.util.HashMap;


/**
 *
 * @author Masaki
 */
public class UserAddServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String path = "/tmp";
		String fileName = null;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try{
			List list = sfu.parseRequest(request);
			Iterator it = list.iterator();
			while(it.hasNext()){
				FileItem item = (FileItem)it.next();
				
				if(!item.isFormField()){
					fileName = item.getName();
					
					if((fileName != null) && (!fileName.equals(""))){
						fileName = (new File(fileName)).getName();
						
						item.write(new File(path+"//"+fileName));
						
					}
				}
			}
		}catch(FileUploadException e){
			throw new IOException(e.getMessage(),e);
		}catch(Exception e){
			throw new IOException(e.getMessage(),e);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path + "/" + fileName)),"Shift_JIS"));
		
		String line = null;
		
		List<String[]> users = new ArrayList<String[]>();
		
		br.readLine();
		while((line = br.readLine())!=null){
			String[] cols = line.split(",");
			users.add(cols);
		}
		
		File df = new File(path + "/" + fileName);
		df.delete();
		
		try{
			MySqlConnectionManager.getInstance().beginTransaction();
			AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory("users");
			AbstractDao dao = daoFactory.getAbstractDao();

			for(String[] user : users){
				Map params = new HashMap();

				params.put("loginId",user[0]);
				params.put("password", user[1]);
				params.put("userName",user[2]);
				params.put("nameKana",user[3]);
				params.put("sex",user[4]);
				String sexVisibleFlag = null;
				if(user[5].equals("表示")){
					sexVisibleFlag = "0";
				}else{
					sexVisibleFlag = "1";
				}
				
				params.put("sexVisibleFlag",sexVisibleFlag);
				
				
				String[] date = user[6].split("/");
				StringBuilder bDate = new StringBuilder();
				bDate.append(date[0]);
				
				if(date[1].length()==1){
					bDate.append("0");
				}
				bDate.append(date[1]);
				
				if(date[2].length()==1){
					bDate.append("0");
				}
				bDate.append(date[2]);
				
				params.put("birthDate",new String(bDate));
				params.put("mailAddress",user[7]);
				params.put("quepstionId",user[8]);
				params.put("secretAnswer",user[9]);
				params.put("adminFlag","0");

				dao.insert(params);
			}
			
			MySqlConnectionManager.getInstance().commit();
			MySqlConnectionManager.getInstance().closeConnection();
			
			request.setAttribute("result", users);
			
		}catch(IntegrationException e){
			throw new IOException(e.getMessage(),e);
		}catch(Exception e){
			throw new IOException(e.getMessage(),e);
		}
		
		
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/csvAddResult.jsp");
		dis.forward(request, response);
		
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
