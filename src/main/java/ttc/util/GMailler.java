/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttc.util;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;
import com.sun.mail.smtp.SMTPTransport;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;

import ttc.exception.BusinessLogicException;


import java.util.Date;
/**
 *
 * @author Masaki
 */
public class GMailler extends Mailler{
	public String sendMail(String toAddress,String title,String mess)throws BusinessLogicException{
		Properties prop = new Properties();
		try{
			String host = "smtp.gmail.com";
			
			String fromName = "TeraNavi";
			
			
			prop.setProperty("mail.transport.protocol","smtps");
			//認証をする
			
			prop.setProperty("mail.smtp.auth","true");
			//ポートの指定
			prop.setProperty("mail.smtp.port","465");
			//STARTTLSによる暗号化の設定
			prop.setProperty("mail.smtp.starttls.enable","true");
			
			prop.setProperty("mail.smtp.starttls.enable", "true");
			prop.setProperty("mail.smtp.starttls.required", "true");
			prop.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			prop.setProperty("mail.smtp.socketFactory.fallback","true");
			
			Session session = Session.getInstance(prop);
			
			MimeMessage msg = new MimeMessage(session);
			
			msg.setSubject(MimeUtility.encodeText(title,"iso-2022-jp","B"),"iso-2022-jp");
			msg.setSentDate(new Date());
			
			InternetAddress[] address = null;
			address = new InternetAddress[1];
			address[0] = new InternetAddress(getAddress());
			
			address[0].setPersonal(MimeUtility.encodeText(fromName,"iso-2022-jp","B"));
			
			msg.setFrom(address[0]);
			
			address[0] = new InternetAddress(toAddress);
			
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
			
			
//			
//			マルチパートのメッセージを作成
//			
			MimeMultipart multiPart = new MimeMultipart();
			msg.setContent(multiPart);
			
			//本文
			MimeBodyPart msgBody = new MimeBodyPart();
			msgBody.setText(mess,"iso-2022-jp");
			msgBody.setHeader("Content-Transfer-Encoding","7bit");
			multiPart.addBodyPart(msgBody);
			
			Transport trans = session.getTransport("smtps");
			
			
			try{
				trans.connect(host,getAccount(),getPass());
				trans.sendMessage(msg,msg.getAllRecipients());
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				trans.close();
			}
			
			return "OK";
		}catch(AddressException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}catch(MessagingException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}catch(UnsupportedEncodingException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}catch(Exception e){
			throw new BusinessLogicException(e.getMessage(),e);
			
		}
		
		
	}
}
