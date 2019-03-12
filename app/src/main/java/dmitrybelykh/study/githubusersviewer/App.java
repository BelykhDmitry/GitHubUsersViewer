package dmitrybelykh.study.githubusersviewer;

import android.app.Application;

import dmitrybelykh.study.githubusersviewer.Model.RetrofitServices;
import dmitrybelykh.study.githubusersviewer.Presenter.UsersPresenter;
import dmitrybelykh.study.githubusersviewer.Presenter.UsersPresenterImpl;

public class App extends Application {

    private RetrofitServices retrofitServices;

    private UsersPresenterImpl usersPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
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

    @Override
    public void onTerminate() {
        if (usersPresenter != null)
            usersPresenter.onTerminate();
        super.onTerminate();
    }
}
