package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserFragment extends Fragment {

    public RecyclerView recyclerView;
    public UserRecyclerViewAdapter recyclerAdapter;

    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        if (view instanceof RecyclerView) {
            this.recyclerView = (RecyclerView) view;
            this.recyclerAdapter = new UserRecyclerViewAdapter();
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this.recyclerView.getContext()));
            this.recyclerView.setAdapter(this.recyclerAdapter);
        }
        return view;
    }
}
