/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttc.util;


import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Iterator;


/**
 *
 * @author Masaki
 */
public class CSVGateway {
    public boolean write(List data,String fileName){
        try{
            StringBuffer sb = new StringBuffer();
	    
	    Iterator itr = data.iterator();
	    
	    int i = 0;
	    while(itr.hasNext()){
		String str = (String)itr.next();
		sb.append(++i);
		sb.append(",");
		sb.append(str);
		sb.append("\r\n");
	    }
	    
            File file = new File("/tmp/"+fileName+".csv");
            FileOutputStream fos = new FileOutputStream(file,true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            PrintWriter pw = new PrintWriter(osw);
            // ファイルへの書き込み
            pw.print(new String(sb));
            // 後始末
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
