package dmitrybelykh.study.githubusersviewer.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import dmitrybelykh.study.githubusersviewer.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mUserList = null;
    private LoadMoreListener mListener;
    //private final float LOAD_FACTOR = 0.9f;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.mUserName.setText(user.getName());
        holder.mUserUrl.setText(user.getHtmlUrl());
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.avatar_placeholder)
                .into(holder.mAvatar);
        if (position == (getItemCount() - 1) && mListener != null) {
            mListener.loadMoreUsers();
        }
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void addUsers(List<User> newUsers) {
        mUserList = newUsers;
        notifyDataSetChanged();
    }

    public void subscribe(LoadMoreListener mListener) {
        this.mListener = mListener;
    }

    public void unsubscribe() {
        mListener = null;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView mUserName;
        private AppCompatImageView mAvatar;
        private AppCompatTextView mUserUrl;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.user_name);
            mAvatar = itemView.findViewById(R.id.user_avatar);
            mUserUrl = itemView.findViewById(R.id.user_url);
        }
    }

    public interface LoadMoreListener {
        public void loadMoreUsers();
    }
}
