package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.BusinessLogicException;
import ttc.exception.IntegrationException;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import java.util.Map;
import java.util.HashMap;
import ttc.bean.Bean;
import java.util.List;
import java.util.ArrayList;

public class ShowCautionCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            HashMap params = new HashMap();


            MySqlConnectionManager.getInstance().beginTransaction();

            AbstractDaoFactory fact=AbstractDaoFactory.getFactory("caution");
            AbstractDao dao= fact.getAbstractDao();

            List result = dao.readAll(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();


			resc.setResult(result);

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
