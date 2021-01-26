/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertProdCatBean.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: The bean represents the model of Product Categories data for 
    use in the PRODUCTCATEGORIES Bubs Duds table. Getters/setters are provided 
    to allow for property manipulation. This bean's InsertUserProdCatData() method 
    allows for the data stored in each of the properties to be saved to a new 
    record in the PRODUCTCATEGORIES table.
 */
package bubsBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertProdCatBean {
    
    private int category_id;
    private String category_name;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    
    // empty constructor
    public InsertProdCatBean(){
        
    }
    
    public void InsertUserProdCatData(){
        
        Connection con = null;
        Statement stmt = null;
        PreparedStatement ps = null;
            
        try {
            int prod_cat_id = getCategory_id();
            String prod_cat_name = getCategory_name();          
            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            stmt = con.createStatement();
            
            ps = con.prepareStatement("INSERT INTO PRODUCTCATEGORIES(Category_ID, Category_Name) VALUES(?,?)");
            ps.setString(1, Integer.toString(prod_cat_id));
            ps.setString(2, prod_cat_name);
            ps.executeUpdate();
            
            System.out.println("Data entered success!");
            
        } catch (Exception e) {
            System.out.println("Error inserting data!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }  
    
}
