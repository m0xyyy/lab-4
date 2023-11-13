import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNoteName;
    private EditText editTextNoteContent;
    private Button buttonAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextNoteName = findViewById(R.id.editTextNoteName);
        editTextNoteContent = findViewById(R.id.editTextNoteContent);
        buttonAddNote = findViewById(R.id.buttonAddNote);

        setupAddNoteButton();
    }

    private void setupAddNoteButton() {
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteName = editTextNoteName.getText().toString().trim();
                String noteContent = editTextNoteContent.getText().toString().trim();

                if (noteName.isEmpty() || noteContent.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, R.string.toast_empty_note, Toast.LENGTH_SHORT).show();
                } else {
                    // Add the note to the list in MainActivity
                    ((MainActivity) getParent()).addNoteToList(noteName);

                    // Finish the activity or navigate back to MainActivity
                    finish();
                }
            }
        });
    }
}
