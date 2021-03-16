package pt.ulisboa.tecnico.muc.notepad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import pt.ulisboa.tecnico.muc.notepad.allnotes.AllNotes;

public class NoteViewerFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_note_viewer, container, false);
        if (getArguments() != null) {
            AllNotes.Note item = AllNotes.ITEMS.get(getArguments().getInt(POSITION));
            ((TextView) view.findViewById(R.id.title_viewer)).setText(item.title);
            ((TextView) view.findViewById(R.id.body_viewer)).setText(item.body);
        }
        return view;
    }
}
