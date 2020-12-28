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
public class FormPostGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      PrintWriter out = response.getWriter();

      printHeader(out);

      out.println("<div><h1>GET</h1></div>");

      printForm(out);
      printFooter(out);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      PrintWriter out = response.getWriter();

      try{
      
          printHeader(out);

          out.println("<div><h1>POST</h1></div>");

          printForm(out);
          printFooter(out);
      }
      finally{
          out.close();
      }
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

    out.println("<form method='get' action='FormPostGetServlet'>");
    out.println("<div>GET Form</div>");
    out.println("<input type='submit' />");
    out.println("</form>");

    out.println("<form method='post' action='FormPostGetServlet'>");
    out.println("<div>POST Form</div>");
    out.println("<input type='submit' />");
    out.println("</form>");

    out.println("<a href='FormPostGetServlet' method='get'>GET Link</a>");
    out.println("<br>");
    out.println("<a href='FormPostGetServlet' method='post'>POST Link</a>");
  }
}
