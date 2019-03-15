package dmitrybelykh.study.githubusersviewer.model;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import dmitrybelykh.study.githubusersviewer.model.github.GithubService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {

    private GithubService githubService;

    public RetrofitServices(Context context) {
        createRetrofitInstance(context);
    }

    public GithubService getGithubService() {
        return githubService;
    }

    private void createRetrofitInstance(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        ChuckInterceptor chuckInterceptor = new ChuckInterceptor(context);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(chuckInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        githubService = retrofit.create(GithubService.class);
    }
}
