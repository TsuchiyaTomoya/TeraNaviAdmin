package ttc.util.mailer;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;
import com.sun.mail.smtp.SMTPTransport;

public class GmailMailer extends AbstractMailer{
	public String sendMail(String toAddress,String title,String mess){
		Properties prop = new Properties();
		try{
			//smtpホストサーバの指定
			prop.put("mail.smtp.host","smtp.gmail.com");
			//認証をする
			prop.put("mail.smtp.auth","true");
			//ポートの指定
			prop.put("mail.smtp.port","587");
			//STARTTLSによる暗号化の設定
			prop.put("mail.smtp.starttls.enable","true");

			Session session = Session.getInstance(prop);

			//MimeMessageの取得
			Message msg = new MimeMessage(session);

			//送信者の設定
			msg.setFrom(new InternetAddress(getAddress()));

			//宛先の設定
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));

			//ここからメッセージに関する設定

			//タイトルの設定
			msg.setSubject(title);

			//本文の設定
			msg.setText(mess);

			SMTPTransport t = (SMTPTransport)session.getTransport("smtp");
			try{
				t.connect("smtp.gmail.com",getAccount(),getPass());
				t.sendMessage(msg,msg.getAllRecipients());

			}finally{
				t.close();
			}

			return "送信成功";
		}catch(AddressException e){
			e.printStackTrace();
		}catch(MessagingException e){
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("想定外の例外です");
			e.printStackTrace();
		}

		return "failed";
	}
}
