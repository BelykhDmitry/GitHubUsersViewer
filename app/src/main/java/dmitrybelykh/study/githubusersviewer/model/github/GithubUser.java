package dmitrybelykh.study.githubusersviewer.model.github;

import com.google.gson.annotations.SerializedName;

import dmitrybelykh.study.githubusersviewer.model.User;

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
