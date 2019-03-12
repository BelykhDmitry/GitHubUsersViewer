package dmitrybelykh.study.githubusersviewer.Model.github;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dmitrybelykh.study.githubusersviewer.Model.User;
import dmitrybelykh.study.githubusersviewer.Model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubUsersModel implements UserModel {

    private GithubService service;

    private Call<List<GithubUser>> githubUserCall;
    private Call<List<GithubUser>> githubMoreUserCall;


    public GithubUsersModel(GithubService service) {
        this.service = service;
    }

    @Override
    public void getUsers(UserModel.UsersModelCallback<List<User>> callback) {
        Log.d("DDDD", "get");
        githubUserCall = service.getUsers(0);
        githubUserCall
                .enqueue(new Callback<List<GithubUser>>() {
                    @Override
                    public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                        ArrayList<User> users = new ArrayList<>(response.body().size());
                        for (GithubUser githubUser : response.body()) {
                            users.add(githubUser.mapToUser());
                        }
                        callback.onSuccess(users);
                    }

                    @Override
                    public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }

    @Override
    public void getMoreUsers(UserModel.UsersModelCallback<List<User>> callback) {
//        if (callMoreUsers != null && !callMoreUsers.isExecuted())
//            return;
//        callMoreUsers = githubService.getUsers(userId);
//        callMoreUsers.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                mUserAdapter.addUsers(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(getContext(), R.string.error_loading_message, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void cancelLoading() {
        if (githubUserCall != null && githubUserCall.isExecuted())
            githubUserCall.cancel();
        if (githubMoreUserCall != null && githubMoreUserCall.isExecuted())
            githubMoreUserCall.cancel();
    }
}
