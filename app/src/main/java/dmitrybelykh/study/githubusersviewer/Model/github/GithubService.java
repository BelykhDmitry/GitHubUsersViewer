package dmitrybelykh.study.githubusersviewer.Model.github;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users")
    Call<List<GithubUser>> getUsers(@Query("since") long id);
}
