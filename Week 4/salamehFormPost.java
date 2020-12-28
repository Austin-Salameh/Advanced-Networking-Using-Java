/*Austin Salameh
 *Assignment 4.1
 *11/22/2020 
 *
 *Create a Servlet that displays a form when the doGet method is invoked. 
 *The form will contain a post action that directs the form post back to the same servlet, which in the doPost method will append the form data to a random access file.
 *After the form data has been appended to the file, respond back with the complete contents of the data file.
 *The form must contain a minimum of three input fields. The grade for this assignment will be based both on the functionality of the servlet and the appearance of the form post results. 
 *Name your servlet <yourName>FormPost and name the random access data file c:\temp\<yourname>week4.dat.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "salamehFormPost", urlPatterns = {"/salamehFormPost"})
public class salamehFormPost extends HttpServlet {

   @Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException{
    
response.setContentType("text/html");
PrintWriter output = response.getWriter();

//calling all methods to create the page
printHeader(output);
printForm(output);
printFooter(output);
}

public void printForm(PrintWriter output){
output.println("<form method='post' action='salamehFormPost'>");
output.println("<label>Client Name</label>");
output.println("<input type='text' name='clientName' size='20' maxlength='20' />");
output.println("<br />");
output.println("<label>Age Range</label>");
output.println("<select name='ageRange'>");
output.println("<option selected='selected' value='21-30'>21-30</option>");
output.println("<option value='31-40'>31-40</option>");
output.println("<option value='41-50'>41-50</option>");
output.println("<option value='51-64'>51-64</option>");
output.println("<option value='65+'>65+</option>");
output.println("</select>");
output.println("<br />");
output.println("<label>Male<input name='pfRadio' type='radio' value='Male' checked='checked' /></label>");
output.println("<label>Female<input name='pfRadio' type='radio' value='Female' /></label>");
output.println("<br />");
output.println("<input type='submit' />");
output.println("</form>");
output.println("<hr />");
}

//process the form and write to .dat file
public void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException{

String n = "\n";
String data = null;

RandomAccessFile randomAccessFile = new RandomAccessFile("C:/temp/Salamehweek4.dat", "rw");

//build the string to write to the file
StringBuffer buffer = new StringBuffer();

buffer.append("Client Name:");
buffer.append(request.getParameter("clientName"));
buffer.append(n);
buffer.append("Age Range:");
buffer.append(request.getParameter("ageRange"));
buffer.append(n);
buffer.append("Male/Female:");
buffer.append(request.getParameter("pfRadio"));
buffer.append(n);
buffer.append(n);

response.setContentType("text/html");

PrintWriter output = response.getWriter();

printHeader(output);
printForm(output);

//write to the file and set it up to be read
randomAccessFile.seek(randomAccessFile.length());
randomAccessFile.writeChars(buffer.toString());
randomAccessFile.seek(0);

//read file an output to the web page
while((data = randomAccessFile.readLine()) != null){
output.println(data + "<br />");
}
printFooter(output);
}

public void printHeader(PrintWriter output){
output.println("<!DOCTYPE html><html lang='en'><title>Assignment 4.1</title><body><div><h1>Client Information</h1>");
}

public void printFooter(PrintWriter output){
output.println("</div></body></html>");
}
}
 