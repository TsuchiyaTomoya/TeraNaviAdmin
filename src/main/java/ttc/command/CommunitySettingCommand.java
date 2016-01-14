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

public class CommunitySettingCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{
            RequestContext reqc = getRequestContext();

            String communityName = reqc.getParameter("communityName")[0];
            String communityProfile = reqc.getParameter("communityProfile")[0];
            String iconPath = reqc.getParameter("iconPath")[0];
            String headerPath = reqc.getParameter("headerPath")[0];
            String deleteFlag = reqc.getParameter("deleteFlag")[0];
            String communityId = reqc.getParameter("communityId")[0];

            HashMap params = new HashMap();
            params.put("communityName",communityName);
            params.put("communityProfile",communityProfile);
            params.put("iconPath",iconPath);
            params.put("headerPath",headerPath);
            params.put("deleteFlag",deleteFlag);
            params.put("communityId",communityId);

            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("community");
            AbstractDao dao = factory.getAbstractDao();
            dao.update(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            resc.setResult(params);
            resc.setTarget("communitySettingResult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
