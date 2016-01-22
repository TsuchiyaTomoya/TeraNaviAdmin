package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.IntegrationException;
import ttc.exception.BusinessLogicException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;


import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;
import ttc.bean.UserBean;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class AdminAuthorizationCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

			String targetId = reqc.getParameter("targetId")[0];
            
            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();
            
            Map params = new HashMap();
            params.put("userId",targetId);
            params.put("adminFlag","1");

            dao.update(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            
            resc.setTarget("AccountLockResult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
