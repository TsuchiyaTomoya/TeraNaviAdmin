package ttc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import java.io.File;

import java.util.List;
import java.util.Iterator;

public class ImageUploadServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{

		req.setCharacterEncoding("utf-8");

		String path = getServletContext().getRealPath("WEB-INF/img");

		String resultPath = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try{
			List list = sfu.parseRequest(req);
			Iterator it = list.iterator();
			while(it.hasNext()){
				FileItem item = (FileItem)it.next();

				if(!item.isFormField()){
					String fileName = item.getName();
					if((fileName!=null)&&(!fileName.equals(""))){
						fileName = (new File(fileName)).getName();

						item.write(new File(path+"/"+fileName));

						resultPath="<img src='/WEB-INF/img/"+fileName+"'>";
					}
				}

			}
		}catch(FileUploadException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}


		String responseJson = "{\"result\":\""+resultPath+"\"}";
		res.setContentType("application/json;charset=UTF-8");

		PrintWriter writer = res.getWriter();
		writer.print(responseJson);

		writer.flush();
		writer.close();
	}

	private String getFilename(Part part){
		for(String cd : part.getHeader("Content-Disposition").split(";")){
			if(cd.trim().startsWith("filename")){
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"","");
			}
		}

		return null;
	}
}
