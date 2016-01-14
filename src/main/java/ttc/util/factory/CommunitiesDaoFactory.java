package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.CommunitiesDao;

import ttc.exception.IntegrationException;

public class CommunitiesDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new CommunitiesDao();
    }
}
