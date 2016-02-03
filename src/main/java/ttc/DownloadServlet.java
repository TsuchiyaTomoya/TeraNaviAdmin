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

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;

import java.io.File;


/**
 *
 * @author Masaki
 */
public class DownloadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		doPost(req,res);
    }
    
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		String fileName = req.getParameter("fileName");
	
		OutputStream out = null;
		InputStream in = null;
		
		String fPath = "/tmp/"+fileName+".csv";
		
		try{
		    res.setContentType("application/octet-stream");
			res.setHeader( "Content-Disposition","filename=\"SignupKeys.csv\"");
			
			
			in = new FileInputStream(fPath);
			out = res.getOutputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff, 0, buff.length)) != -1) {
				out.write(buff, 0, len);
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		
		File f = new File(fPath);
		f.delete();
    }

}
