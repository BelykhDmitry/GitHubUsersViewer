package dmitrybelykh.study.githubusersviewer.Model;

import java.util.Objects;

public class User {

    private String name;
    private String avatar;
    private long id;

    public User(String name, String avatar, long id) {
        this.name = name;
        this.avatar = avatar;
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(avatar, user.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, avatar);
    }
}
