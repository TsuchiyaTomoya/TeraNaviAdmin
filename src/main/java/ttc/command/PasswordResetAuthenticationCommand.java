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

public class PasswordResetAuthenticationCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            String loginId=reqc.getParameter("loginId")[0];
			String questionNo = reqc.getParameter("questionNo")[0];
			String questionAnswer = reqc.getParameter("questionAnswer")[0];



            Map params = new HashMap();
            params.put("value",loginId);
            params.put("where","where login_id=?");

            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
            AbstractDao dao = factory.getAbstractDao();

			UserBean ub = (UserBean)dao.read(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

			System.out.println(questionNo.equals(ub.getQuestionNo()));

			if(questionNo.equals(ub.getQuestionNo()) && questionAnswer.equals(ub.getSecretAnswer())){
				System.out.println(ub.getLoginId());
				resc.setResult(ub);
				resc.setTarget("passResetPage");

	            return resc;
			}else{
				throw new BusinessLogicException("入力内容が違います",null);
			}



        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
