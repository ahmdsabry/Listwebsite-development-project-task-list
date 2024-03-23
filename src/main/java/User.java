 
public class User {
    private String fname;
    private String lname;
    private String uname;
    private String pass;
    private String phone;
    public User(String fname, String lname, String uname, String pass, String phone) {
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.pass = pass;
        this.phone = phone;
    }
    public User(){}

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
