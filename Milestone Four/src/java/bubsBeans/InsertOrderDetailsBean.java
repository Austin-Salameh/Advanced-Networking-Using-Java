/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertOrderDetailsBean.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: The bean represents the model of Order Details data for use in 
    the ORDERDETAILS Bubs Duds table. Getters/setters are provided to allow for 
    property manipulation. This bean's InsertOrderDetailsData() method allows for
    the data stored in each of the properties to be saved to a new record in the
    ORDERDETAILS table.
 */
package bubsBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertOrderDetailsBean {
    private int detail_id;
    private int order_detail_id;
    private int detail_prod_id;
    private String detail_name;
    private double detail_price;
    private String detail_sku;
    private int detail_quantity;
    
    // empty constructor
    public InsertOrderDetailsBean(){
        
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getDetail_prod_id() {
        return detail_prod_id;
    }

    public void setDetail_prod_id(int detail_prod_id) {
        this.detail_prod_id = detail_prod_id;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    public double getDetail_price() {
        return detail_price;
    }

    public void setDetail_price(double detail_price) {
        this.detail_price = detail_price;
    }

    public String getDetail_sku() {
        return detail_sku;
    }

    public void setDetail_sku(String detail_sku) {
        this.detail_sku = detail_sku;
    }

    public int getDetail_quantity() {
        return detail_quantity;
    }

    public void setDetail_quantity(int detail_quantity) {
        this.detail_quantity = detail_quantity;
    }
    
    public void InsertOrderDetailsData(){
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            int detail_id = getDetail_id();
            int order_detail_id = getOrder_detail_id();
            int detail_prod_id = getDetail_prod_id();
            String detail_name = getDetail_name();
            double detail_price = getDetail_price();
            String detail_sku = getDetail_sku();
            int detail_quantity = getDetail_quantity();
            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            
            ps = con.prepareStatement("INSERT INTO ORDERDETAILS(Detail_ID, Order_Detail_ID, "
                    + "Detail_Prod_ID, Detail_Name, Detail_Price, Detail_SKU, Detail_Quantity) "
                    + "VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, Integer.toString(detail_id));
            ps.setString(2, Integer.toString(order_detail_id));
            ps.setString(3, Integer.toString(detail_prod_id));
            ps.setString(4, detail_name);
            ps.setString(5, Double.toString(detail_price));
            ps.setString(6, detail_sku);
            ps.setString(7, Integer.toString(detail_quantity));            
            ps.executeUpdate();
            
            System.out.println("Data Entry Success!");
        } catch (Exception e) {
            System.out.println("Error inserting data!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
