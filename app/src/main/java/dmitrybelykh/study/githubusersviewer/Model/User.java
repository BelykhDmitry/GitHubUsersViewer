package dmitrybelykh.study.githubusersviewer.Model;

import java.util.Objects;

public class User {

    private String name;
    private String avatar;
    private long id;
    private String htmlUrl;

    public User(String name, String avatar, long id, String url) {
        this.name = name;
        this.avatar = avatar;
        this.id = id;
        this.htmlUrl = url;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public long getId() {
        return id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", id=" + id +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(htmlUrl, user.htmlUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, avatar, id, htmlUrl);
    }
}
