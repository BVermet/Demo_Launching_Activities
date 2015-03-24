package be.howest.nmct.launching;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private Button buttonStartActivity2;
    private Button btnDialoog;
    private EditText txtScore;
    final Context context = this;

    public static final int REQUEST_CODE_EXPLICIT = 1;
    public static final String EXTRA_INFO_BACK_LASTNAME = "be.howest.nmct.android.launching.EXTRA_INFO_BACK_LASTNAME";
    public static final String EXTRA_INFO_BACK_AGE = "be.howest.nmct.android.launching.EXTRA_INFO_BACK_AGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartActivity2 = (Button) findViewById(R.id.buttonStartActivity2);
        buttonStartActivity2.setOnClickListener(new View.OnClickListener(){
           @Override
                   public void onClick(View v){
                startSecondActivity(v);
            }
        });

        btnDialoog = (Button) findViewById(R.id.btnDialoog);
        btnDialoog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.txtScore);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        int result = Integer.parseInt(userInput.getText().toString());
                                        Toast.makeText(MainActivity.this,"User selects No Idea " + result,Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });
    }



    public void startSecondActivity(View v){
        Intent intent = new Intent(MainActivity.this, ExplicitActivity.class);
        intent.putExtra(ExplicitActivity.EXTRA_INFO,"2NMCT");
        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE_EXPLICIT);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){

            case REQUEST_CODE_EXPLICIT:

                switch (resultCode){
                    case RESULT_CANCELED:
                        Toast.makeText(MainActivity.this,"User selects Cancel",Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this,"User selects Ok",Toast.LENGTH_SHORT).show();
                        break;
                    case ExplicitActivity.RESULT_NOIDEA:
                        String value = data.getStringExtra(MainActivity.EXTRA_INFO_BACK_LASTNAME);
                        int age = data.getIntExtra(MainActivity.EXTRA_INFO_BACK_AGE, 0);

                        Toast.makeText(MainActivity.this,"User selects No Idea " + value + " " + age,Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                super.onActivityResult(requestCode,resultCode,data);
        }
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
}
