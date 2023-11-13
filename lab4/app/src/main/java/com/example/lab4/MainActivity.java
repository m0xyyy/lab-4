import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNotes;
    private ArrayAdapter<String> notesAdapter;
    private ArrayList<String> notesList;
    private static final String NOTES_PREFS = "notes_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        notesList = new ArrayList<>();
        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listViewNotes.setAdapter(notesAdapter);

        loadNotes();
    }

    private void loadNotes() {
        SharedPreferences preferences = getSharedPreferences(NOTES_PREFS, MODE_PRIVATE);
        Set<String> savedNotes = preferences.getStringSet("notes", new HashSet<>());

        notesList.clear();
        notesList.addAll(savedNotes);

        notesAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, R.id.menu_add_note, Menu.NONE, "Add Note");
        menu.add(Menu.NONE, R.id.menu_delete_note, Menu.NONE, "Delete Note");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_note:
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
                return true;
            case R.id.menu_delete_note:
                startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Implement methods to add and delete notes
    public void addNoteToList(String note) {
        notesList.add(note);
        notesAdapter.notifyDataSetChanged();
        saveNotes();
    }

    public void deleteNoteFromList(int position) {
        notesList.remove(position);
        notesAdapter.notifyDataSetChanged();
        saveNotes();
    }

    private void saveNotes() {
        SharedPreferences preferences = getSharedPreferences(NOTES_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> notesSet = new HashSet<>(notesList);
        editor.putStringSet("notes", notesSet);
        editor.apply();
    }
}
