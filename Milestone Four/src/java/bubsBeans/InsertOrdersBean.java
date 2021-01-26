/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertOrdersBean.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: The bean represents the model of Orders data for use in 
    the ORDERS Bubs Duds table. Getters/setters are provided to allow for 
    property manipulation. This bean's InsertOrdersData() method allows for
    the data stored in each of the properties to be saved to a new record in the
    ORDERS table.
 */
package bubsBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertOrdersBean {
    private int order_id;
    private int order_cust_id;
    private String order_amt;
    private String order_ship_name;
    private String order_ship_addr;
    private String order_ship_city;
    private String order_ship_state;
    private String order_ship_country;
    private String order_phone_num;
    private String order_fax_num;
    private String order_shipping_amt;
    private String order_tax_amt;
    private int order_payment_id;
    private String order_date;
    private char order_shipped;
    private String order_tracking_num;
    
    // empty constructor
    public InsertOrdersBean(){
        
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_cust_id() {
        return order_cust_id;
    }

    public void setOrder_cust_id(int order_cust_id) {
        this.order_cust_id = order_cust_id;
    }

    public String getOrder_amt() {
        return order_amt;
    }

    public void setOrder_amt(String order_amt) {
        this.order_amt = order_amt;
    }

    public String getOrder_ship_name() {
        return order_ship_name;
    }

    public void setOrder_ship_name(String order_ship_name) {
        this.order_ship_name = order_ship_name;
    }

    public String getOrder_ship_addr() {
        return order_ship_addr;
    }

    public void setOrder_ship_addr(String order_ship_addr) {
        this.order_ship_addr = order_ship_addr;
    }

    public String getOrder_ship_city() {
        return order_ship_city;
    }

    public void setOrder_ship_city(String order_ship_city) {
        this.order_ship_city = order_ship_city;
    }

    public String getOrder_ship_state() {
        return order_ship_state;
    }

    public void setOrder_ship_state(String order_ship_state) {
        this.order_ship_state = order_ship_state;
    }

    public String getOrder_ship_country() {
        return order_ship_country;
    }

    public void setOrder_ship_country(String order_ship_country) {
        this.order_ship_country = order_ship_country;
    }

    public String getOrder_phone_num() {
        return order_phone_num;
    }

    public void setOrder_phone_num(String order_phone_num) {
        this.order_phone_num = order_phone_num;
    }

    public String getOrder_fax_num() {
        return order_fax_num;
    }

    public void setOrder_fax_num(String order_fax_num) {
        this.order_fax_num = order_fax_num;
    }

    public String getOrder_shipping_amt() {
        return order_shipping_amt;
    }

    public void setOrder_shipping_amt(String order_shipping_amt) {
        this.order_shipping_amt = order_shipping_amt;
    }

    public String getOrder_tax_amt() {
        return order_tax_amt;
    }

    public void setOrder_tax_amt(String order_tax_amt) {
        this.order_tax_amt = order_tax_amt;
    }

    public int getOrder_payment_id() {
        return order_payment_id;
    }

    public void setOrder_payment_id(int order_payment_id) {
        this.order_payment_id = order_payment_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public char getOrder_shipped() {
        return order_shipped;
    }

    public void setOrder_shipped(char order_shipped) {
        this.order_shipped = order_shipped;
    }

    public String getOrder_tracking_num() {
        return order_tracking_num;
    }

    public void setOrder_tracking_num(String order_tracking_num) {
        this.order_tracking_num = order_tracking_num;
    }
    
    public void InsertOrdersData(){
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            int order_id = getOrder_id();
            int order_cust_id = getOrder_cust_id();
            String order_amt = getOrder_amt();
            String order_ship_name = getOrder_ship_name();
            String order_ship_addr = getOrder_ship_addr();
            String order_ship_city = getOrder_ship_city();
            String order_ship_state = getOrder_ship_state();
            String order_ship_country = getOrder_ship_country();
            String order_phone_num = getOrder_phone_num();
            String order_fax_num = getOrder_fax_num();
            String order_shipping_amt = getOrder_shipping_amt();
            String order_tax_amt = getOrder_tax_amt();
            int order_payment_id = getOrder_payment_id();
            String order_date = getOrder_date();
            char order_shipped = getOrder_shipped();
            String order_tracking_num = getOrder_tracking_num();
            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            
            ps = con.prepareStatement("INSERT INTO ORDERS(Order_ID, Order_Cust_ID, "
                    + "Order_Amt, Order_Ship_Name, Order_Ship_Addr, Order_Ship_City, "
                    + "Order_Ship_State, Order_Ship_Country, Order_PhoneNum, "
                    + "Order_FaxNum, Order_Shipping_Amt, Order_Tax_Amt, Order_Payment_ID, "
                    + "Order_Date, Order_Shipped, Order_Tracking_Num) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, Integer.toString(order_id));
            ps.setString(2, Integer.toString(order_cust_id));
            ps.setString(3, order_amt);
            ps.setString(4, order_ship_name);
            ps.setString(5, order_ship_addr);
            ps.setString(6, order_ship_city);
            ps.setString(7, order_ship_state);
            ps.setString(8, order_ship_country);
            ps.setString(9, order_phone_num);
            ps.setString(10, order_fax_num);
            ps.setString(11, order_shipping_amt);
            ps.setString(12, order_tax_amt);
            ps.setString(13, Integer.toString(order_payment_id));
            ps.setString(14, order_date);
            ps.setString(15, Character.toString(order_shipped));
            ps.setString(16, order_tracking_num);            
            ps.executeUpdate();
            
            System.out.println("Data Entry Successful!");
        } catch (Exception e) {
            System.out.println("Error inserting data!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
}
