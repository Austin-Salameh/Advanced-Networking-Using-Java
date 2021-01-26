/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertProdBean.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: The bean represents the model of Products data for use in 
    the PRODUCTS Bubs Duds table. Getters/setters are provided to allow for 
    property manipulation. This bean's InsertProdData() method allows for
    the data stored in each of the properties to be saved to a new record in the
    PRODUCTS table.
 */
package bubsBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertProdBean {
    
    private int product_id;
    private String product_sku;
    private String product_name;
    private double product_price;
    private double product_weight;
    private String product_desc;
    private String product_image;
    private int product_cat_id;
    private int product_stock;
    private String product_location;
    private int prod_supplier_id;
    
    // empty constructor
    public InsertProdBean(){
        
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public double getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(double product_weight) {
        this.product_weight = product_weight;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public int getProduct_cat_id() {
        return product_cat_id;
    }

    public void setProduct_cat_id(int product_cat_id) {
        this.product_cat_id = product_cat_id;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public String getProduct_location() {
        return product_location;
    }

    public void setProduct_location(String product_location) {
        this.product_location = product_location;
    }

    public int getProd_supplier_id() {
        return prod_supplier_id;
    }

    public void setProd_supplier_id(int prod_supplier_id) {
        this.prod_supplier_id = prod_supplier_id;
    }
    
    public void InsertProdData(){
        
        Connection con = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        
        try {
            int prod_id = getProduct_id();
            String prod_sku = getProduct_sku();
            String prod_name = getProduct_name();
            double prod_price = getProduct_price();
            double prod_weight = getProduct_weight();
            String prod_desc = getProduct_desc();
            String prod_image = getProduct_image();
            int prod_cat_id = getProduct_cat_id();
            int prod_stock = getProduct_stock();
            String prod_location = getProduct_location();
            int prod_supplier_id = getProd_supplier_id();
            
            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            stmt = con.createStatement();
            
            ps = con.prepareStatement("INSERT INTO PRODUCTS(Product_ID, Product_SKU, "
                    + "Product_Name, Product_Price, Product_Weight, Product_Desc, "
                    + "Product_Image, Product_Cat_ID, Product_Stock, Product_Location, "
                    + "Prod_Supplier_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, Integer.toString(prod_id));
            ps.setString(2, prod_sku);
            ps.setString(3, prod_name);
            ps.setString(4, Double.toString(prod_price));
            ps.setString(5, Double.toString(prod_weight));
            ps.setString(6, prod_desc);
            ps.setString(7, prod_image);
            ps.setString(8, Integer.toString(prod_cat_id));
            ps.setString(9, Integer.toString(prod_stock));
            ps.setString(10, prod_location);
            ps.setString(11, Integer.toString(prod_supplier_id));            
            ps.executeUpdate();
            
            System.out.println("Data entry success!");
        } catch (Exception e) {
            System.out.println("Error inserting data!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }
    
}
