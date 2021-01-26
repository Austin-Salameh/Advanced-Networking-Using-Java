/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      CreateInputTables.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: This servlet enables the user to generate the seven tables that
    are used by the Bubs Duds online retailer. First, when the doGet() method
    is invoked, a simple button is provided for the user to generate the tables. 
    If the tables are unable to be created, an error message is displayed to the
    user.
*/
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/CreateInputTables"})
public class CreateInputTables extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        PrintWriter out = response.getWriter();

        /* 
            form that simply provides button to invoke doPost() that attempts to
            generate Bubs Duds' tables 
        */
        String htmlForm = "<html>" +
        "<head>" +
        "<title>Create Tables</title>\n" +
        "<meta charset=\"UTF-8\">\n" +
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "</head>\n" +
        "<body>\n" +
        "<div style=\"text-align: center;\">\n" +
        "<h1 style=\"color:blue; text-align: center; font-size: 70px;\">BubsDubs"
        + "  <img src=\"https://logo.stocklight.com/ASX/BUB.png\" width=\"80\" height=\"80\" >"
        + "</h1>\n" +
        "<h1>Create Tables</h1>" +
                
        "<p>Please hit the 'Submit' button below to generate the tables for Bubs Duds</p>" +
                
        "<form name=\"inputFrom\" method=\"post\" action=\"CreateInputTables\">\n" +

        "<input type=\"submit\" value=\"Generate Tables\" />\n" +
        "</form>\n" +
        "</div>" +
        "</body>\n" +
        "</html>";
        
        out.println(htmlForm);
        
        // button to return to index.html
        out.println(
            "<div style=\"text-align: center;\">\n" +
            "<form style=\"display: inline-block;\" action=\"index.html\">\n" +
                "<input type=\"submit\" value=\"Main Menu\" />\n" +
            "</form>\n" +
            "</div>"
            );
        
        out.println("<div><h2 style=\"text-align: center\">"
                + "<img src=\"https://previews.123rf.com/images/maradaisy/maradaisy1902/maradaisy190200008/116525877-baby-clothes-vector-kid-models-children-clothing-set-girl-boy-cloth-child-garment-apparel-isolated-o.jpg\" "
                + "alt =\"babyClothes\"></h2></div>");        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletOutputStream out = response.getOutputStream();
        Connection con = null;
        Statement stmt = null;
        
        out.println("<html>");
            out.println("<head><title>Created Tables</title></head>");
            out.println("<body style=\"text-align: center;\">");
            out.println("<h1 style=\"color:blue; text-align: center; font-size: 50px;\">BubsDuds"
            + "<img src=\"https://logo.stocklight.com/ASX/BUB.png\" width=\"80\" height=\"80\" >"
            + "</h1>\n");
            
        out.println("<h2><u>Bubs Duds Table Creation Tool</u></h2><br>");
  
        // attempts to establish connection to DB
        try {            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1","pass");
            stmt = con.createStatement();
            System.out.println("Connection to DB established!");    // debug tool
        } catch(Exception e){
            out.println("<b>Unable to establish connection to database...<b></br>");
            out.println(e.getMessage());
            e.printStackTrace();
        }
        
        // attempt to create tables for bubs duds (all at once) using for loop
        try {
            String create_prod_cat_table = "CREATE TABLE PRODUCTCATEGORIES(Category_ID "
                    + "INT, Category_Name VARCHAR(30), CONSTRAINT "
                    + "CATEGORY_PK PRIMARY KEY (CATEGORY_ID))";
            String create_customer_table = "CREATE TABLE CUSTOMER (Cust_ID INT,	Cust_Email VARCHAR(50),	"
                    + "Cust_Password VARCHAR(50), Cust_FirstName VARCHAR(50), "
                    + "Cust_LastName VARCHAR(50), Cust_PhoneNum VARCHAR(50), "
                    + "Cust_FaxNum VARCHAR(50), Cust_City VARCHAR(50), Cust_State VARCHAR(50), "
                    + "Cust_Zip INT, Cust_Country VARCHAR(50), Cust_EmailVerify Char(1), "
                    + "CONSTRAINT Customer_PRIMARY_KEY PRIMARY KEY (Cust_id))";
            String create_supplier_table = "CREATE TABLE SUPPLIERS (Supplier_ID INT NOT NULL, "
                    + "Supplier_Name VARCHAR(20) NOT NULL, Contact_fName VARCHAR(20) NOT NULL, "
                    + "Contact_lName VARCHAR(20) NOT NULL, Contact_Title VARCHAR(20) NULL, "
                    + "Contact_Phone_Num VARCHAR(10) NOT NULL, Company_Addr VARCHAR(40) NOT NULL, "
                    + "Local_Or_Private VARCHAR2(10), CONSTRAINT SUPPLIERS_pk PRIMARY KEY(Supplier_ID))";
            String create_payment_table = "CREATE TABLE PAYMENT (Payment_ID INT NOT NULL, "
                    + "Payment_Type VARCHAR(25) NOT NULL, "
                    + "Payment_Accepted char(1) check (Payment_Accepted in ('Y','N')), "
                    + "CONSTRAINT PAYMENT_pk PRIMARY KEY(Payment_ID))";
            String create_orders_table = "CREATE TABLE ORDERS(Order_ID INT, Order_Cust_ID INT , "
                    + "Order_Amt VARCHAR(50), Order_Ship_Name VARCHAR(50), Order_Ship_Addr "
                    + "VARCHAR(50), Order_Ship_City VARCHAR(50), Order_Ship_State VARCHAR(50), "
                    + "Order_Ship_Country VARCHAR(50), Order_PhoneNum VARCHAR(50), Order_FaxNum "
                    + "VARCHAR(50), Order_Shipping_Amt VARCHAR(50), Order_Tax_Amt VARCHAR(50), "
                    + "Order_Payment_ID INT, Order_Date VARCHAR(50), Order_Shipped char(1), "
                    + "Order_Tracking_Num VARCHAR(50), CONSTRAINT Orders_PRIMARY_KEY PRIMARY KEY (Order_id), "
                    + "CONSTRAINT Orders_PK FOREIGN key (Order_Cust_ID) REFERENCES Customer (Cust_ID), "
                    + "CONSTRAINT Orders_FK FOREIGN key (Order_Payment_ID) REFERENCES PAYMENT (PAYMENT_ID))";                    
            String create_products_table = "CREATE TABLE PRODUCTS(Product_ID INT NOT NULL, "
                    + "Product_SKU VARCHAR(50) NOT NULL, Product_Name VARCHAR(50) NOT NULL, "
                    + "Product_Price FLOAT NOT NULL, Product_Weight FLOAT NOT NULL, "
                    + "Product_Desc VARCHAR(50) NOT NULL, Product_Image VARCHAR(50) NOT NULL, "
                    + "Product_Cat_ID INT NOT NULL REFERENCES PRODUCTCATEGORIES (Category_ID), "
                    + "Product_Stock INT NOT NULL, Product_Location VARCHAR(50) NOT NULL, "
                    + "Prod_Supplier_ID INT NOT NULL REFERENCES SUPPLIERS (Supplier_ID), "
                    + "CONSTRAINT PRODUCTS_pk PRIMARY KEY(Product_ID))";
            String create_order_details_table = "CREATE TABLE ORDERDETAILS(Detail_ID INT NOT NULL, "
                    + "Order_Detail_ID INT NOT NULL, Detail_Prod_ID INT NOT NULL, "
                    + "Detail_Name VARCHAR(50) NOT NULL, Detail_Price FLOAT NOT NULL, "
                    + "Detail_SKU VARCHAR(50) NOT NULL, Detail_Quantity INT NOT NULL, "
                    + "CONSTRAINT ORDERDETAILS_PRIMARY_KEY PRIMARY KEY (Detail_ID), "
                    + "CONSTRAINT ORDERDETAILS_pk FOREIGN KEY (Order_Detail_ID) REFERENCES ORDERS(Order_Id), "
                    + "CONSTRAINT ORDERDETAILS_fk FOREIGN KEY (Detail_Prod_ID) REFERENCES PRODUCTS(Product_ID))";
            
            String[] tableNames = {create_prod_cat_table, create_customer_table, 
                                    create_supplier_table, create_payment_table, create_orders_table, 
                                    create_products_table, create_order_details_table};
            
            for (int i = 0; i < tableNames.length; i++) {
                stmt.executeUpdate(tableNames[i]);
            }
            
            out.println("All Bubs Duds' table were created successfully!<br><br>");
            System.out.println("All tables created successully!");  // debug tool

        } catch (SQLException e) {
            out.println("<b>Bubs Duds' tables were unable to be created...</b></br>");
            out.println("<i>It is likely that they are already created! <br/>Please try "
                    + "either displaying the data to confirm, or try dropping the tables altogether...</i><br/>");
            out.println("<u>Error: "+e.getMessage() + "</u><br><br>");
            e.printStackTrace();            
        }
        
        // menu button
        out.println(
        "<br>"
        + "<div style=\"text-align: center;\">\n" +
        "<form style=\"display: inline-block;\" action=\"index.html\">\n" +
            "<input type=\"submit\" value=\"Main Menu\" />\n" +
        "</form>\n" +
        "</div>"
        );

        // footer image
        out.println("<div><h2 style=\"text-align: center\">"
            + "<img src=\"https://previews.123rf.com/images/maradaisy/maradaisy1902/"
            + "maradaisy190200008/116525877-baby-clothes-vector-kid-models-children-"
            + "clothing-set-girl-boy-cloth-child-garment-apparel-isolated-o.jpg\" "
            + "alt =\"babyClothes\"></h2></div>");

    }
}