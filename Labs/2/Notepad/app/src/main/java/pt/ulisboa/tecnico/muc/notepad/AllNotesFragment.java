package pt.ulisboa.tecnico.muc.notepad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllNotesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_notes_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.all_notes_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AllNotesRecyclerViewAdapter());

        view.findViewById(R.id.new_fab).setOnClickListener(this::newNote);
        return view;
    }

    private void newNote(final View view) {
        Navigation.findNavController(view)
                .navigate(R.id.action_AllNotesFragment_to_EditNoteFragment);
    }
}
