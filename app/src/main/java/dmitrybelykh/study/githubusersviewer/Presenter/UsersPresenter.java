package dmitrybelykh.study.githubusersviewer.Presenter;

import dmitrybelykh.study.githubusersviewer.View.UsersView;

public interface UsersPresenter {
    public void onAttach(UsersView view);

    public void onDetach();

    public void loadUsers();
}
