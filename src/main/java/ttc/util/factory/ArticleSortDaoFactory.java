package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.ArticleSortDao;

import ttc.exception.IntegrationException;

public class ArticleSortDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new ArticleSortDao();
    }
}
