// Duku Henry Mugume 26/E/26521/PS
// Mayen Ayiik Mayen 26/E/26534/PS 

public class Customer {

    private int customerId;
    private String fullName;
    private String phone;
    private String address;
    
    public Customer(int customerId, String fullName, String phone, String address) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address; 
    }

    //getters
    public int getCustomerId() {
        return customerId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }

    //setters
     public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override 
    public String toString() {
        return "ID: " + customerId +
               "Name: " + fullName +
               "Phone: " + phone +
               "Address: " + address; 
    }

}
