package pt.ulisboa.tecnico.muc.notepad.allnotes;

import java.util.ArrayList;
import java.util.List;

public class AllNotes {

    public static final List<Note> ITEMS = new ArrayList<>();

    public static Note createNote(String title, String body) {
        return new Note(title, body);
    }

    public static class Note {
        public final String title;
        public final String body;

        public Note(String title, String body) {
            this.title = title;
            this.body = body;
        }
    }
}
