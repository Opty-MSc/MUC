package pt.ulisboa.tecnico.muc.notepad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import pt.ulisboa.tecnico.muc.notepad.allnotes.AllNotes;


/**
 * {@link RecyclerView.Adapter} that can display a {@link AllNotes.Note}.
 */
public class AllNotesRecyclerViewAdapter extends RecyclerView.Adapter<AllNotesRecyclerViewAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_all_notes, parent, false);
        view.setOnClickListener(this::viewNote);
        view.findViewById(R.id.edit_fab).setOnClickListener(this::editNote);
        view.findViewById(R.id.delete_fab).setOnClickListener(this::deleteNote);
        return new ViewHolder(view);
    }

    private void viewNote(final View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        RecyclerView recyclerView = (RecyclerView) linearLayout.getParent();
        int position = recyclerView.getChildLayoutPosition(linearLayout);
        Bundle args = NoteViewerFragment.newBundle(position);
        Navigation.findNavController(view)
                .navigate(R.id.action_AllNotesFragment_to_NoteViewerFragment, args);
    }

    private void editNote(final View view) {
        LinearLayout linearLayout = (LinearLayout) view.getParent();
        RecyclerView recyclerView = (RecyclerView) linearLayout.getParent();
        int position = recyclerView.getChildLayoutPosition(linearLayout);
        Bundle args = EditNoteFragment.newBundle(position);
        Navigation.findNavController(view)
                .navigate(R.id.action_AllNotesFragment_to_EditNoteFragment, args);
    }

    private void deleteNote(final View view) {
        LinearLayout linearLayout = (LinearLayout) view.getParent();
        RecyclerView recyclerView = (RecyclerView) linearLayout.getParent();
        int position = recyclerView.getChildLayoutPosition(linearLayout);
        recyclerView.removeViewAt(position);
        AllNotes.ITEMS.remove(position);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.idView.setText(String.valueOf(position + 1));
        holder.titleView.setText(AllNotes.ITEMS.get(position).title);
    }

    @Override
    public int getItemCount() {
        return AllNotes.ITEMS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView idView;
        public final TextView titleView;

        public ViewHolder(View view) {
            super(view);
            idView = view.findViewById(R.id.item_number);
            titleView = view.findViewById(R.id.content);
        }
    }
}
