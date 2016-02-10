package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.ContactDao;

import ttc.exception.IntegrationException;

public class ContactDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new ContactDao();
    }
}
