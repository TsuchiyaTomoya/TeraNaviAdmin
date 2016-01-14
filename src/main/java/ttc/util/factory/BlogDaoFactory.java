package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.BlogDao;

import ttc.exception.IntegrationException;

public class BlogDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new BlogDao();
    }
}
