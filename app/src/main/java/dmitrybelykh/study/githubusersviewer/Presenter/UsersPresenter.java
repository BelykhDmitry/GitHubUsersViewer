package dmitrybelykh.study.githubusersviewer.Presenter;

import dmitrybelykh.study.githubusersviewer.View.UsersView;

public interface UsersPresenter {
    public void handleOnStart(UsersView view);

    public void handleOnStop();

    public void loadUsers();
}
