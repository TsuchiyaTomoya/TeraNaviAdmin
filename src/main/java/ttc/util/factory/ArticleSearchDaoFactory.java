package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.ArticleSearchDao;

import ttc.exception.IntegrationException;

public class ArticleSearchDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new ArticleSearchDao();
    }
}
