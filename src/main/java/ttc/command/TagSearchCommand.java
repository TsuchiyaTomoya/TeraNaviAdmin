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

public class TagSearchCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();
			String[] tags=null;
			String tag = reqc.getParameter("tag")[0];
			tags=tag.split(" ", 0);

			Map params = new HashMap();

			for(int i=0;i<tags.length;i++){
				params.put("tag"+i,tags[i]);
			}

			MySqlConnectionManager.getInstance().beginTransaction();
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("tagsearch");
			AbstractDao dao = factory.getAbstractDao();

			MySqlConnectionManager.getInstance().commit();
			MySqlConnectionManager.getInstance().closeConnection();

			resc.setTarget("tagsearch_show");
			resc.setResult(dao.readAll(params));

			return resc;
		}catch(IntegrationException e){
				throw new BusinessLogicException(e.getMessage(),e);
		}

	}
}
