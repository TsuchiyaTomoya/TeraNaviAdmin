package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.BusinessLogicException;
import ttc.exception.IntegrationException;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import java.util.Map;
import java.util.HashMap;

public class AccountDeleteCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            String userId = reqc.getParameter("userId")[0];
            String status="3";

            Map params = new HashMap();
            params.put("userId",userId);
            params.put("userStatus",status);
            MySqlConnectionManager.getInstance().beginTransaction();

            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("user");
            AbstractDao dao = factory.getAbstractDao();
            dao.update(params);
            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();


            resc.setTarget("accountdeleteresult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
