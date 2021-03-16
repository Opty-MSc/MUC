package pt.ulisboa.tecnico.muc.notepad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import pt.ulisboa.tecnico.muc.notepad.allnotes.AllNotes;

public class EditNoteFragment extends Fragment {

    private static final String POSITION = "POSITION";

    public static Bundle newBundle(int position) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        return args;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);

        if (getArguments() != null) {
            AllNotes.Note item = AllNotes.ITEMS.get(getArguments().getInt(POSITION));
            ((EditText) view.findViewById(R.id.title_edit)).setText(item.title);
            ((EditText) view.findViewById(R.id.body_edit)).setText(item.body);
        }
        view.findViewById(R.id.save_fab).setOnClickListener(this::saveNote);
        view.findViewById(R.id.discard_fab).setOnClickListener(this::discardNote);
        return view;
    }

    private void saveNote(final View view) {
        if (getArguments() != null) {
            AllNotes.ITEMS.remove(getArguments().getInt(POSITION));
        }
        if (getView() != null) {
            String title = ((EditText) getView().findViewById(R.id.title_edit)).getText().toString();
            String body = ((EditText) getView().findViewById(R.id.body_edit)).getText().toString();
            AllNotes.ITEMS.add(0, AllNotes.createNote(title, body));
        }
        Navigation.findNavController(view)
                .navigate(R.id.action_EditNoteFragment_to_AllNotesFragment);
    }

    private void discardNote(final View view) {
        Navigation.findNavController(view)
                .navigate(R.id.action_EditNoteFragment_to_AllNotesFragment);
    }
}
