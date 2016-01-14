package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.UsersCommunitiesDao;

import ttc.exception.IntegrationException;

public class UsersCommunitiesDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new UsersCommunitiesDao();
    }
}
