package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.BusinessLogicException;
import ttc.exception.IntegrationException;

import java.util.Map;
import java.util.HashMap;

import java.util.Calendar;
import java.text.SimpleDateFormat;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

public class DirectMessageSendCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date=sdf.format(c.getTime());

            String messageId=reqc.getParameter("messageId")[0];

            String messageBody=reqc.getParameter("messageBody")[0];

            String sendUserId = reqc.getParameter("userId")[0];

            String receiveUserId=reqc.getParameter("receiveUserId")[0];

            Map params = new HashMap();
            params.put("messageId", messageId);
            params.put("messageBody", messageBody);
            params.put("messageDate", date);
            params.put("sendUserId", sendUserId);
            params.put("receiveUserId", receiveUserId);

            MySqlConnectionManager.getInstance().beginTransaction();

            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("dm");
            AbstractDao dao = factory.getAbstractDao();
            dao.insert(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            //福田案採用果たして動くのか・・・    
            resc.setTarget("/front/dmreceive");

            return resc;

        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(), e);
        }
    }
}
