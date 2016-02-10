


package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.exception.IntegrationException;
import ttc.exception.BusinessLogicException;

import ttc.util.Mailler;
import ttc.util.GMailler;

import ttc.util.MySqlConnectionManager;

import java.util.Map;
import java.util.HashMap;


import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import java.util.List;

public class ReplyContactCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();

            String toAddress = reqc.getParameter("toAddress")[0];
			String title = reqc.getParameter("title")[0];
			String mess= reqc.getParameter("mess")[0];


            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("adminMail");
            AbstractDao dao = factory.getAbstractDao();

            List acount=dao.readAll(new HashMap());

            String address=(String)acount.get(0);
            String pass=(String)acount.get(1);

			Mailler mailler = new GMailler();

			mailler.setAddress(address);
			mailler.setAccount(address);
			mailler.setPass(pass);

			String result = mailler.sendMail(toAddress, title, mess);

			System.out.println(result);

			resc.setResult(result);
            resc.setTarget("replyContactResult");

			return resc;
		}catch(IntegrationException e){
				throw new BusinessLogicException(e.getMessage(),e);
		}

	}
}
