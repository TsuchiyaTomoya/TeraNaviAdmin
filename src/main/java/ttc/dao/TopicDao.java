package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ttc.util.MySqlConnectionManager;
import ttc.bean.Bean;
import ttc.bean.TopicBean;
import ttc.bean.UserBean;
import ttc.exception.IntegrationException;

public class TopicDao implements AbstractDao{

    public Bean read(Map map)throws IntegrationException{
        return new TopicBean();
    }

    public int update(Map map)throws IntegrationException{
        return 0;
    }

    public int insert(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        int result = 0;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            MySqlConnectionManager.getInstance().beginTransaction();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into ");
            sql.append("topics(fk_community_id,fk_create_user_id,topic_name,");
            sql.append("topic_updatetime_date,topic_created_date) ");
            sql.append("values(?,?,?,?,?)");

            pst = cn.prepareStatement( new String(sql) );

            pst.setString(1, (String)map.get("communityId"));
            pst.setString(2, (String)map.get("userId"));
            pst.setString(3, (String)map.get("topic_name"));
            pst.setString(4, (String)map.get("update_date"));
            pst.setString(5, (String)map.get("create_date"));

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
        List result = new ArrayList();
        PreparedStatement pst = null;
        String comId= null;
        comId=(String)map.get("communityId");

        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
			sql.append("select topic_id,fk_create_user_id,topic_name,topic_updatetime_date,");
			sql.append("topic_created_date,users.user_name ");
			sql.append("from topics inner join users");
			sql.append(" on topics.fk_create_user_id=users.user_id where fk_community_Id=?");

            pst = cn.prepareStatement(new String(sql));

			pst.setInt(1,Integer.parseInt(comId));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                TopicBean topics = new TopicBean();
                topics.setTopicId(rs.getString(1));
                topics.setCreateUserId(rs.getString(2));
                topics.setName(rs.getString(3));
                topics.setUpdateDate(rs.getString(4));
                topics.setCreateDate(rs.getString(5));
                topics.setCreateUserName(rs.getString(6));
                result.add(topics);
            }

        }catch(SQLException e){
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

}
