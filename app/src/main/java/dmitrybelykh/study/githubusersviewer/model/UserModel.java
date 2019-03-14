package dmitrybelykh.study.githubusersviewer.model;

import java.util.List;

import io.reactivex.Single;

public interface UserModel {

    public Single<List<User>> getUsers(long lastUserId);

    public interface UsersModelCallback<T> {
        public void onSuccess(T response);

        public void onError(Throwable error);
    }
}
