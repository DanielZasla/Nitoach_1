public class User {
    static int UserCounter = 1;
    String _id;
    String login_id;
    String password;
    UserState state;

    public User(String login_id, String password) {
        this._id = String.format("user%d",UserCounter);
        UserCounter++;
        this.login_id = login_id;
        this.password = password;
        state = UserState.New;
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
