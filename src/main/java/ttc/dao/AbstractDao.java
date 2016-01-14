package ttc.dao;

import java.util.List;
import java.util.Map;

import ttc.exception.IntegrationException;

import ttc.bean.Bean;

public interface AbstractDao{
    public Bean read(Map map)throws IntegrationException;
    public int update(Map map)throws IntegrationException;
    public int insert(Map map)throws IntegrationException;
    public List readAll(Map map)throws IntegrationException;
}
