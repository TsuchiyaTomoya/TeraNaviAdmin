package ttc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MySqlConnectionManager{
    private static MySqlConnectionManager myInstance = null;
    private Connection connection = null;


    private MySqlConnectionManager(){}

    public static MySqlConnectionManager getInstance(){
        if(myInstance == null){
            synchronized(MySqlConnectionManager.class){
                if(myInstance == null){
                    myInstance = new MySqlConnectionManager();
                }
            }
        }
        return myInstance;
    }

	public static void destroy(){
		// Connection is Closed対策
		myInstance = null;
	}

    public Connection getConnection(){
        if(connection == null){
            try{
                InitialContext init = new InitialContext();
                DataSource source = (DataSource)init.lookup("java:comp/env/jdbc/mysql");
                connection = source.getConnection();
				connection.setAutoCommit(false);
            }catch(NamingException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
			destroy();
		}
    }

    public void beginTransaction(){

		if(connection == null){
            getConnection();
        }
        try{
            connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void commit(){
        try{
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void rollback(){
        try{
            connection.rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
