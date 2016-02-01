package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.BusinessLogicException;
import ttc.exception.IntegrationException;

import java.util.Map;
import java.util.HashMap;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import java.util.Calendar;
import java.text.SimpleDateFormat;

import ttc.bean.BlogBean;

public class AdminArticlePostCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            String userId = reqc.getParameter("userId")[0];

            String title = reqc.getParameter("title")[0];

            String body = reqc.getParameter("body")[0];

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            String date = formatter.format(cal.getTime());
            System.out.println(date);

            String status = "0";

			Map params = new HashMap();
            params.put("userId",userId);
            params.put("title",title);
            params.put("body",body);
            params.put("date",date);
            params.put("status",status);

			// ブログが解説しているかどうかのチェック
			MySqlConnectionManager.getInstance().beginTransaction();

			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("blog");
			AbstractDao dao = factory.getAbstractDao();
			BlogBean blog = (BlogBean)dao.read(params);

			MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            if(blog.getStatus().equals("0")){
				throw new BusinessLogicException("ブログが開設されていません",null);
			}



            MySqlConnectionManager.getInstance().beginTransaction();

            AbstractDaoFactory factory2 = AbstractDaoFactory.getFactory("article");
            AbstractDao dao2 = factory2.getAbstractDao();
            dao2.insert(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

			resc.setResult(params);
            resc.setTarget("articlepostresult");

            return resc;

        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(), e);
        }
    }
}
