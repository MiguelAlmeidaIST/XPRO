package pt.ulisboa.tecnico.cmov.airdesk;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class MainActivity5Activity extends ActionBarActivity {
    ArrayList<String> lista;
    ArrayAdapter<String> listaAdapter;
    EditText textmsg;
    public final static String EXTRA_MESSAGE = "pt.ulisboa.tecnico.cmov.helloworld.MESSAGE";
    public final static String Intent_message = "pt.ulisboa.tecnico.cmov.helloworld.MESSAGE2";
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5);

        textmsg=(EditText)findViewById(R.id.editText5);
        ListView listView = (ListView) findViewById(R.id.listView);


        lista = new ArrayList<>();

        listaAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);

        listView.setAdapter(listaAdapter);
    }

    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        EditText editText = (EditText) findViewById(R.id.editText5);
        String nota = editText.getText().toString();

        lista.add(nota);
        listaAdapter.notifyDataSetChanged();
    }

    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
