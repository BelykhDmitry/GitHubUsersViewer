package dmitrybelykh.study.githubusersviewer.model.github;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users")
    Single<List<GithubUser>> getUsers(@Query("since") long id);
}
