package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.domain.UserCollection;
import pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.domain.UserCollection.User;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User}.
 */
public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setUser(UserCollection.ITEMS.get(position));
    }

    @Override
    public int getItemCount() {
        return UserCollection.ITEMS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView uidView;
        public final TextView usernameView;
        public final TextView ageView;

        public ViewHolder(View view) {
            super(view);
            this.uidView = (TextView) view.findViewById(R.id.uid);
            this.usernameView = (TextView) view.findViewById(R.id.username);
            this.ageView = (TextView) view.findViewById(R.id.age);
        }

        public void setUser(User user) {
            this.uidView.setText(String.valueOf(user.getUid()));
            this.usernameView.setText(String.valueOf(user.getUsername()));
            this.ageView.setText(String.valueOf(user.getAge()));
        }
    }
}
