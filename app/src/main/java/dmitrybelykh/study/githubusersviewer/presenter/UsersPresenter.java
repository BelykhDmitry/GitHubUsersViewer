package dmitrybelykh.study.githubusersviewer.presenter;

import dmitrybelykh.study.githubusersviewer.view.UsersView;

public interface UsersPresenter {
    public void handleOnStart(UsersView view);

    public void handleOnStop();

    public void loadUsers();

    public void onTerminate();
}
