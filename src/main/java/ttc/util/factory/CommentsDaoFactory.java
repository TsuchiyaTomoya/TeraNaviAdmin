package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.CommentsDao;

import ttc.exception.IntegrationException;

public class CommentsDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new CommentsDao();
    }
}
