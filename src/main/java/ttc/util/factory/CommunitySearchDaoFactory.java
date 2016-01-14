package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao. CommunitySearchDao;

import ttc.exception.IntegrationException;

public class CommunitySearchDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new  CommunitySearchDao();
    }
}
