package ba.unsa.etf.rpr.projekat;

public class Manager {
    private String username = "admin";
    private String password = "admin";

    public Manager() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
