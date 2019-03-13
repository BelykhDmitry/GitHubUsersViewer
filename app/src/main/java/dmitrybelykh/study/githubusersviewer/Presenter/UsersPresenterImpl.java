package dmitrybelykh.study.githubusersviewer.Presenter;

import java.util.ArrayList;
import java.util.List;

import dmitrybelykh.study.githubusersviewer.Model.User;
import dmitrybelykh.study.githubusersviewer.Model.UserModel;
import dmitrybelykh.study.githubusersviewer.Model.github.GithubService;
import dmitrybelykh.study.githubusersviewer.Model.github.GithubUsersModel;
import dmitrybelykh.study.githubusersviewer.View.UsersView;

public class UsersPresenterImpl implements UsersPresenter {

    private List<User> mUsers = new ArrayList<>(); //Временно. При смене фрагмента писать в базу
    private UsersView mListener = null;
    private UserModel mUserModel;

    public UsersPresenterImpl(GithubService service) {
        mUserModel = new GithubUsersModel(service);
    }

    @Override
    public void handleOnStart(UsersView view) {
        mListener = view;
        mListener.setUsers(mUsers);
        if (mUsers.size() == 0)
            loadUsers();
    }

    @Override
    public void handleOnStop() {
        mListener = null;
        mUserModel.cancelLoading();
    }

    @Override
    public void loadUsers() {
        long id = 0;
        if (mUsers.size() != 0) {
            id = mUsers.get(mUsers.size() - 1).getId();
        }
        mUserModel.getUsers(id, new UserModel.UsersModelCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                int size = mUsers.size();
                mUsers.addAll(response);
                if (mListener != null)
                    mListener.notifyUsersChanged(size, response.size());
            }

            @Override
            public void onError(Throwable error) {
                if (mListener != null)
                    mListener.onError();
            }
        });
    }

    public void onTerminate() {
        mUserModel.cancelLoading();
    }
}
