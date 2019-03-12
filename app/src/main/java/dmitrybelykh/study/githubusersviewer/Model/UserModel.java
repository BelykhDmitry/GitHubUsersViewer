package dmitrybelykh.study.githubusersviewer.Model;

import java.util.List;

public interface UserModel {

    public void getUsers(UsersModelCallback<List<User>> callback);

    public void getMoreUsers(UserModel.UsersModelCallback<List<User>> callback);

    public void cancelLoading();

    public interface UsersModelCallback<T> {
        public void onSuccess(T response);

        public void onError(Throwable error);
    }
}
