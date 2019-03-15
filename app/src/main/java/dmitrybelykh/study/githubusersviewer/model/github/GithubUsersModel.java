package dmitrybelykh.study.githubusersviewer.model.github;

import java.util.List;

import dmitrybelykh.study.githubusersviewer.model.User;
import dmitrybelykh.study.githubusersviewer.model.UserModel;
import io.reactivex.Observable;
import io.reactivex.Single;

public class GithubUsersModel implements UserModel {

    private GithubService service;

    public GithubUsersModel(GithubService service) {
        this.service = service;
    }

    @Override
    public Single<List<User>> getUsers(long lastUserId) {
        return service.getUsers(lastUserId)
                .flatMapObservable(Observable::fromIterable)
                .map(GithubUser::mapToUser)
                .toList();
    }
}
