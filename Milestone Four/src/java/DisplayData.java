/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      DisplayData.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: This servlet provides the user with the ability to view the
    data from each of the seven Bubs Duds' database tables. When the servlet's
    doGet() method is invoked, a simple form is displayed to allow for the user
    to select which table's data they want to have displayed form a drop-down
    list. After making their selection and hitting the 'Display Data' button,
    the servlet's doPost() method is invoked. 

    Using a series of nested if statements, the results of a SELECT * FROM 
    <tableName> is stored in a new ResultSet object. Then, the results of the
    ResultSet object are displayed back to the user in tabular format.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/DisplayData"})
public class DisplayData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Drop Tables</title></head>");
        out.println("<h1 style=\"color:blue; text-align: center; font-size: 50px;\">BubsDubs"
                + "<img src=\"https://logo.stocklight.com/ASX/BUB.png\" width=\"80\" height=\"80\" >"
                + "</h1>\n");

        out.println("<body style=\"text-align: center;\"><h1>Display Data</h1>"
            + "<h2><u>Table Names</u></h2>"
        );

        out.println("<form action='DisplayData' method='POST'><label>Select"
                + " the Table You Would Like To Display The Data From&nbsp;</label>"
                + "<select name='display_table_selections'><option name='order_details'>"
                + "ORDERDETAILS</option><option name='orders'>ORDERS</option>"
                + "<option name='products'>PRODUCTS</option><option name='CUSTOMER'>"
                + "CUSTOMER</option><option name='suppliers'>SUPPLIERS</option>"
                + "<option name='payment'>PAYMENT</option><option name='product_categories'>"
                + "PRODUCTCATEGORIES</option></select><br><br><input type='submit' "
                + "value='Display Data' name='display_data_btn' width='200'/><br><br></form>");

        out.println(
        "<div style=\"text-align: center;\">\n" +
        "<form style=\"display: inline-block;\" action=\"index.html\">\n" +
            "<input type=\"submit\" value=\"Main Menu\" />\n" +
        "</form>\n" +
        "</div>"
        );

        out.println("<div><h2 style=\"text-align: center\">"
            + "<img src=\"https://previews.123rf.com/images/maradaisy/maradaisy1902/"
            + "maradaisy190200008/116525877-baby-clothes-vector-kid-models-children-"
            + "clothing-set-girl-boy-cloth-child-garment-apparel-isolated-o.jpg\" "
            + "alt =\"babyClothes\"></h2></div>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletOutputStream out = response.getOutputStream();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean isSuccessful = true;
      
        // page header
        out.println("<html>");
        out.println("<head><title>Display Table</title></head>");
        out.println("<h1 style=\"color:blue; text-align: center; font-size: 50px;\">BubsDubs"
                + "<img src=\"https://logo.stocklight.com/ASX/BUB.png\" width=\"80\" height=\"80\" >"
                + "</h1>\n");
        out.println("<body style=\"text-align: center;\"><h1>Display Data</h1>"
                + "<h2><u>Table Data</u></h2>"
            );
        
        // establishes connection to the DB
        try {
            // set up DB driver
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            //Connect to the URL
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student1","pass");

            // Execute a SELECT statement
            stmt = con.createStatement();
            
            // selects all data from whatever table was selected by the user from drop down
            // stores results of query in resultset object
            if(request.getParameter("display_table_selections").equals("ORDERDETAILS")){
                rs = stmt.executeQuery("SELECT * FROM ORDERDETAILS");
            }
            else if(request.getParameter("display_table_selections").equals("ORDERS")){
                rs = stmt.executeQuery("SELECT * FROM ORDERS");
            }
            else if(request.getParameter("display_table_selections").equals("PRODUCTS")){
                rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
            }
            else if(request.getParameter("display_table_selections").equals("CUSTOMER")){
                rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
            }
            else if(request.getParameter("display_table_selections").equals("SUPPLIERS")){
                rs = stmt.executeQuery("SELECT * FROM SUPPLIERS");
            }
            else if(request.getParameter("display_table_selections").equals("PAYMENT")){
                rs = stmt.executeQuery("SELECT * FROM PAYMENT");
            }
            else if(request.getParameter("display_table_selections").equals("PRODUCTCATEGORIES")){
                rs = stmt.executeQuery("SELECT * FROM PRODUCTCATEGORIES");
            }
        } catch(Exception e){
            out.println("<u>Error: " + e.getMessage() + "</u><br/><br/>");
            e.printStackTrace();
            isSuccessful = false;
        }
        
        // display contents of resultset object back to the browser in tabular format
        if(isSuccessful == true){
            try{
                out.println("<table border='1' bgcolor='#34eb95' align='center'>");

                out.println("<tr>");

                // dispay column names first
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    out.println("<td>");
                    out.print((rs.getMetaData().getColumnName(i)).trim());
                    out.println("</td>");
                }

                out.println("</tr>");

                while(rs.next()){
                    out.println("<tr>");

                    // get each columns values for each record
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        out.println("<td>");
                        out.print((rs.getString(i)).trim());
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }            
                out.println("</table><br><br>");
            } catch(SQLException e){
                out.println("<b>There was an error when attempting to display the contents of your selected table</b><br/>");
                out.println("<u>Error: " + e.getMessage() + "</u><br/>");
            }
        }        
        
        // release DB connections
        try {
            stmt.close();
            con.close();
            System.out.println("DB connections released successfully!");
        } catch (Exception e) {
              e.printStackTrace();
              out.println("<b>There was an error when attempting to release DB connections</b></br>");
              out.println("<u>Error: " + e.getMessage() + "</u><br/>");
        }
    
        
        out.println(
                "<div style=\"text-align: center;\">\n" +
                "<form style=\"display: inline-block;\" action=\"index.html\">\n" +
                    "<input type=\"submit\" value=\"Main Menu\" />\n" +
                "</form>\n" +
                "</div>"
                );

        out.println("<div><h2 style=\"text-align: center\">"
                + "<img src=\"https://previews.123rf.com/images/maradaisy/maradaisy1902/"
                + "maradaisy190200008/116525877-baby-clothes-vector-kid-models-children-"
                + "clothing-set-girl-boy-cloth-child-garment-apparel-isolated-o.jpg\" "
                + "alt =\"babyClothes\"></h2></div>");
    }
}

