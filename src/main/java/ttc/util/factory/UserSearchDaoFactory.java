package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.UserSearchDao;

import ttc.exception.IntegrationException;

public class UserSearchDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new UserSearchDao();
    }
}
