package dmitrybelykh.study.githubusersviewer.View;

import java.util.List;

import dmitrybelykh.study.githubusersviewer.Model.User;

public interface UsersView {
    public void setUsers(List<User> users);

    public void notifyUsersChanged(int position, int length);

    public void onError();
}
