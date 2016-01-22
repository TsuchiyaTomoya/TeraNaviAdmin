package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.IntegrationException;
import ttc.exception.BusinessLogicException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import ttc.util.UniqueKeyGenerator;

public class KeyCreateCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

			int count = Integer.parseInt(reqc.getParameter("count")[0]);
            
            List keys = UniqueKeyGenerator.generateKeys(count);
            
            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("keyCreate");
            AbstractDao dao = factory.getAbstractDao();
            
            int rCount = 0;
            Iterator itr = keys.iterator();
            while(itr.hasNext()){
                Map param = new HashMap();
                param.put("key",itr.next());
                rCount += dao.insert(param);
            }

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            if(count == rCount){
                resc.setResult(keys);
                resc.setTarget("signkeyResult");
            }else{
                Map errResult = new HashMap();
                errResult.put("count",count-rCount);
                errResult.put("keys",keys);
                
                resc.setResult(errResult);
                resc.setTarget("errSignKeyResult");
            }
            

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
