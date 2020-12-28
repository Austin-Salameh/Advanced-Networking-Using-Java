package week31;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
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
public class ReadWriteFile2Servlet extends HttpServlet {

    static Charset myCharset = Charset.forName("US-ASCII");
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try{
    
            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println("Read/Write File Servlet");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");

            writeAndReadFile(out);

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
        finally{
            out.close();
        }
    }
    
    private synchronized void writeAndReadFile(PrintWriter out){

        try{

            Path target = Paths.get("C:/Temp/data3.dat");

            Calendar calendar = Calendar.getInstance();
            String s1 = calendar.getTime().toString();
            String s2 = "Random number = " + String.valueOf((int)(Math.random() * 1000 + 1));

            List <String> linesToWrite = Arrays.asList(s1, s2);

            if(Files.notExists(target)){

                Files.write(target, linesToWrite, myCharset);
            }
            else{

                Files.write(target, linesToWrite, myCharset, StandardOpenOption.APPEND);
            }

            Path source = Paths.get("C:/Temp/data3.dat");

            List <String> linesRead = null;

            linesRead = Files.readAllLines(source, myCharset);      

            if(linesRead != null){

                for(String line : linesRead){

                    out.println(line+ "<br />\n");
                }
            }
        }
        catch(IOException e){

        }
    }
}
