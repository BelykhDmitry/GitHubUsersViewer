package dmitrybelykh.study.githubusersviewer.model.github;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dmitrybelykh.study.githubusersviewer.model.User;
import dmitrybelykh.study.githubusersviewer.model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubUsersModel implements UserModel {

    private GithubService service;

    private Call<List<GithubUser>> githubUserCall = null;

    public GithubUsersModel(GithubService service) {
        this.service = service;
    }

    @Override
    public void getUsers(long lastUserId, UserModel.UsersModelCallback<List<User>> callback) {
        if (githubUserCall != null && !githubUserCall.isExecuted())
            return;
        Log.d("DDDD", "get");
        githubUserCall = service.getUsers(lastUserId);
        githubUserCall
                .enqueue(new Callback<List<GithubUser>>() {
                    @Override
                    public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                        if (response.code() == 200) {
                            ArrayList<User> users = new ArrayList<>(response.body().size());
                            for (GithubUser githubUser : response.body()) {
                                users.add(githubUser.mapToUser());
                            }
                            callback.onSuccess(users);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }

    @Override
    public void cancelLoading() {
        if (githubUserCall != null && githubUserCall.isExecuted())
            githubUserCall.cancel();
    }
}
