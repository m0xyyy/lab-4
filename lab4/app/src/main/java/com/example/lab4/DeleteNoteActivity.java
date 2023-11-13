import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {

    private ListView listViewNotesForDeletion;
    private ArrayAdapter<String> deleteNotesAdapter;
    private ArrayList<String> deleteNotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        listViewNotesForDeletion = findViewById(R.id.listViewNotesForDeletion);
        deleteNotesList = new ArrayList<>();
        deleteNotesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deleteNotesList);
        listViewNotesForDeletion.setAdapter(deleteNotesAdapter);

        loadNotesForDeletion();
    }

    private void loadNotesForDeletion() {
        deleteNotesList.clear();
        deleteNotesList.addAll(((MainActivity) getParent()).notesList);

        deleteNotesAdapter.notifyDataSetChanged();

        listViewNotesForDeletion.setOnItemClickListener((parent, view, position, id) -> {
            String selectedNote = deleteNotesList.get(position);
            ((MainActivity) getParent()).deleteNoteFromList(position);
            deleteNotesList.remove(position);
            deleteNotesAdapter.notifyDataSetChanged();
            Toast.makeText(DeleteNoteActivity.this, "Note deleted: " + selectedNote, Toast.LENGTH_SHORT).show();
        });
    }
}
