package ttc.util.factory;

import java.util.Properties;
import java.io.IOException;

import ttc.dao.AbstractDao;

import ttc.exception.IntegrationException;

public abstract class AbstractDaoFactory{
    public static AbstractDaoFactory getFactory(String key)throws IntegrationException{
        AbstractDaoFactory factory = null;
        Properties prop = new Properties();

        try{
            prop.load(AbstractDaoFactory.class.getClassLoader().getResourceAsStream("daofactory.properties"));
            String className = prop.getProperty(key);

            Class c = Class.forName(className);
            factory=(AbstractDaoFactory)c.newInstance();

        }catch(ClassNotFoundException e){
            throw new IntegrationException(e.getMessage(),e);
        }catch(IOException e){
            throw new IntegrationException(e.getMessage(),e);
        }catch(InstantiationException e){
            throw new IntegrationException(e.getMessage(),e);
        }catch(IllegalAccessException e){
            throw new IntegrationException(e.getMessage(),e);
        }catch(Exception e){
            throw new IntegrationException(e.getMessage(),e);
        }

        return factory;
    }

    public abstract AbstractDao getAbstractDao();
}
