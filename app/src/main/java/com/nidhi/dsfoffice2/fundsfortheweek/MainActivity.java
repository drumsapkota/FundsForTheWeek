package com.nidhi.dsfoffice2.fundsfortheweek;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.nidhi.dsfoffice2.fundsfortheweek.R.id.EditText1;

public class MainActivity extends AppCompatActivity {

    EditText EditText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save("Notex.txt");
            }
        });
        EditText1 = (EditText)findViewById(R.id.EditText1);
        EditText1.setText(Open("Notex.txt"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Save(String fileName){

            try {
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput(fileName, 0));
                out.write(EditText1.getText().toString());
                out.close();
                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
            } catch (Throwable t) {
                Toast.makeText(this, "Exception!!"+t.toString(), Toast.LENGTH_LONG).show();
            }

    }
    public boolean FileExists(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
    public String Open(String fileName){
        String content="";
        if(FileExists(fileName)){
            try{
                InputStream is =openFileInput(fileName);
                if(is!=null){
                    InputStreamReader tmp = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf=new StringBuilder();
                    while((str=br.readLine())!=null){
                        buf.append(str+"\n");
                    }
                    is.close();
                    content = buf.toString();
                }
            }catch(java.io.FileNotFoundException e){}catch(Throwable t){
                Toast.makeText(this, "Exception"+t.toString(), Toast.LENGTH_LONG).show();
            }

        }
        return content;
    }
}
