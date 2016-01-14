package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ttc.bean.CommunityBean;
import ttc.bean.Bean;

import ttc.exception.IntegrationException;

import ttc.util.MySqlConnectionManager;

public class CommunitySearchDao implements AbstractDao{
    public int insert(Map map)throws IntegrationException{
        return 0;
    }
    public int update(Map map)throws IntegrationException{
        return 0;
    }
    public Bean read(Map map)throws IntegrationException{
        return null;
    }

    public List readAll(Map map)throws IntegrationException{
        List result = new ArrayList();
        PreparedStatement pst = null;
        String[] keywords = null;
        for(int i=0;i<map.size();i++){
            keywords[i]=(String)map.get("keyword"+i);
        }

        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("select community_id,community_name,community_profile,community_icon_path,");
            sql.append("ommunity_header_photo_path,community_created_date ");
            sql.append("from communities where blog_title like '%?%'");
            for(int i=1;i<keywords.length;i++){
                sql.append(" and");
                sql.append(" blog_title like ");
                sql.append("'%");
                sql.append("?");
                sql.append("%'");
            }
            sql.append(" and community_delete_flag=0");

            for(int i=0;i<keywords.length;i++){
                pst.setString(i+1,keywords[i]);
            }

            pst = cn.prepareStatement(new String(sql));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                CommunityBean comm = new CommunityBean();
                comm.setId(rs.getString(1));
                comm.setName(rs.getString(2));
                comm.setProfile(rs.getString(3));
                comm.setIconPath(rs.getString(4));
                comm.setHeaderPath(rs.getString(5));
                comm.setCreatedDate(rs.getString(6));
                result.add(comm);
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
