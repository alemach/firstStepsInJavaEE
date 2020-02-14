package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    byte enable = 1;
    byte superadmin = 0;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,3}$";
    private static final String PASSWD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    public Admin() {

    }

    public byte getSuperadmin() {
        return superadmin;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        Pattern compiledPattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = compiledPattern.matcher(email);
        if (matcher.matches()) {
            this.email = email;
            return true;
        } else {
            return false;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean setNewPassword(String password) {
        Pattern compiledPattern = Pattern.compile(PASSWD_REGEX);
        Matcher matcher = compiledPattern.matcher(password);
        if (matcher.matches()) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
            return true;
        } else {
            return false;
        }
    }

    public byte getEnable() {
        return enable;
    }

    public boolean comparePassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

}
