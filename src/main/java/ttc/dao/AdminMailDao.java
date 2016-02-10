package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import ttc.util.MySqlConnectionManager;
import ttc.bean.Bean;
import ttc.bean.ArticleBean;
import ttc.bean.UserBean;
import ttc.bean.BlogBean;
import ttc.bean.TagBean;
import ttc.bean.CommentBean;
import ttc.exception.IntegrationException;

public class AdminMailDao implements AbstractDao{

    public Bean read(Map map)throws IntegrationException{

        return null;
    }

    public int update(Map map)throws IntegrationException{

        return 0;
    }

    public int insert(Map map)throws IntegrationException{
        return 0;
    }

    public List readAll(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        List results = new ArrayList();
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            MySqlConnectionManager.getInstance().beginTransaction();
            String sql="SELECT * from admin_mail_acount";

            pst = cn.prepareStatement( sql );

            ResultSet rs = pst.executeQuery();

            rs.next();
            results.add(rs.getString(1));
            results.add(rs.getString(2));




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

        return results;

    }
}
