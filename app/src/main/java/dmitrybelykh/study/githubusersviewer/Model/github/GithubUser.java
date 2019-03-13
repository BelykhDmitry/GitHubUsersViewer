package dmitrybelykh.study.githubusersviewer.Model.github;

import com.google.gson.annotations.SerializedName;

import dmitrybelykh.study.githubusersviewer.Model.User;

public class GithubUser {
    @SerializedName("login")
    String name;
    @SerializedName("avatar_url")
    String avatar;
    @SerializedName("id")
    long id;
    @SerializedName("html_url")
    String htmlUrl;

    public User mapToUser() {
        return new User(name, avatar, id, htmlUrl);
    }
}
