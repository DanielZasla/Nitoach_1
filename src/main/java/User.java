public class User {
    String login_id;
    String password;
    UserState state;

    public User(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_id() {
        return login_id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }
}
