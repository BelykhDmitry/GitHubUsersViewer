package dmitrybelykh.study.githubusersviewer.model;

import java.util.List;

public interface UserModel {

    public void getUsers(long lastUserId, UsersModelCallback<List<User>> callback);

    public void cancelLoading();

    public interface UsersModelCallback<T> {
        public void onSuccess(T response);

        public void onError(Throwable error);
    }
}
