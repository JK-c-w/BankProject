
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        String url ="jdbc:mysql://localhost:3306/bankdb";
        String user="root";
        String password="Boyfriend";

        try (Connection conn =DriverManager.getConnection(url,user,password)){
            System.out.println("Created DATABASE....");

        }
        catch(SQLException e){
            System.out.println("error in ");
            e.printStackTrace();
        }


    }
}
