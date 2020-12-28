/*
 * Austin Salameh - 11/15/2020 - Bellevue University
 * Assignment 3.1
 * This servlet will read the txt file in the temp folder and output the text to the browser.
 */

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Salameh
 */

@WebServlet(urlPatterns = {"/Assignment_3_1"})
public class Assignment_3_1 extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
{
    // output an HTML page
    response.setContentType("text/html;charset=UTF-8");

    // Print title, h1, and h2 to the page in html 
    try ( PrintWriter out = response.getWriter()) {
    out.println("<html>");
    out.println("<head><title>Assignment 3.1</title></head>");
    out.println("<body><center><h1>Austin Salameh</h1></center>");
    out.println("<body><center><h2>Assignment 3.1</h2></center>");

try
       (// This part of the code will print the file
        InputStream in = new BufferedInputStream (new FileInputStream("C:\\temp\\servlet1.dat") ) // very important
    ) {
//Opening input file stored at c:\temp
        int ch;
while ((ch = in.read()) !=-1)
           {
out.print((char)ch);
}
}
       catch(IOException e)
       {
           out.println(""+e);
       }
            out.println("</body></html>");
        }
    }
}
