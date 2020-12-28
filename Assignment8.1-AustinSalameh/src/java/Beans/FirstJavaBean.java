/*

Austin Salameh
Assignment 8.1

Create a new Web application titled <yourname>Week8. Next, create a JSP that displays a form when the doGet method is invoked. 
The form will contain a post action that directs the form post back to the same JSP, which in the doPost method will save the form data to a database using a Java Bean and a Custom Tag. 
Use your Oracle account to make the DB connection. After the form data has been saved to the database, respond back with a query from the database displaying all 
the current records contained in the database, in an appealing format. The form must contain a minimum of three input fields. The grade for this assignment 
will be based both on the functionality of the servlet and the appearance of the form post results. Name your JSP <yourName>FormPost5 and name the application <yourname>Week8. 
Create a Web archive file and attach to this assignment. Do not copy (cut and paste) any example code, create your own code and use the examples as a guide.
*/
package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstJavaBean implements Serializable {
    
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private String fname;
    private String lname;
    private String phone;
    private String height;
    private String weight;
    private String gender;
    
    public FirstJavaBean(){
    }
    
    public FirstJavaBean(String fname, String lname, String phone, String height, String weight, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
    
    public String getfname() {
        return fname;
    }
    public void setfname(String fname) {
        this.fname = fname;
    }
    
    public String getlname() {
        return lname;
    }
    public void setlname(String lname) {
        this.lname = lname;
    }
    
    public String getphone() {
        return phone;
    }
    
    public void setphone(String phone) {
        this.phone = phone;
    }
    
    public String height(){
            return height;
    }
    
    public void setheight(String height) {
        this.height= height;
    }
    
    public String getweight(){
        return weight;
    }
    
    public void setweight(String weight){
        this.weight = weight;
    }
    
    public String getgender() {
        return gender;
    }
    
    public void setgender(String gender) {
        this.gender = gender;
    }
    
 public void createTable() {
        try{
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            
            stmt = con.createStatement();
            
            stmt.executeUpdate("CREATE TABLE clientdata(fname VARCHAR(20) NOT NULL, lname VARCHAR(20) NOT NULL, phone VARCHAR(20) NOT NULL, height VARCHAR(20) NOT NULL, weight VARCHAR(20) NOT NULL, gender CHAR(2) NOT NULL)");
            con.close();
        }
        catch(Exception e) {
            
        }          
    }
    
    public void insert(){

          try{
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
         
            if(fname != null && lname != null && phone != null && height != null && weight != null && gender !=null) {
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO clientdata VALUES ('" + fname + "', '" + lname + "', '" + phone + "', '" + height + "', '" + weight + "', '" + gender + "')");
                stmt.close();
            }
        }
        catch(Exception e) {
            
        }
    }
    
}        

       
