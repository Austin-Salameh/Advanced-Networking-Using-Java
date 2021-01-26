<%-- 
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertData.jsp
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: This JSP provides the user with the ability to add sample data
    to each of the seven tables used by Bubs Duds. Once the user presses the
    'Insert Data' button from the index.html page, this JSP will attempt to
    insert sample data in a series of try/catch blocks. If an error is
    encountered, an appropriate error message is displayed to the user.
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserting Data For Bubs Duds Tables</title>
    </head>
    <body style="text-align: center;">
        <h1 style="color:blue; text-align:center; font-size:50px;">
            Inserting Data For Bubs Duds Tables
            <img src="https://logo.stocklight.com/ASX/BUB.png" width="80" height="80" >
        </h1>
        <%
            Connection con = null;
            Statement stmt = null;
            boolean isSuccessful = true;
            
            if(request.getMethod().equals("GET")){
                
                // get db connection
                try {
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
                    stmt = con.createStatement();
                    System.out.println("DB connection established!");   // debug tool
                } catch (Exception e) {
                    out.println("Unable to connection to db</br>");
                    out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                    isSuccessful = false;
                }
                
                // try to insert data into all of the tables
                try {
                    // PRODUCTCATEGORIES data
                    try {
                         stmt.executeUpdate("INSERT ALL INTO PRODUCTCATEGORIES (Category_ID, Category_Name) "
                                + "VALUES (1, 'toys') INTO PRODUCTCATEGORIES (Category_ID, Category_Name) "
                                + "VALUES (2, 'clothes') INTO PRODUCTCATEGORIES (Category_ID, Category_Name) "
                                + "VALUES (3, 'pacifiers') INTO PRODUCTCATEGORIES (Category_ID, Category_Name) "
                                + "VALUES (4, 'books') INTO PRODUCTCATEGORIES (Category_ID, Category_Name) "
                                + "VALUES (5, 'strollers') SELECT * FROM dual");   
                    } catch (SQLException e) {
                        out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                        isSuccessful = false;
                    }
                    
                    // CUSTOMER data
                    try {
                        stmt.executeUpdate("INSERT ALL INTO CUSTOMER (Cust_ID, Cust_Email, "
                                + "Cust_Password, Cust_FirstName, Cust_LastName, "
                                + "Cust_PhoneNum, Cust_FaxNum, Cust_City, Cust_State, "
                                + "Cust_Zip, Cust_Country, Cust_EmailVerify) values "
                                + "(1, 'skendell0@salon.com', 'vj4goBKVbU', 'Sasha', "
                                + "'Kendell', '836-125-9804', '606-430-7685', 'Bellevue', "
                                + "'Nebraska', 68123, 'USA', 'Y') INTO CUSTOMER (Cust_ID, Cust_Email, "
                                + "Cust_Password, Cust_FirstName, Cust_LastName, Cust_PhoneNum, "
                                + "Cust_FaxNum, Cust_City, Cust_State, Cust_Zip, Cust_Country, "
                                + "Cust_EmailVerify) values (2, 'rdecoursey1@wix.com', 'LEczePO0TL', "
                                + "'Rosabella', 'De Coursey', '782-849-1810', '289-282-7833', "
                                + "'Antioch', 'California', 68288, 'USA', 'N') INTO CUSTOMER "
                                + "(Cust_ID, Cust_Email, Cust_Password, Cust_FirstName, Cust_LastName, "
                                + "Cust_PhoneNum, Cust_FaxNum, Cust_City, Cust_State, Cust_Zip, Cust_Country, "
                                + "Cust_EmailVerify) values (3, 'rlesmonde2@photobucket.com', 'a9cY3KuBT', "
                                + "'Roselia', 'Lesmonde', '890-662-3428', '398-849-8308', 'Silver Springs', "
                                + "'Nevada', 46795, 'USA', 'Y') INTO CUSTOMER (Cust_ID, Cust_Email, "
                                + "Cust_Password, Cust_FirstName, Cust_LastName, Cust_PhoneNum, Cust_FaxNum, "
                                + "Cust_City, Cust_State, Cust_Zip, Cust_Country, Cust_EmailVerify) "
                                + "values (4, 'gdener3@wunderground.com', '6PtpVaYa', 'Garrott', "
                                + "'Dener', '199-809-6312', '584-568-8579', 'Colorado City', "
                                + "'Texas', 41485, 'USA','Y') INTO CUSTOMER (Cust_ID, Cust_Email, "
                                + "Cust_Password, Cust_FirstName, Cust_LastName, Cust_PhoneNum, Cust_FaxNum, "
                                + "Cust_City, Cust_State, Cust_Zip, Cust_Country, Cust_EmailVerify) values "
                                + "(5, 'sgraham4@wikia.com', 'I1LGQc7ayYwM', 'Shepherd', 'Graham', '794-936-0062', "
                                + "'553-258-8600', 'Atmore', 'Alabama', 12161, 'USA', 'N') SELECT * from dual");    
                    } catch (SQLException e) {
                            out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                            isSuccessful = false;
                    }
                    
                    // SUPPLIERS data
                    try {                         
                        stmt.executeUpdate("INSERT ALL INTO SUPPLIERS(Supplier_ID, Supplier_Name, "
                                + "Contact_fName, Contact_lName, Contact_Title, Contact_Phone_Num, "
                                + "Company_Addr, Local_Or_Private) VALUES(1, 'Target','Tina', 'Thompson', "
                                + "'Manager', 8063216556, '341 North Pole Drive', 'Local') "
                                + "INTO SUPPLIERS(Supplier_ID, Supplier_Name, Contact_fName, "
                                + "Contact_lName, Contact_Title, Contact_Phone_Num, Company_Addr, "
                                + "Local_Or_Private) VALUES(2, 'Wal-Mart','Richard', 'Fish', "
                                + "'Clerk', 6058891256, '9800 Candycane Road', 'Local') "
                                + "INTO SUPPLIERS(Supplier_ID, Supplier_Name, Contact_fName, "
                                + "Contact_lName, Contact_Title, Contact_Phone_Num, "
                                + "Company_Addr, Local_Or_Private) VALUES(3, 'Wal-Mart','Gabriel', "
                                + "'Newberger', 'Clerk', 6058891256, '9800 Candycane Road', "
                                + "'Local') INTO SUPPLIERS(Supplier_ID, Supplier_Name, Contact_fName, "
                                + "Contact_lName, Contact_Title, Contact_Phone_Num, Company_Addr, "
                                + "Local_Or_Private) VALUES(4, 'Target', 'Grinch', 'WhoStoleChristmas', "
                                + "'President', 8063216556, '341 North Pole Drive', 'Local') "
                                + "INTO SUPPLIERS(Supplier_ID, Supplier_Name, Contact_fName, Contact_lName, "
                                + "Contact_Title, Contact_Phone_Num, Company_Addr, Local_Or_Private) "
                                + "VALUES(5, 'Gucci', 'Kato', 'Johnson', 'Manager', 7172215398, "
                                + "'123 Coal Avenue','Private') SELECT * FROM dual");   
                    } catch (SQLException e) {
                        out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                        isSuccessful = false;
                    }
                    
                    // PAYMENT data
                    try {                        
                        stmt.executeUpdate("INSERT ALL INTO PAYMENT(Payment_ID, Payment_Type, "
                                + "Payment_Accepted) VALUES(1,'Credit Card', 'Y') "
                                + "INTO PAYMENT(Payment_ID, Payment_Type, Payment_Accepted) "
                                + "VALUES(2, 'Credit Card', 'Y') INTO PAYMENT(Payment_ID, "
                                + "Payment_Type, Payment_Accepted) VALUES(3, 'Credit Card', 'Y') "
                                + "INTO PAYMENT(Payment_ID, Payment_Type, Payment_Accepted) "
                                + "VALUES(4,'Credit Card', 'Y') INTO PAYMENT(Payment_ID, "
                                + "Payment_Type, Payment_Accepted) VALUES(5, 'Credit Card', 'Y') "
                                + "SELECT * FROM dual");
                    } catch (SQLException e) {
                        out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                        isSuccessful = false;
                    }
                 
                    // ORDERS data
                    try {                       
                        stmt.executeUpdate("INSERT ALL INTO ORDERS (Order_ID, Order_Cust_ID, "
                            + "Order_Amt, Order_Ship_Name, Order_Ship_Addr, Order_Ship_City, "
                            + "Order_Ship_State, Order_Ship_Country, Order_PhoneNum, Order_FaxNum, "
                            + "Order_Shipping_Amt, Order_Tax_Amt, Order_Payment_ID, Order_Date, "
                            + "Order_Shipped, Order_Tracking_Num) values (1, 1, '£6993.41', "
                            + "'Nelli Nell', '1 Pond Park', 'Charlottesville', 'Virginia', "
                            + "'United States', '540-739-8844', '212-703-6401', '£94.91', "
                            + "'£19.24', 1, '9/24/2020', 'Y', '4198019916') INTO ORDERS "
                            + "(Order_ID, Order_Cust_ID, Order_Amt, Order_Ship_Name, "
                            + "Order_Ship_Addr, Order_Ship_City, Order_Ship_State, "
                            + "Order_Ship_Country, Order_PhoneNum, Order_FaxNum, "
                            + "Order_Shipping_Amt, Order_Tax_Amt, Order_Payment_ID, "
                            + "Order_Date, Order_Shipped, Order_Tracking_Num) values "
                            + "(2, 2, '£7697.31', 'Elliott Rosengarten', '26 Memorial Plaza', "
                            + "'Long Beach', 'California', 'United States', '310-482-4539', "
                            + "'865-185-7824', '£6.11', '£17.33', 2, '12/4/2019', 'N', '2985036208') "
                            + "INTO ORDERS (Order_ID, Order_Cust_ID, Order_Amt, Order_Ship_Name, "
                            + "Order_Ship_Addr, Order_Ship_City, Order_Ship_State, Order_Ship_Country,"
                            + " Order_PhoneNum, Order_FaxNum, Order_Shipping_Amt, Order_Tax_Amt, "
                            + "Order_Payment_ID, Order_Date, Order_Shipped, Order_Tracking_Num) "
                            + "values (3, 3, '£7101.28', 'Kimbra McDonogh', '5732 Holy Cross Junction', "
                            + "'Mobile', 'Alabama', 'United States', '251-670-7410', '336-723-5244', "
                            + "'£21.57', '£17.05', 3, '4/24/2020', 'Y', '8220601663') INTO ORDERS "
                            + "(Order_ID, Order_Cust_ID, Order_Amt, Order_Ship_Name, Order_Ship_Addr, "
                            + "Order_Ship_City, Order_Ship_State, Order_Ship_Country, Order_PhoneNum, "
                            + "Order_FaxNum, Order_Shipping_Amt, Order_Tax_Amt, Order_Payment_ID, "
                            + "Order_Date, Order_Shipped, Order_Tracking_Num) values (4, 4, '£8234.77', "
                            + "'Andria Meriton', '0 Old Shore Way', 'Dallas', 'Texas', 'United States', "
                            + "'972-192-9641', '228-881-6165', '£45.52', '£8.69', 4, '10/20/2020', 'N', "
                            + "'7413189313') INTO ORDERS (Order_ID, Order_Cust_ID, Order_Amt, "
                            + "Order_Ship_Name, Order_Ship_Addr, Order_Ship_City, Order_Ship_State, "
                            + "Order_Ship_Country, Order_PhoneNum, Order_FaxNum, Order_Shipping_Amt, "
                            + "Order_Tax_Amt, Order_Payment_ID, Order_Date, Order_Shipped, "
                            + "Order_Tracking_Num) values (5, 5, '£3042.53', 'Bo Blindt', "
                            + "'56 Reinke Road', 'Reno', 'Nevada', 'United States', '775-998-5582', "
                            + "'510-604-4048', '£65.77', '£18.89', 5, '2/4/2020', 'Y', "
                            + "'3376022526') SELECT * FROM dual");
                    } catch (SQLException e) {
                        out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                        isSuccessful = false;
                    }
                    
                    // PRODUCTS data
                    try {                        
                        stmt.executeUpdate("INSERT ALL INTO PRODUCTS(Product_ID, Product_SKU, "
                            + "Product_Name, Product_Price, Product_Weight, Product_Desc, "
                            + "Product_Image, Product_Cat_ID, Product_Stock, Product_Location, "
                            + "Prod_Supplier_ID) VALUES(1, 'lk234lk2j34', 'Shirt', 9.99, 1, "
                            + "'3-6 month shirts', 'shirt.png', 1, 10, 'St.Louis', 1) "
                            + "INTO PRODUCTS(Product_ID, Product_SKU, Product_Name, "
                            + "Product_Price, Product_Weight, Product_Desc, Product_Image, "
                            + "Product_Cat_ID, Product_Stock, Product_Location, Prod_Supplier_ID) "
                            + "VALUES(789, 'lk78lk234', 'Pants', 13.99, 1, '6-12 month pants', "
                            + "'pants.png', 2, 5, 'Omaha', 2) INTO PRODUCTS(Product_ID, "
                            + "Product_SKU, Product_Name, Product_Price, Product_Weight, "
                            + "Product_Desc, Product_Image, Product_Cat_ID, Product_Stock, "
                            + "Product_Location, Prod_Supplier_ID) VALUES(12, 'zk234Pk2j34xx', "
                            + "'Sleeper', 8.99, 1, 'Newborn Sleeper', 'sleeper.png', 3, 3, "
                            + "'Las Vegas', 3) INTO PRODUCTS(Product_ID, Product_SKU, Product_Name, "
                            + "Product_Price, Product_Weight, Product_Desc, Product_Image, "
                            + "Product_Cat_ID, Product_Stock, Product_Location, Prod_Supplier_ID) "
                            + "VALUES(222, '918wjkeujfh', 'Blanket', 11.99, 1, "
                            + "'Fuzzy Maroon Baby Blanket', 'blanket.png', 5, 1, 'St.Louis', 4) "
                            + "INTO PRODUCTS(Product_ID, Product_SKU, Product_Name, Product_Price, "
                            + "Product_Weight, Product_Desc, Product_Image, Product_Cat_ID, "
                            + "Product_Stock, Product_Location, Prod_Supplier_ID) VALUES(15, "
                            + "'lk234lk2j34', 'Socks', 3.99, 1, '6-12 months', 'socks.png', 4, 1, "
                            + "'San Fransisco', 5) SELECT * FROM dual");
                    } catch (SQLException e) {
                        out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                        isSuccessful = false;
                    }
                    
                    // ORDERDETAILS data
                    try {                        
                        stmt.executeUpdate("INSERT ALL INTO ORDERDETAILS(Detail_ID, "
                            + "Order_Detail_ID, Detail_Prod_ID, Detail_Name, Detail_Price, "
                            + "Detail_SKU, Detail_Quantity) VALUES (1, 1, 1, 'Red Toddler Shirt', "
                            + "9.99, 'lk234lk2j34', 2) INTO ORDERDETAILS(Detail_ID, "
                            + "Order_Detail_ID, Detail_Prod_ID, Detail_Name, Detail_Price, "
                            + "Detail_SKU, Detail_Quantity) VALUES (2, 2, 789, 'Blue Toddler Pants', "
                            + "13.99, 'lk78lk234', 1) INTO ORDERDETAILS(Detail_ID, Order_Detail_ID, "
                            + "Detail_Prod_ID, Detail_Name, Detail_Price, Detail_SKU, Detail_Quantity) "
                            + "VALUES (3, 3, 12, 'Grey Toddler Sleeper', 8.99, 'zk234Pk2j34xx', 1) "
                            + "INTO ORDERDETAILS(Detail_ID, Order_Detail_ID, Detail_Prod_ID, "
                            + "Detail_Name, Detail_Price, Detail_SKU, Detail_Quantity) VALUES "
                            + "(4, 4, 222, 'Medium Polka Dot Blanket', 11.99, '918wjkeujfh', 1) "
                            + "INTO ORDERDETAILS(Detail_ID, Order_Detail_ID, Detail_Prod_ID, "
                            + "Detail_Name, Detail_Price, Detail_SKU, Detail_Quantity) VALUES "
                            + "(5, 5, 15, 'Two-Pair Cowboy Newborn Socks', 3.99, 'lk234lk2j34', 2) "
                            + "SELECT * FROM dual");
                    } catch (SQLException e) {
                        out.println("<u>Error: " + e.getMessage() + "</u><br/>");
                        isSuccessful = false;
                    }
                    
                    if(isSuccessful == true){
                        out.println("Data has been inserted into the tables successfully!</br>");
                    }
                    else{
                        out.println("There was an error when attempting to insert the data into the tables...</br>");
                        out.println("<i>It is likely that you haven't yet created the tables for the data to be inserted into...</i><br/>");
                    }
                    
                } catch (Exception e) {
                    out.println("There was an error when attempting to insert the data into the tables...</br>");
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
        <div style='text-align: center;'>
            <h2 style="text-align: center">
                <img src="https://previews.123rf.com/images/maradaisy/maradaisy1902/maradaisy190200008/116525877-baby-clothes-vector-kid-models-children-clothing-set-girl-boy-cloth-child-garment-apparel-isolated-o.jpg" alt ="babyClothes">
            </h2>
        </div>
    </body>
</html>
