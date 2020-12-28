<%-- 
Austin Salameh
Assignment 8.1

Create a new Web application titled <yourname>Week8. Next, create a JSP that displays a form when the doGet method is invoked. 
The form will contain a post action that directs the form post back to the same JSP, which in the doPost method will save the form data to a database using a Java Bean and a Custom Tag. 
Use your Oracle account to make the DB connection. After the form data has been saved to the database, respond back with a query from the database displaying all 
the current records contained in the database, in an appealing format. The form must contain a minimum of three input fields. The grade for this assignment 
will be based both on the functionality of the servlet and the appearance of the form post results. Name your JSP <yourName>FormPost5 and name the application <yourname>Week8. 
Create a Web archive file and attach to this assignment. Do not copy (cut and paste) any example code, create your own code and use the examples as a guide.
--%>

<%@page import="java.sql.*"%>
<%@page import="oracle.jdbc.OracleResultSetMetaData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/customtag" prefix="ex" %> 

<!DOCTYPE html>

<html>
<head>    
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Survey</title> 
</head>
    <body bgcolor="aquamarine">
        <jsp:useBean id="client" class="Beans.FirstJavaBean">
            <jsp:setProperty name="client" property="fname" param="FNAME" />
            <jsp:setProperty name="client" property="lname" param="LNAME" />
            <jsp:setProperty name="client" property="phone" param="PHONE" />
            <jsp:setProperty name="client" property="height" param="HEIGHT" />
            <jsp:setProperty name="client" property="weight" param="WEIGHT" />
            <jsp:setProperty name="client" property="gender" param="GENDER" />
        </jsp:useBean>
        
       

<%
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            client.createTable();
            
if(request.getMethod().equals("GET")) {

%>


    <center><form action='SalamehFormPost4.jsp' method='post' >
<h1> Fill Out the Health Survey For Your Free Screening!</h1>
First Name: <br>
<input type='text' name='FNAME' /><br>
Last Name: <br>
<input type='text' name='LNAME' /><br>
Phone Number: <br>
<input type='text' name='PHONE' /> <br>
Height: <br>
<input type='text' name='HEIGHT' /> <br>
Weight: <br>
<input type='text' name='WEIGHT' /> <br>
Gender: <br>
<input type='radio' name='GENDER' value='M' required/><label>Male</label>
<input type='radio' name='GENDER' value='F' required/><label>Female</label><br>
<input type='submit' value='Submit' />

</form> <h2><ex:hello /></h2></center>

<%
            }
            if(request.getMethod().equals("POST")) {
            
                client.insert();

                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
                
                ps = con.prepareStatement("SELECT * FROM clientdata");
                rs = ps.executeQuery();           
%>

    <center><h1>New Clients</h1>

   <table border="1" bgcolor="white">    
       <h2><ex:thanks /></h2>
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Phone</th>
    <th>Height</th>
    <th>Weight</th>
    <th>Gender</th>
  </tr>
        <%
                while(rs.next()) {
        %>
  <tr>
    <td><%=rs.getString("fname")%></td>
    <td><%=rs.getString("lname")%></td>
    <td><%=rs.getString("phone")%></td>
    <td><%=rs.getString("height")%></td>
    <td><%=rs.getString("weight")%></td>
    <td><%=rs.getString("gender")%></td>    
  </tr>
  
     <%
            
                }
}
        %>

 </table><br><br></center>
    </body>
</html>


