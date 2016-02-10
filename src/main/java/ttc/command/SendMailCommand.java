
//メールの送信テスト用のコマンド　本番環境では使わない可能性あり


package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.exception.IntegrationException;
import ttc.exception.BusinessLogicException;

import ttc.util.Mailler;
import ttc.util.GMailler;

public class SendMailCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();

			String address = reqc.getParameter("address")[0];
			String toAddress = reqc.getParameter("toAddress")[0];
			String pass = reqc.getParameter("pass")[0];
			String title = reqc.getParameter("title")[0];
			String mess= reqc.getParameter("mess")[0];
			
			Mailler mailler = new GMailler();
			
			mailler.setAddress(address);
			mailler.setAccount(address);
			mailler.setPass(pass);
			
			String result = mailler.sendMail(toAddress, title, mess);
			
			System.out.println(result);
			
			resc.setResult(result);

			return resc;
		}catch(IntegrationException e){
				throw new BusinessLogicException(e.getMessage(),e);
		}

	}
}
