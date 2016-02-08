package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.RuleDao;

import ttc.exception.IntegrationException;

public class RuleDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new RuleDao();
    }
}
