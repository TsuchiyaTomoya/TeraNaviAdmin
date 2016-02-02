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

public class KeywordSearchCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();

			String keyword = reqc.getParameter("keyword")[0];
			String[] keywords = null;
			keywords=keyword.split(" ", 0);

			Map params = new HashMap();

			params.put("keywords",keywords);

			MySqlConnectionManager.getInstance().beginTransaction();
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("keywordsearch");
			AbstractDao dao = factory.getAbstractDao();

			List result = dao.readAll(params);

			MySqlConnectionManager.getInstance().commit();
			MySqlConnectionManager.getInstance().closeConnection();

			resc.setResult(result);
			resc.setTarget("searchresult");

			return resc;
		}catch(IntegrationException e){
				throw new BusinessLogicException(e.getMessage(),e);
		}

	}
}
