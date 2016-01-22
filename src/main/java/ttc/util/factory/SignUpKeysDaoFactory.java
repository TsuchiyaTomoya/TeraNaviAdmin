package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.SignUpKeysDao;

import ttc.exception.IntegrationException;

public class SignUpKeysDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new SignUpKeysDao();
    }
}
