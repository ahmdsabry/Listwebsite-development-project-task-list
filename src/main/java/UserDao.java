 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String dbPath = "jdbc:mysql://localhost/todolist";
    private  String dbunamee = "root";
    private String dbpass = "root123";

    
    String firstname="";
    String lastname="";
    public User selectuserbyId(int id){
        User user=null;
        try (Connection con =
                     DriverManager.getConnection(dbPath, dbunamee, dbpass);
             PreparedStatement st = con.prepareStatement("SELECT * FROM notes where id=?");){
            Class.forName("com.mysql.jdbc.Driver");
            st.setInt(1, id);
            ResultSet resultSet = st
                    .executeQuery();
            if(resultSet.next()){

                String fname=resultSet.getString(2);
                String lname=resultSet.getString(3);
                String username=resultSet.getString(4);
                String pass=resultSet.getString(5);
                String phone=resultSet.getString(6);
                user=new User(fname,lname,username,pass,phone);
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;

    }

      public int insertuser(String fname,String lname,String uname,String pass,String phone) {
        System.out.println(pass);

        int rows=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =
                    DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("insert into users(fname,lname,username,pass,phone) values(?,?,?,?,?)");

            st.setString(1, fname);
            st.setString(2, lname);
            st.setString(3, uname);
            st.setString(4, pass);
            st.setString(5, phone);

            rows=st.executeUpdate();
            if (rows>0)
                System.out.println("done");
            return rows;
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
        return rows;

    }

      
      
      
      
      
      
      
      
          public int Login( String uname,String pass ) {
        System.out.println(pass);
              int userid=0;
        int rows=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =
                    DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("select * from users where username=? and pass=?");

            st.setString(1, uname);
             st.setString(2, pass);
             
             ResultSet resultSet=st.executeQuery();
             if(resultSet.next()) {
                 rows=1;
                 
                   userid=resultSet.getInt("id");
                   
                   firstname=resultSet.getString("fname");
                   lastname=resultSet.getString("lname");
                   
                   
     PreparedStatement st2 = con.prepareStatement("update notes   set user_id=?  where user_id=1000 ");
     st2.setInt(1, userid);
     int ro=st2.executeUpdate();
 
                 
                 
                             return userid;

             }
             else {
                 rows=0;
             }
 
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
        return userid;

    }
          
          
          public  String getfullname() {
              String fullname=firstname+ " " + lastname;
              return fullname;
          }
}
