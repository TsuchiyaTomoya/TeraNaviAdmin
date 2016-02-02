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

public class AccountLockCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

			String[] targets = reqc.getParameter("target");
            String[] endDate = reqc.getParameter("lockEnd");
            String[] status = reqc.getParameter("status");
            
            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String now = formatter.format(cal.getTime());
            
			List users = new ArrayList();
			
            for(int i = 0;i < targets.length;i++){
                Map params = new HashMap();
                params.put("value",targets[i]);
                params.put("where","where user_id=?");
                
                UserBean ub = (UserBean)dao.read(params);
                
				params.put("userId",targets[i]);
                params.put("lockStartDate",now);
                params.put("lockEndDate",endDate[i]);
                params.put("userbean",ub);
                params.put("userStatus",status[i]);
				
				dao.update(params);
				users.add(ub.getUserName());
			}			

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            Map result = new HashMap();
			result.put("list", users);
			result.put("want", "ロック");
			
			resc.setResult(result);
            resc.setTarget("AccountChangeResult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
