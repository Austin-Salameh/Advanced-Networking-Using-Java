/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertPaymentBean.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: The bean represents the model of Payment data for use in 
    the PAYMENTS Bubs Duds table. Getters/setters are provided to allow for 
    property manipulation. This bean's InsertPaymentData() method allows for
    the data stored in each of the properties to be saved to a new record in the
    PAYMENT table.
 */
package bubsBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertPaymentBean {
    private int payment_id;
    private String payment_type;
    private char payment_accepted;
    
    public InsertPaymentBean(){
        
    }
    
    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public char getPayment_accepted() {
        return payment_accepted;
    }

    public void setPayment_accepted(char payment_accepted) {
        this.payment_accepted = payment_accepted;
    }
    
    public void InsertPaymentData(){
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            int payment_id = getPayment_id();
            String payment_type = getPayment_type();
            char payment_accepted = getPayment_accepted();
            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            
            ps = con.prepareStatement("INSERT INTO PAYMENT(Payment_ID, Payment_Type, Payment_Accepted) VALUES(?,?,?)");
            ps.setString(1, Integer.toString(payment_id));
            ps.setString(2, payment_type);
            ps.setString(3, Character.toString(payment_accepted));
            ps.executeUpdate();
            
            System.out.println("Data Entry Success!");
        } catch (Exception e) {
            System.out.println("Error inserting data!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
