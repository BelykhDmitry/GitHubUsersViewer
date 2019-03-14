package dmitrybelykh.study.githubusersviewer;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import dmitrybelykh.study.githubusersviewer.model.RetrofitServices;
import dmitrybelykh.study.githubusersviewer.presenter.UsersPresenter;
import dmitrybelykh.study.githubusersviewer.presenter.UsersPresenterImpl;

public class App extends Application {

    private RetrofitServices retrofitServices;

    private UsersPresenterImpl usersPresenter;

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);

        retrofitServices = new RetrofitServices(this);
        usersPresenter = null;
    }

    public RetrofitServices getRetrofitServices() {
        return retrofitServices;
    }

    public UsersPresenter getUsersPresenter() {
        if (usersPresenter == null) {
            usersPresenter = new UsersPresenterImpl(retrofitServices.getGithubService());
        }
        return usersPresenter;
    }
}
