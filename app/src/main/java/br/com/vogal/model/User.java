package br.com.vogal.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lbvil on 11/10/2017.
 */

public class User {

    private String username;
    private String email;
    private String token;

    @SerializedName("profile_pic")
    private String profilePic;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }
}
