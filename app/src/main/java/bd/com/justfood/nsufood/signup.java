package bd.com.justfood.nsufood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {


    protected EditText name , ID, varsity_name, mobile, pass, repass;

    private static Button submitbutton;


    //change layout position from sign up to Main activity class
    private void init() {

        submitbutton = (Button) findViewById(R.id.btsubmit);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create intent object to go to 1st layout(main activity class)
                Intent toy = new Intent(signup.this, MainActivity.class);
                //go to 1st layout page
                startActivity(toy);
            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //getting information from user inputs
        name = (EditText) findViewById(R.id.etName);
        ID = (EditText) findViewById(R.id.etID);
        varsity_name = (EditText) findViewById(R.id.etvName);
        mobile = (EditText) findViewById(R.id.etmobile);
        pass = (EditText) findViewById(R.id.etpass);
        repass = (EditText) findViewById(R.id.etrepass);



    }




    //sign up submission function
    public void clickOnSubmit(View view) {
        String st_name = name.getText().toString();
        String st_ID = ID.getText().toString();
        String st_vName = varsity_name.getText().toString();
        String st_mobile = mobile.getText().toString();
        String st_pass = pass.getText().toString();
        String st_repass = repass.getText().toString();
        if(st_pass==st_repass) {
            String type = "register";

            BackgroundWorker backgroundWorker = new BackgroundWorker(signup.this);
            //sign up submission back end process
            backgroundWorker.execute(type, st_name, st_ID, st_vName, st_mobile, st_pass);

        }
        clearUserInput();
        init();

    }


    private void clearUserInput()
    {
        name.setText("");
        ID.setText("");
        varsity_name.setText("");
        mobile.setText("");
        pass.setText("");
        repass.setText("");
    }
}

