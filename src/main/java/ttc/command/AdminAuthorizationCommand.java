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
import java.util.ArrayList;

public class AdminAuthorizationCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

			String[] targets = reqc.getParameter("target");

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
                params.put("adminFlag","1");
                params.put("userbean",ub);
				dao.update(params);
				users.add(ub.getUserName());
            }

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();


            Map result = new HashMap();
			result.put("list", users);
			result.put("want", "管理者権限に");
			
			resc.setResult(result);
            resc.setTarget("AccountChangeResult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
