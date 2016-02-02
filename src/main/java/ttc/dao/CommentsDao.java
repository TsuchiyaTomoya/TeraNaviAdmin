package ttc.dao;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ttc.bean.Bean;
import ttc.bean.CommentBean;

import ttc.exception.IntegrationException;

import ttc.util.MySqlConnectionManager;

public class CommentsDao implements AbstractDao{
    public int insert(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        int result=0;
        try{
            Connection cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into comments(fk_article_id,");
            sql.append("comment_date,fk_user_id,comment_body,comment_delete_flag) values(?,?,?,?,?)");
            pst = cn.prepareStatement(new String(sql));

            pst.setString(1,(String)map.get("articleId"));
            pst.setString(2,(String)map.get("date"));
            pst.setString(3,(String)map.get("userId"));
            pst.setString(4,(String)map.get("body"));
            pst.setString(5,(String)map.get("status"));

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
        PreparedStatement pst = null;
        int result = 0;
        try{
            Connection cn = MySqlConnectionManager.getInstance().getConnection();
            String sql = "update comments set comment_delete_flag=1 where comment_id=?";
            pst = cn.prepareStatement(sql);
            pst.setString(1,(String)map.get("commentId"));
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

    public Bean read(Map map)throws IntegrationException{
        return null;
    }
}
