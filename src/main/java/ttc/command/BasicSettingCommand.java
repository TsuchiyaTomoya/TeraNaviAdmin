package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.IntegrationException;
import ttc.exception.BusinessLogicException;

import java.util.Map;
import java.util.HashMap;


import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;
import ttc.bean.UserBean;

public class BasicSettingCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

			String userId = reqc.getParameter("userId")[0];
            String userName=reqc.getParameter("userName")[0];
            String nameKana=reqc.getParameter("nameKane")[0];
			String password = reqc.getParameter("password")[0];
            String sexVisibleFlag=reqc.getParameter("sexVisibleFlag")[0];
            String mailAddress=reqc.getParameter("mailAddress")[0];
            String headerPath=reqc.getParameter("headerPath")[0];
            String iconPath=reqc.getParameter("iconPath")[0];
            String profile=reqc.getParameter("profile")[0];




            Map params = new HashMap();
			params.put("value",userId);
			params.put("where","where user_id=?");

            params.put("userName",userName);
            params.put("nameKana",nameKana);
            params.put("sexVisibleFlag",sexVisibleFlag);
            params.put("mailAddress",mailAddress);
            params.put("headerPath",headerPath);
            params.put("iconPath",iconPath);
            params.put("profile",profile);
			params.put("password",password);

            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();
            UserBean ub = (UserBean)dao.read(params);

			params.put("userbean",ub);
			dao.update(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();




            resc.setTarget("SettingResult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
