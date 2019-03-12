package dmitrybelykh.study.githubusersviewer.Presenter;

import java.util.List;

import dmitrybelykh.study.githubusersviewer.Model.User;
import dmitrybelykh.study.githubusersviewer.Model.UserModel;
import dmitrybelykh.study.githubusersviewer.Model.github.GithubService;
import dmitrybelykh.study.githubusersviewer.Model.github.GithubUsersModel;
import dmitrybelykh.study.githubusersviewer.View.UsersView;

public class UsersPresenterImpl implements UsersPresenter {

    private List<User> mUsers; //Временно. При смене фрагмента писать в базу
    private UsersView mListener = null;
    private UserModel mUserModel;

    public UsersPresenterImpl(GithubService service) {
        mUserModel = new GithubUsersModel(service);
    }

    @Override
    public void onAttach(UsersView view) {
        mListener = view;
    }

    @Override
    public void onDetach() {
        mListener = null;
        mUserModel.cancelLoading();
    }

    @Override
    public void loadUsers() {
        mUserModel.getUsers(new UserModel.UsersModelCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                //mUsers.addAll(response);
                if (mListener != null)
                    mListener.setUsers(response);
            }

            @Override
            public void onError(Throwable error) {
                if (mListener != null)
                    mListener.onError();
            }
        });
    }

    @Override
    public void loadMoreUsers() {
        mUserModel.getMoreUsers(new UserModel.UsersModelCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {

            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public void onTerminate() {
        mUserModel.cancelLoading();
    }
}
