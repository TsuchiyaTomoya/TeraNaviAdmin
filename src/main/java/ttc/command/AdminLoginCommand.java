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

public class AdminLoginCommand extends AbstractCommand {


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            String loginId=reqc.getParameter("loginId")[0];
            String password=reqc.getParameter("password")[0];



            Map params = new HashMap();
            params.put("value",loginId);
            params.put("where","where login_id=?");


            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();
            UserBean ub = (UserBean)dao.read(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();



            String flag="1";

            if(flag.equals(ub.getAdminFlag())){
                if(password.equals(ub.getPassword())){
                    ub.setPassword("dummy");
                    ub.setSecretAnswer("dummy");
                    resc.setResult(ub);
                    resc.setTarget("adminLoginResult");

                    return resc;
                }else{
                    throw new BusinessLogicException("パスワードが違います",null);
                }
            }else{
                throw new BusinessLogicException("管理者ではありません",null);
            }



        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
