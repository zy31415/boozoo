package boozoo;

import java.sql.*;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Class.forName("org.h2.Driver");

        try {

            String dbURL = "jdbc:h2:./db";
            Connection conn = DriverManager.getConnection(dbURL);

            Statement stat = conn.createStatement();

            stat.execute(
                    "create table if not exists XX (id int primary key, name varchar(255))");

            stat.execute(
                    "insert into XX values (1234, 'yang zhang');"
            );

            conn.commit();

            stat.close();

            conn.close();

            System.out.println( "Hello World!" );

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
