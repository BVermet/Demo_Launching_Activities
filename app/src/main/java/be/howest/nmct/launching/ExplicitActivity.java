package be.howest.nmct.launching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ExplicitActivity extends Activity {

    public static final String EXTRA_INFO = "be.howest.nmct.android.launching.EXTRA_INFO";

    public static final int RESULT_NOIDEA = 1;
    private TextView txtView1;
    private Button btnOk;
    private Button btnCancel;
    private Button btnNoIdea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);


        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnNoIdea = (Button)findViewById(R.id.btnNoIdea);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnNoIdea.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_INFO_BACK_LASTNAME,"Vermet");
                intent.putExtra(MainActivity.EXTRA_INFO_BACK_AGE,24);
                setResult(ExplicitActivity.RESULT_NOIDEA,intent);
                finish();
            }
        });

        txtView1 = (TextView) findViewById(R.id.txtView1);
        String value = getIntent().getStringExtra(ExplicitActivity.EXTRA_INFO);

        txtView1.setText(value);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_explicit, menu);
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
