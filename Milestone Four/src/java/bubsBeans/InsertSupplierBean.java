/*
    Group Foxtrot :: Taylor Imhof, Austin Salameh, Kaitlan Roberts, Jason Christian
    CIS 308/404 Milestone #4 Pilot Project
    
    File Name:      InsertSupplierBean.java
    Created:        1/4/2021
    Last Update:    1/9/2021

    Description: The bean represents the model of Suppliers data for 
    use in the SUPPLIERS Bubs Duds table. Getters/setters are provided to allow 
    for property manipulation. This bean's InsertSupplierData() method allows 
    for the data stored in each of the properties to be saved to a new record 
    in the SUPPLIERS table.
 */
package bubsBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertSupplierBean {
    
    private int supplier_id;
    private String supplier_name;
    private String contact_fname;
    private String contact_lname;
    private String contact_title;
    private String contact_phone_num;
    private String company_addr;
    private String local_or_private;
    
    // empty constructor
    public InsertSupplierBean(){
        
    }
    
    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getContact_fname() {
        return contact_fname;
    }

    public void setContact_fname(String contact_fname) {
        this.contact_fname = contact_fname;
    }

    public String getContact_lname() {
        return contact_lname;
    }

    public void setContact_lname(String contact_lname) {
        this.contact_lname = contact_lname;
    }

    public String getContact_title() {
        return contact_title;
    }

    public void setContact_title(String contact_title) {
        this.contact_title = contact_title;
    }

    public String getContact_phone_num() {
        return contact_phone_num;
    }

    public void setContact_phone_num(String contact_phone_num) {
        this.contact_phone_num = contact_phone_num;
    }

    public String getCompany_addr() {
        return company_addr;
    }

    public void setCompany_addr(String company_addr) {
        this.company_addr = company_addr;
    }

    public String getLocal_or_private() {
        return local_or_private;
    }

    public void setLocal_or_private(String local_or_private) {
        this.local_or_private = local_or_private;
    }
    
    public void InsertSupplierData(){     
        Connection con = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        
        int supplier_id = getSupplier_id();
        String supplier_name = getSupplier_name();
        String contact_fname = getContact_fname();
        String contact_lname = getContact_lname();
        String contact_title = getContact_title();
        String contact_phone_num = getContact_phone_num();
        String company_addr = getCompany_addr();
        String local_or_private = getLocal_or_private();
        
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");
            stmt = con.createStatement();
            
            ps = con.prepareStatement("INSERT INTO SUPPLIERS(Supplier_ID, Supplier_Name, "
                    + "Contact_fName, Contact_lName, Contact_Title, Contact_Phone_Num, "
                    + "Company_Addr, Local_Or_Private) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, Integer.toString(supplier_id));
            ps.setString(2, supplier_name);
            ps.setString(3, contact_fname);
            ps.setString(4, contact_lname);
            ps.setString(5, contact_title);
            ps.setString(6, contact_phone_num);
            ps.setString(7, company_addr);
            ps.setString(8, local_or_private);            
            ps.executeUpdate();
            
            System.out.println("Data Entry Success!");
        } catch (Exception e) {
            System.out.println("Error inserting data!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }
    
}
