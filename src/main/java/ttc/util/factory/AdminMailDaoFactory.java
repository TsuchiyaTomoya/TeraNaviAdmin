package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.AdminMailDao;

import ttc.exception.IntegrationException;

public class AdminMailDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new AdminMailDao();
    }
}
