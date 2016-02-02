package ttc.command;

import java.util.ArrayList;
import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.BusinessLogicException;
import ttc.exception.IntegrationException;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import ttc.bean.UserBean;

public class AccountDeleteCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            String[] targets = reqc.getParameter("target");
            String status="3";

            MySqlConnectionManager.getInstance().beginTransaction();

            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();
			
			List users = new ArrayList();
			
			for(int i = 0;i < targets.length;i++){
                Map params = new HashMap();
                params.put("value",targets[i]);
                params.put("where","where user_id=?");
                
                UserBean ub = (UserBean)dao.read(params);
                
				params.put("userId",targets[i]);
                params.put("userbean",ub);
                params.put("userStatus",status);
				dao.update(params);
				
				users.add(ub.getUserName());
            }
			
            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

			Map result = new HashMap();
			result.put("list", users);
			result.put("want", "削除");
			
			resc.setResult(result);
            resc.setTarget("AccountChangeResult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
