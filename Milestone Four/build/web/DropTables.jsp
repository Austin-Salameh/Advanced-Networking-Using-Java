<%-- 
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      DropTables.jsp
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: This JSP provides the functionality for the user to drop all
    of the Bubs Duds seven tables. The tables are attempted to be dropped as
    soon as the user presses the 'Drop Tables' button from the index.html page.
    If any errors are encountered, an appropriate error message is displayed to
    the user.
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dropping Tables From Bubs Duds</title>
    </head>
    <body style="text-align: center;">
        <h1 style="color:blue; text-align: center; font-size: 50px">
            Dropping Tables From Bubs Duds
            <img src="https://logo.stocklight.com/ASX/BUB.png" width="80" height="80" >
        </h1>
        <%
            if(request.getMethod().equals("POST")){
                Connection con = null;
                Statement stmt = null;
                
                // get oracle db connections
                try {
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
                    stmt = con.createStatement();   
                } catch (Exception e) {
                    out.println("Sorry, something went wrong when trying to connect to the database!");
                    out.println("<u>Error: "+e.getMessage()+"</u><br/>");
                }
                
                // attempts to drop tables
                try {
                    String drop_prod_cat_table = "DROP TABLE PRODUCTCATEGORIES";
                    String drop_cust_table = "DROP TABLE CUSTOMER";
                    String drop_supplier_table = "DROP TABLE SUPPLIERS";
                    String drop_payment_table = "DROP TABLE PAYMENT";
                    String drop_orders_table = "DROP TABLE ORDERS";
                    String drop_products_table = "DROP TABLE PRODUCTS";
                    String drop_order_details_table = "DROP TABLE ORDERDETAILS";
                    
                    stmt.executeUpdate(drop_order_details_table);
                    stmt.executeUpdate(drop_orders_table);
                    stmt.executeUpdate(drop_products_table);
                    stmt.executeUpdate(drop_cust_table);
                    stmt.executeUpdate(drop_supplier_table);
                    stmt.executeUpdate(drop_payment_table);
                    stmt.executeUpdate(drop_prod_cat_table);
                    
                    out.println("All tables dropped successfully!<br>");
                } catch (SQLException e) {
                    out.println("Tables were unable to be dropped...<br><br>");
                    out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                }
                
                // releases db connections
                try {
                    stmt.close();
                    con.close();
                    System.out.println("DB Connections released successful!");  // debug tool
                } catch (Exception e) {
                    out.println("Unable to release DB connections...<br><br>");
                    out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                }                
            }
        %>
        
        <br><br>
        <!-- menu button -->
        <div style="text-align: center;">
            <form style="display: inline-block;" action="index.html">
                <input type="submit" value="Main Menu" />
            </form>
        </div>
        
        <!-- footer image -->
        <div>
            <h2 style="text-align: center">
                <img src="https://previews.123rf.com/images/maradaisy/maradaisy1902/maradaisy190200008/116525877-baby-clothes-vector-kid-models-children-clothing-set-girl-boy-cloth-child-garment-apparel-isolated-o.jpg" alt ="babyClothes">
            </h2>
        </div>
    </body>
</html>
