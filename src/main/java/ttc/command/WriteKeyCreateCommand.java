package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.IntegrationException;
import ttc.exception.BusinessLogicException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;

import ttc.util.UniqueKeyGenerator;

import ttc.util.CSVGateway;

public class WriteKeyCreateCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            int count = Integer.parseInt(reqc.getParameter("count")[0]);
            

            List hashs = new ArrayList();
            List keys = new ArrayList();
            
            for(int i = 0;i < count;i++){
                String key = UniqueKeyGenerator.generateKeys();
                String hash = UniqueKeyGenerator.getHashCode(key);
                keys.add(key);
                hashs.add(hash);
            }
            
            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("keyCreate");
            AbstractDao dao = factory.getAbstractDao();
            
            int rCount = 0;
            Iterator itr = hashs.iterator();
            while(itr.hasNext()){
                Map param = new HashMap();
                param.put("key",itr.next());
                rCount += dao.insert(param);
            }

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();
	    
	    String fileName = UniqueKeyGenerator.generateKeys();
	    
	    CSVGateway gateway = new CSVGateway();
	    boolean flag = gateway.write(keys, fileName);

            if(flag){
                resc.setResult(fileName);
                resc.setTarget("writeSignkeyResult");
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
