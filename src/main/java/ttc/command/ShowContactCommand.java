package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.util.MySqlConnectionManager;

import ttc.exception.IntegrationException;
import ttc.exception.BusinessLogicException;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


import ttc.util.factory.AbstractDaoFactory;
import ttc.dao.AbstractDao;
import ttc.bean.ContactBean;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class ShowContactCommand extends AbstractCommand{


    public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
        try{

            RequestContext reqc = getRequestContext();

            String conId=reqc.getParameter("conId")[0];



            MySqlConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory("contact");
            AbstractDao dao = factory.getAbstractDao();

            Map params = new HashMap();
            params.put("conId",conId);

            ContactBean bean=(ContactBean)dao.read(params);

            MySqlConnectionManager.getInstance().commit();
            MySqlConnectionManager.getInstance().closeConnection();

            resc.setResult(bean);
            resc.setTarget("cotactResult");

            return resc;
        }catch(IntegrationException e){
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}
