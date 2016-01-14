package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.UsersDao;


import ttc.exception.IntegrationException;

public class UsersDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new UsersDao();
    }
}
