package dmitrybelykh.study.githubusersviewer.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmitrybelykh.study.githubusersviewer.App;
import dmitrybelykh.study.githubusersviewer.R;
import dmitrybelykh.study.githubusersviewer.model.User;
import dmitrybelykh.study.githubusersviewer.model.UserAdapter;
import dmitrybelykh.study.githubusersviewer.presenter.UsersPresenter;

public class UsersFragment extends Fragment implements UsersView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;
    private UserAdapter mUserAdapter;

    private UsersPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mUserAdapter = new UserAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mUserAdapter);
        mRecyclerView.setHasFixedSize(true);

        mPresenter = ((App) getActivity().getApplication()).getUsersPresenter();

        mUserAdapter.subscribe(() -> mPresenter.loadUsers());

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.handleOnStart(this);
    }

    @Override
    public void onStop() {
        mPresenter.handleOnStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        if (getActivity().isFinishing())
            mPresenter.onTerminate();
        super.onDestroyView();
    }

    @Override
    public void setUsers(List<User> users) {
        mUserAdapter.addUsers(users);
    }

    @Override
    public void notifyUsersChanged(int position, int length) {
        mUserAdapter.notifyItemRangeInserted(position, length);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), R.string.error_loading_message, Toast.LENGTH_SHORT).show();
    }
}
