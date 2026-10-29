package model;

import model.annotation.Id;
import model.annotation.MaxLength;
import java.io.Serializable;

public class Customer implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private static long customerCounter = 100;
    @MaxLength(50)
    private String name;
    private String email;
    private String mobile;
    @Id
    private final String customerId;
    private Address address;

    public Customer(String name, String email, String mobile) {
        this.customerId = generateCustomerId();
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    private static String generateCustomerId() { return "CUST" + ++customerCounter; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getCustomerId() { return customerId; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    @Override public Customer clone() throws CloneNotSupportedException { return (Customer) super.clone(); }
    @Override public String toString() { return customerId + " | " + name; }

    public static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private String line;
        private String city;
        private String pincode;
        public Address(String line, String city, String pincode) { this.line = line; this.city = city; this.pincode = pincode; }
        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }
    }
}
