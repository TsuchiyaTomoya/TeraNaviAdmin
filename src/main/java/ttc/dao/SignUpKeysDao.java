package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ttc.bean.Bean;


import ttc.exception.IntegrationException;

import ttc.util.MySqlConnectionManager;

public class SignUpKeysDao implements AbstractDao{
    public int insert(Map map)throws IntegrationException{
        Connection cn = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into sign_up_keys ");
            sql.append("values(?,0)");
            
            pst = cn.prepareStatement(new String(sql));
            
            pst.setString(1,(String)map.get("key"));
            
            result = pst.executeUpdate();
        }catch(SQLException e){
            MySqlConnectionManager.getInstance().rollback();
            throw new IntegrationException(e.getMessage(),e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
            }catch(SQLException e){
                throw new IntegrationException(e.getMessage(),e);
            }
        }

        return result;
        
    }
    public int update(Map map)throws IntegrationException{
        return 0;
    }
    public Bean read(Map map)throws IntegrationException{
        return null;
    }

    public List readAll(Map map)throws IntegrationException{
        return null;
    }
}
