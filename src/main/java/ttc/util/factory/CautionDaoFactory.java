package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;
import ttc.dao.CautionDao;

import ttc.exception.IntegrationException;

public class CautionDaoFactory extends AbstractDaoFactory{
    public AbstractDao getAbstractDao(){
        return new CautionDao();
    }
}
