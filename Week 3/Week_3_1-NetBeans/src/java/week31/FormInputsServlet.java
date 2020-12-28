package week31;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dpayne
 */
/*
 *
 * Professor Darrell Payne
 * Bellevue University
 *
 */
public class FormInputsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      PrintWriter out = response.getWriter();

      try{
      
          printHeader(out);

          out.println("<div><h1>GET</h1></div>");

          printForm(out);
          printFooter(out);
      }
      finally{
          
          out.close();
      }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      PrintWriter out = response.getWriter();

      printHeader(out);

      out.println("<div><h1>Output Form Data</h1></div>");

      out.println("<hr>");

      out.println(request.getParameter("hiddenInput"));
      out.println("<br>");
      out.println(request.getParameter("select"));
      out.println("<br>");
      out.println(request.getParameter("textarea"));
      out.println("<br>");
      out.println(request.getParameter("radioButton"));
      out.println("<br>");

      if(request.getParameter("checkBox_1[]") == null){

        out.println("No checkboxes were selected - 8.");
      }
      else{

        String[] checkBoxSelected = request.getParameterValues("checkBox_1[]");

        out.println("<ul>");

        for(String value : checkBoxSelected){

          out.println("<li>" + value + "</li>");
        }

        out.println("</ul>");
      }
      out.println("<br>");

      out.println(request.getParameter("password_1"));

      out.println("<br>");

      out.println("<hr>");

      printForm(out);
      printFooter(out);
    }
    
      public void printHeader(PrintWriter out){

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>");
    out.println("Form Post & Get");
    out.println("</title>");
    out.println("<meta charset='utf-8'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<div>");
  }

  public void printFooter(PrintWriter out){

    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
  }

  public void printForm(PrintWriter out){


    out.println("<form action='FormInputsServlet' method='post'>");
    out.println("<p>");
    out.println("<input type='hidden' name='hiddenInput' value='hiddenValue' />");
    out.println("</p>");
    out.println("<p>");
    out.println("<label>");
    out.println("Option Selection");
    out.println("<br />");
    out.println("</label>");
    out.println("<select name='select'>");
    out.println("<option selected='selected' value='1'>Selection one</option>");
    out.println("<option value='2'>Selection two</option>");
    out.println("<option value='3'>Selection three</option>");
    out.println("<option value='4'>Selction four</option>");
    out.println("</select>");
    out.println("</p>");
    out.println("<p>");
    out.println("<label>");
    out.println("Text Area");
    out.println("<br />");
    out.println("</label>");
    out.println("<textarea name='textarea' rows='6' cols='20'></textarea>");
    out.println("</p>");
    out.println("<p>");
    out.println("<label>");
    out.println("Radio Button 1");
    out.println("<input name='radioButton' type='radio' value='Radio_1' checked='checked' />");
    out.println("</label>");
    out.println("<br />");
    out.println("<label>");
    out.println("Radio Button 2");
    out.println("<input name='radioButton' type='radio' value='Radio_2' />");
    out.println("</label>");
    out.println("<br />");
    out.println("<label>");
    out.println("Radio Button 3");
    out.println("<input name='radioButton' type='radio' value='Radio_3' />");
    out.println("</label>");
    out.println("<br />");
    out.println("<label>");
    out.println("Radio Button 4");
    out.println("<input name='radioButton' type='radio' value='Radio_4' />");
    out.println("</label>");
    out.println("</p>");
    out.println("<p>");
    out.println("<label>");
    out.println("Check Box 1");
    out.println("<input name='checkBox_1[]' type='checkbox' value='CheckBox_1' />");
    out.println("<br />");
    out.println("</label>");
    out.println("<label>");
    out.println("Check Box 2");
    out.println("<input name='checkBox_1[]' type='checkbox' value='CheckBox_2' />");
    out.println("<br />");
    out.println("</label>");
    out.println("<label>");
    out.println("Check Box 3");
    out.println("<input name='checkBox_1[]' type='checkbox' value='CheckBox_3' />");
    out.println("<br />");
    out.println("</label>");
    out.println("<label>");
    out.println("Check Box 4");
    out.println("<input name='checkBox_1[]' type='checkbox' value='CheckBox_4' />");
    out.println("<br />");
    out.println("</label>");
    out.println("</p>");
    out.println("<p>");
    out.println("<label>");
    out.println("Password:");
    out.println("<input name='password_1' type='password' size='15' />");
    out.println("</label>");
    out.println("</p>");
    out.println("<p>");
    out.println("<input type='submit' value='Submit' />");
    out.println("</p>");
    out.println("<p>");
    out.println("<input type='reset' value='Clear Entries' />");
    out.println("</p>");
    out.println("</form>");
  }
}
