package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ttc.util.MySqlConnectionManager;
import ttc.bean.Bean;
import ttc.bean.CommunityBean;
import ttc.exception.IntegrationException;

public class UsersCommunitiesDao implements AbstractDao{

    public Bean read(Map map)throws IntegrationException{
        return null;
    }

    public int update(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        int result = 0;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("delete from community_members_list where");
            sql.append("fk_user_id=? and fk_community_id=?");

            pst = cn.prepareStatement(new String(sql));

            pst.setString(1,(String)map.get("userId"));
            pst.setString(2,(String)map.get("commId"));


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

    public int insert(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        int result = 0;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into community_members_list values(");
            sql.append("?,?,?)");

            pst = cn.prepareStatement(new String(sql));

            pst.setString(1,(String)map.get("userId"));
            pst.setString(2,(String)map.get("commId"));
            pst.setString(3,"0");


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

    public List readAll(Map map)throws IntegrationException{
        return null;
    }

}
