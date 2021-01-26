<%-- 
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      GetUserData.jsp
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: This JSP provides the user with the ability to add their own
    inputted data to each of the seven Bubs Duds' tables. The user is first
    prompted to select which table they would like to add data to from a drop
    down list. Then, once the 'Insert New Data' button is pressed, corresponding
    fields are displayed to allow for user input.

    Depending on the user selection, beans for each of the seven tables are used
    to store the user-inputted values and then invoke each bean's Insert()
    method to store the values to a new record in their respective tables.
--%>

<%@page import="bubsBeans.InsertOrderDetailsBean"%>
<%@page import="bubsBeans.InsertOrdersBean"%>
<%@page import="bubsBeans.InsertCustomerBean"%>
<%@page import="bubsBeans.InsertPaymentBean"%>
<%@page import="bubsBeans.InsertSupplierBean"%>
<%@page import="bubsBeans.InsertProdBean"%>
<%@page import="bubsBeans.InsertProdCatBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Data Based On Table Selection</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body style="text-align: center">
        <h1 style="color:blue; text-align: center; font-size: 50px">
            Insert Data Based On Table Selection
            <img src="https://logo.stocklight.com/ASX/BUB.png" width="80" height="80" >
        </h1>
        
        <%
            Connection con = null;
            Statement stmt = null;
            
        %>
        
        <%!public int categoryIdentifier = 0;%>
        
        <%
            if(request.getMethod().equals("GET")){
                printForm(out);
                
                try {
                   if(request.getParameter("bubs_table_selections").equals("PRODUCTCATEGORIES")){
                        categoryIdentifier = 1;
                       
                        out.println("<form method='POST' action='GetUserData.jsp'><div class='container'>");
                        out.println("<label>Category_ID:</label><input type='text' name='user_category_id'><br>");
                        out.println("<label>Category_Name:</label><input type='text' name='user_category_name'><br><br>");
                        out.println("<input type='submit' value='Submit New Data' name='submit_new_data_btn'");
                        out.println("</div></form>");
                    }
                   else if(request.getParameter("bubs_table_selections").equals("PRODUCTS")){
                        categoryIdentifier = 2;
                       
                        out.println("<form method='POST' action='GetUserData.jsp'><div class='container'>");
                        out.println("Product_ID:<input type='text' name='user_product_id'><br>");
                        out.println("Product_SKU:<input type='text' name='user_product_sku'><br>");
                        out.println("Product_Name<input type='text' name='user_product_name'><br>");
                        out.println("Product_Price:<input type='text' name='user_product_price'><br>");
                        out.println("Product_Weight:<input type='text' name='user_product_weight'><br>");
                        out.println("Product_Desc:<input type='text' name='user_product_desc'><br>");
                        out.println("Product_Image:<input type='text' name='user_product_image'><br>");
                        out.println("Product_Cat_ID:<input type='text' name='user_product_cat_id'><br>");
                        out.println("Product_Stock:<input type='text' name='user_product_stock'><br>");
                        out.println("Product_Location:<input type='text' name='user_product_location'><br>");
                        out.println("Product_Supplier_ID:<input type='text' name='user_product_supplier_id'><br><br>");
                        out.println("<input type='submit' value='Submit New Data' name='submit_new_data_btn'");
                        out.println("</div></form>");
                   }
                   else if(request.getParameter("bubs_table_selections").equals("SUPPLIERS")){
                       categoryIdentifier = 3;
                       
                        out.println("<form method='POST' action='GetUserData.jsp'><div class='container'>");
                        out.println("Supplier_ID:<input type='text' name='user_supplier_id'><br>");
                        out.println("Supplier_Name:<input type='text' name='user_supplier_name'><br>");
                        out.println("Contact_fName<input type='text' name='user_contact_fname'><br>");
                        out.println("Contact_lName<input type='text' name='user_contact_lname'><br>");
                        out.println("Contact_Title:<input type='text' name='user_contact_title'><br>");
                        out.println("Contact_Phone_Num:<input type='text' name='user_contact_phone_num'><br>");
                        out.println("Company_Addr<input type='text' name='user_company_addr'><br>");
                        out.println("Local_Or_Private<input type='text' name='user_local_or_private'><br><br>");                       
                        out.println("<input type='submit' value='Submit New Data' name='submit_new_data_btn'");
                        out.println("</div></form>");
                   }
                   else if(request.getParameter("bubs_table_selections").equals("PAYMENT")){
                        categoryIdentifier = 4;

                        out.println("<form method='POST' action='GetUserData.jsp'><div class='container'>");
                        out.println("Payment_ID:<input type='text' name='user_payment_id'><br>");
                        out.println("Payment_Type:<input type='text' name='user_payment_type'><br>");
                        out.println("Payment_Accepted:<input type='text' value='Y or N' name='user_payment_accepted'><br><br>");
                        out.println("<input type='submit' value='Submit New Data' name='submit_new_data_btn'");
                        out.println("</div></form>");
                   }
                   else if(request.getParameter("bubs_table_selections").equals("CUSTOMER")){
                        categoryIdentifier = 5;

                        out.println("<form method='POST' action='GetUserData.jsp'><div class='container'>");
                        out.println("Cust_ID:<input type='text' name='user_cust_id'><br>");
                        out.println("Cust_Email:<input type='email' name='user_cust_email'><br>");
                        out.println("Cust_Password:<input type='password' name='user_cust_password'><br>");
                        out.println("Cust_FirstName:<input type='text' name='user_cust_first_name'><br>");
                        out.println("Cust_LastName:<input type='text' name='user_cust_last_name'><br>");
                        out.println("Cust_PhoneNum:<input type='text' name='user_cust_phone_num'><br>");
                        out.println("Cust_FaxNum:<input type='text' name='user_cust_fax_num'><br>");
                        out.println("Cust_City:<input type='text' name='user_cust_city'><br>");
                        out.println("Cust_State:<input type='text' name='user_cust_state'><br>");
                        out.println("Cust_Zip:<input type='text' name='user_cust_zip'><br>");
                        out.println("Cust_Country:<input type='text' name='user_cust_country'><br>");
                        out.println("Cust_EmailVerify:<input type='text' value='Y or N' name='user_cust_email_verify'><br><br>");
                        out.println("<input type='submit' value='Submit New Data' name='submit_new_data_btn'");
                        out.println("</div></form>");
                   }
                   else if(request.getParameter("bubs_table_selections").equals("ORDERS")){
                        categoryIdentifier = 6;

                        out.println("<form method='POST' action='GetUserData.jsp'><div class='container'>");
                        out.println("Order_ID:<input type='text' name='user_order_id'><br>");
                        out.println("Order_Cust_ID<input type='text' name='user_order_cust_id'><br>");
                        out.println("Order_Amt<input type='text' name='user_order_amt'><br>");
                        out.println("Order_Ship_Name<input type='text' name='user_order_ship_name'><br>");
                        out.println("Order_Ship_Addr:<input type='text' name='user_order_ship_addr'><br>");
                        out.println("Order_Ship_City:<input type='text' name='user_order_ship_city'><br>");
                        out.println("Order_Ship_State:<input type='text' name='user_order_ship_state'><br>");
                        out.println("Order_Ship_Country:<input type='text' name='user_order_ship_country'><br>");
                        out.println("Order_PhoneNum:<input type='text' name='user_order_phone_num'><br>");
                        out.println("Order_FaxNum:<input type='text' name='user_order_fax_num'><br>");
                        out.println("Order_Shipping_Amt:<input type='text' name='user_order_shipping_amt'><br>");
                        out.println("Order_Tax_Amt:<input type='text' name='user_order_tax_amt'><br>");
                        out.println("Order_Payment_ID:<input type='text' name='user_order_payment_id'><br>");
                        out.println("Order_Date:<input type='text' name='user_order_date'><br>");
                        out.println("Order_Shipped:<input type='text' value='Y or N' name='user_order_shipped'><br>");
                        out.println("Order_Tracking_Num:<input type='text' name='user_order_tracking_num'><br><br>");
                        out.println("<input type='submit' value='Submit New Data' name='submit_new_data_btn'");
                        out.println("</div></form>");                       
                   }
                   else if(request.getParameter("bubs_table_selections").equals("ORDERDETAILS")){
                       categoryIdentifier = 7;
                       
                        out.println("<form method='POST' action='GetUserData.jsp'><div class='container'>");
                        out.println("Detail_ID:<input type='text' name='user_detail_id'><br>");    
                        out.println("Order_Detail_ID:<input type='text' name='user_order_detail_id'><br>");    
                        out.println("Detail_Prod_ID:<input type='text' name='user_detail_prod_id'><br>");    
                        out.println("Detail_Name:<input type='text' name='user_detail_name'><br>");    
                        out.println("Detail_Price:<input type='text' name='user_detail_price'><br>");    
                        out.println("Detail_SKU:<input type='text' name='user_detail_sku'><br>");    
                        out.println("Detail_Quanitity:<input type='text' name='user_detail_quantity'><br><br>");    
                        out.println("<input type='submit' value='Submit New Data' name='submit_new_data_btn'");
                        out.println("</div></form>"); 
                   }
                } catch (Exception e) {
                    out.println(e.getMessage() + "</br>");
                    e.printStackTrace();
                }                
            }
            
            else if(request.getMethod().equals("POST")){
                if(categoryIdentifier == 1){
                    try {
                        bubsBeans.InsertProdCatBean ipcb = new InsertProdCatBean();
                        ipcb.setCategory_id(Integer.parseInt(request.getParameter("user_category_id")));
                        ipcb.setCategory_name(request.getParameter("user_category_name"));
                        
                        ipcb.InsertUserProdCatData(); 
                        out.println("Your data has been entered <b>successfully!</b><br>");
                    
                      
                    } catch (Exception e) {
                        out.println("Data insertion failed!<br>");
                        out.println(e.getMessage() + "</br>");
                        e.printStackTrace();
                    }
                }
                else if(categoryIdentifier == 2){
                    try {
                        bubsBeans.InsertProdBean ipb = new InsertProdBean();
                        
                        ipb.setProduct_id(Integer.parseInt(request.getParameter("user_product_id")));
                        ipb.setProduct_sku(request.getParameter("user_product_sku"));
                        ipb.setProduct_name(request.getParameter("user_product_name"));
                        ipb.setProduct_price(Double.parseDouble(request.getParameter("user_product_price")));
                        ipb.setProduct_weight(Double.parseDouble(request.getParameter("user_product_weight")));
                        ipb.setProduct_desc(request.getParameter("user_product_desc"));
                        ipb.setProduct_image(request.getParameter("user_product_image"));
                        ipb.setProduct_cat_id(Integer.parseInt(request.getParameter("user_product_cat_id")));
                        ipb.setProduct_stock(Integer.parseInt(request.getParameter("user_product_stock")));
                        ipb.setProduct_location(request.getParameter("user_product_location"));
                        ipb.setProd_supplier_id(Integer.parseInt(request.getParameter("user_product_supplier_id")));
                        
                        ipb.InsertProdData();
                        
                        out.println("Your data has been entered <b>successfully!</b><br>");
                    } catch (Exception e) {
                        out.println("Data insertion failed!<br>");
                        out.println(e.getMessage() + "</br>");
                        e.printStackTrace();
                    }                    
                }
                else if(categoryIdentifier == 3){
                    try {
                        bubsBeans.InsertSupplierBean isb = new InsertSupplierBean();
                    
                        isb.setSupplier_id(Integer.parseInt(request.getParameter("user_supplier_id")));
                        isb.setSupplier_name(request.getParameter("user_supplier_name"));
                        isb.setContact_fname(request.getParameter("user_contact_fname"));
                        isb.setContact_lname(request.getParameter("user_contact_lname"));
                        isb.setContact_title(request.getParameter("user_contact_title"));
                        isb.setContact_phone_num(request.getParameter("user_contact_phone_num"));
                        isb.setCompany_addr(request.getParameter("user_company_addr"));
                        isb.setLocal_or_private(request.getParameter("user_local_or_private"));

                        isb.InsertSupplierData();
                        out.println("Your data has been entered <b>successfully!</b><br>");  
                    } catch (Exception e) {
                        out.println("Data insertion failed!<br>");
                        out.println(e.getMessage() + "</br>");
                        e.printStackTrace();
                    }
                    
                }
                else if(categoryIdentifier == 4){
                    try {
                        bubsBeans.InsertPaymentBean ipb = new InsertPaymentBean();
                        
                        ipb.setPayment_id(Integer.parseInt(request.getParameter("user_payment_id")));
                        ipb.setPayment_type(request.getParameter("user_payment_type"));
                        ipb.setPayment_accepted(request.getParameter("user_payment_accepted").charAt(0));
                        
                        ipb.InsertPaymentData();
                        out.println("Your data has been entered <b>successfully!</b><br>");
                    } catch (Exception e) {
                        out.println("Data insertion failed!<br>");
                        out.println(e.getMessage() + "</br>");
                        e.printStackTrace();
                    }
                }
                else if(categoryIdentifier == 5){
                    try {
                        bubsBeans.InsertCustomerBean icb = new InsertCustomerBean();

                        icb.setCust_id(Integer.parseInt(request.getParameter("user_cust_id")));
                        icb.setCust_email(request.getParameter("user_cust_email"));
                        icb.setCust_password(request.getParameter("user_cust_password"));
                        icb.setCust_first_name(request.getParameter("user_cust_first_name"));
                        icb.setCust_last_name(request.getParameter("user_cust_last_name"));
                        icb.setCust_phone_num(request.getParameter("user_cust_phone_num"));
                        icb.setCust_fax_num(request.getParameter("user_cust_fax_num"));
                        icb.setCust_city(request.getParameter("user_cust_city"));
                        icb.setCust_state(request.getParameter("user_cust_state"));
                        icb.setCust_zip(Integer.parseInt(request.getParameter("user_cust_zip")));
                        icb.setCust_email_verify(request.getParameter("user_cust_email_verify").charAt(0));

                        icb.InsertCustomerData();
                        out.println("Your data has been entered <b>successfully!</b><br>");
                    } catch (Exception e) {
                        out.println("Data insertion failed!<br>");
                        out.println(e.getMessage() + "</br>");
                        e.printStackTrace();
                    }
                }
                else if(categoryIdentifier == 6){
                    try {
                        bubsBeans.InsertOrdersBean iob = new InsertOrdersBean();

                        iob.setOrder_id(Integer.parseInt(request.getParameter("user_order_id")));
                        iob.setOrder_cust_id(Integer.parseInt(request.getParameter("user_order_cust_id")));
                        iob.setOrder_amt(request.getParameter("user_order_amt"));
                        iob.setOrder_ship_name(request.getParameter("user_order_ship_name"));
                        iob.setOrder_ship_addr(request.getParameter("user_order_ship_addr"));
                        iob.setOrder_ship_city(request.getParameter("user_order_ship_city"));
                        iob.setOrder_ship_state(request.getParameter("user_order_ship_state"));
                        iob.setOrder_ship_country(request.getParameter("user_order_ship_country"));
                        iob.setOrder_phone_num(request.getParameter("user_order_phone_num"));
                        iob.setOrder_fax_num(request.getParameter("user_order_fax_num"));
                        iob.setOrder_shipping_amt(request.getParameter("user_order_shipping_amt"));
                        iob.setOrder_tax_amt(request.getParameter("user_order_tax_amt"));
                        iob.setOrder_payment_id(Integer.parseInt(request.getParameter("user_order_payment_id")));
                        iob.setOrder_date(request.getParameter("user_order_date"));
                        iob.setOrder_shipped(request.getParameter("user_order_shipped").charAt(0));
                        iob.setOrder_tracking_num(request.getParameter("user_order_tracking_num"));

                        iob.InsertOrdersData();                            
                        out.println("Your data has been entered <b>successfully!</b><br>");
                    } catch (Exception e) {
                        out.println("Data insertion failed!<br>");
                        out.println(e.getMessage() + "</br>");
                        e.printStackTrace();
                    }
                }
                else if(categoryIdentifier == 7){
                    try {
                        bubsBeans.InsertOrderDetailsBean iodb = new InsertOrderDetailsBean();

                        iodb.setDetail_id(Integer.parseInt(request.getParameter("user_detail_id")));
                        iodb.setOrder_detail_id(Integer.parseInt(request.getParameter("user_order_detail_id")));
                        iodb.setDetail_prod_id(Integer.parseInt(request.getParameter("user_detail_prod_id")));
                        iodb.setDetail_name(request.getParameter("user_detail_name"));
                        iodb.setDetail_price(Double.parseDouble(request.getParameter("user_detail_price")));
                        iodb.setDetail_sku(request.getParameter("user_detail_sku"));
                        iodb.setDetail_quantity(Integer.parseInt(request.getParameter("user_detail_quantity")));

                        iodb.InsertOrderDetailsData();
                        out.println("Your data has been entered <b>successfully!</b><br>");                           
                    } catch (Exception e) {
                        out.println("Data insertion failed!<br>");
                        out.println(e.getMessage() + "</br>");
                        e.printStackTrace();
                    }
                }
            }
        %>
        
        <!-- Function to display initial form to make user table selection -->
        <%!
            void printForm(JspWriter out) throws java.io.IOException{
                
            out.println("<form action='GetUserData.jsp' method='GET'>Select The "
                    + "Table You Would Like To Add Data To:&nbsp;<select name='bubs_table_selections'>"
                    + "<option name='order_details'>ORDERDETAILS</option><option name='orders'>"
                    + "ORDERS</option><option name='products'>PRODUCTS</option><option "
                    + "name='customer'>CUSTOMER</option><option name='suppliers'>"
                    + "SUPPLIERS</option><option name='payment'>PAYMENT</option>"
                    + "<option name='product_categories'>PRODUCTCATEGORIES</option>"
                    + "</select><br><br><input type='submit' value='Insert New Data' "
                    + "name='insert_new_data' /><br><br></form>");
            
           
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
            <h2 style="display: inline-block;">
                <img src="https://previews.123rf.com/images/maradaisy/maradaisy1902/maradaisy190200008/116525877-baby-clothes-vector-kid-models-children-clothing-set-girl-boy-cloth-child-garment-apparel-isolated-o.jpg" alt ="babyClothes">
            </h2>
        </div>
    </body>
</html>
