package dmitrybelykh.study.githubusersviewer.presenter;

import java.util.ArrayList;
import java.util.List;

import dmitrybelykh.study.githubusersviewer.model.User;
import dmitrybelykh.study.githubusersviewer.model.UserModel;
import dmitrybelykh.study.githubusersviewer.model.github.GithubService;
import dmitrybelykh.study.githubusersviewer.model.github.GithubUsersModel;
import dmitrybelykh.study.githubusersviewer.view.UsersView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UsersPresenterImpl implements UsersPresenter {

    private List<User> mUsers = new ArrayList<>(); //Временно. При смене фрагмента писать в базу
    private int size = 0;
    private UsersView mListener = null;
    private UserModel mUserModel;
    private Disposable disposable;

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
    }

    @Override
    public void loadUsers() {
        long id = 0;
        if (size != 0) {
            id = mUsers.get(mUsers.size() - 1).getId();
        }
        if (disposable == null || disposable.isDisposed()) {
            disposable = mUserModel.getUsers(id)
                    .retry(2)
                    .doOnSuccess(list -> mUsers.addAll(list))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter(users -> mListener != null)
                    .subscribe(users -> {
                                mListener.notifyUsersChanged(size, users.size());
                                size = mUsers.size();
                            },
                            error -> mListener.onError());
        }
    }

    @Override
    public void onTerminate() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
