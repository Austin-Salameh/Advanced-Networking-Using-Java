/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertCustomerBean.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: The bean represents the model of Customer data for use in the
    CUSTOMER Bubs Duds table. Getters/setters are provided to allow for 
    property manipulation. This bean's InsertCustomerData() method allows for
    the data stored in each of the properties to be saved to a new record in the
    CUSTOMERS table.
 */
package bubsBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertCustomerBean {
    private int cust_id;
    private String cust_email;
    private String cust_password;
    private String cust_first_name;
    private String cust_last_name;
    private String cust_phone_num;
    private String cust_fax_num;
    private String cust_city;
    private String cust_state;
    private int cust_zip;
    private String cust_country;
    private char cust_email_verify;
    
    // empty constructor
    public InsertCustomerBean(){
        
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_password() {
        return cust_password;
    }

    public void setCust_password(String cust_password) {
        this.cust_password = cust_password;
    }

    public String getCust_first_name() {
        return cust_first_name;
    }

    public void setCust_first_name(String cust_first_name) {
        this.cust_first_name = cust_first_name;
    }

    public String getCust_last_name() {
        return cust_last_name;
    }

    public void setCust_last_name(String cust_last_name) {
        this.cust_last_name = cust_last_name;
    }

    public String getCust_phone_num() {
        return cust_phone_num;
    }

    public void setCust_phone_num(String cust_phone_num) {
        this.cust_phone_num = cust_phone_num;
    }

    public String getCust_fax_num() {
        return cust_fax_num;
    }

    public void setCust_fax_num(String cust_fax_num) {
        this.cust_fax_num = cust_fax_num;
    }

    public String getCust_city() {
        return cust_city;
    }

    public void setCust_city(String cust_city) {
        this.cust_city = cust_city;
    }

    public String getCust_state() {
        return cust_state;
    }

    public void setCust_state(String cust_state) {
        this.cust_state = cust_state;
    }

    public int getCust_zip() {
        return cust_zip;
    }

    public void setCust_zip(int cust_zip) {
        this.cust_zip = cust_zip;
    }

    
    public String getCust_country() {
        return cust_country;
    }

    public void setCust_country(String cust_country) {
        this.cust_country = cust_country;
    }

    public char getCust_email_verify() {
        return cust_email_verify;
    }

    public void setCust_email_verify(char cust_email_verify) {
        this.cust_email_verify = cust_email_verify;
    }
    
    public void InsertCustomerData(){
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            int cust_id = getCust_id();
            String cust_email = getCust_email();
            String cust_password = getCust_password();
            String cust_first_name = getCust_first_name();
            String cust_last_name = getCust_last_name();
            String cust_phone_num = getCust_phone_num();
            String cust_fax_num = getCust_fax_num();
            String cust_city = getCust_city();
            String cust_state = getCust_state();
            int cust_zip = getCust_zip();
            String cust_country = getCust_country();
            char cust_email_verify = getCust_email_verify();
            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            
            ps = con.prepareStatement("INSERT INTO CUSTOMER(Cust_ID, Cust_Email, Cust_Password, Cust_FirstName, Cust_LastName, Cust_PhoneNum, Cust_FaxNum, Cust_City, Cust_State, Cust_Zip, Cust_Country, Cust_EmailVerify) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, Integer.toString(cust_id));
            ps.setString(2, cust_email);
            ps.setString(3, cust_password);
            ps.setString(4, cust_first_name);
            ps.setString(5, cust_last_name);
            ps.setString(6, cust_phone_num);
            ps.setString(7, cust_fax_num);
            ps.setString(8, cust_city);
            ps.setString(9, cust_state);
            ps.setString(10, Integer.toString(cust_zip));
            ps.setString(11, cust_country);
            ps.setString(12, Character.toString(cust_email_verify));
            
            ps.executeUpdate();
            System.out.println("Data Entry Success!");
        } catch (Exception e) {
            System.out.println("Error inserting data!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
