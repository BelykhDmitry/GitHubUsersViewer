package dmitrybelykh.study.githubusersviewer.view;

import java.util.List;

import dmitrybelykh.study.githubusersviewer.model.User;

public interface UsersView {
    public void setUsers(List<User> users);

    public void notifyUsersChanged(int position, int length);

    public void onError();
}
