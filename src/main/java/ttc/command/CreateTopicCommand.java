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

public class CreateTopicCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            String communityId = reqc.getParameter("communityId")[0];
            String userId = reqc.getParameter("userId")[0];
            String topic_name = reqc.getParameter("topic_name")[0];
            String update_date = reqc.getParameter("update_date")[0];
            String create_date = reqc.getParameter("create_date")[0];

            Map params = new HashMap();
            params.put("communityId",communityId);
            params.put("userId",userId);
            params.put("topic_name",topic_name);
            params.put("update_date",update_date);
            params.put("create_date",create_date);

            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("topic");
            AbstractDao dao = factory.getAbstractDao();
            dao.insert(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            resc.setTarget("topic");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
