package bd.com.justfood.nsufood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static bd.com.justfood.nsufood.BackgroundWorker.result;

public class signin extends AppCompatActivity {


    EditText id_Et, passwordEt;
   public Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //get the user name from user
        id_Et = (EditText)findViewById(R.id.etID);
        //get the user password from password
        passwordEt = (EditText)findViewById(R.id.etPass);
        signinButton=(Button)findViewById(R.id.btSignIn);
    }
//sign in back-end function
    public void clickSignIn(View view) {

        //convert the userID into string type
        String userID = id_Et.getText().toString();
        //convert the user password into string type
        String password = passwordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, userID, password);
        if(result=="login sucess")
        {

            Toast.makeText(this,"Login successful",Toast.LENGTH_LONG).show();
            Intent toy = new Intent(signin.this, getData.class);
            //go to 1st layout page
            startActivity(toy);
            //init();
        }
        else if(result=="NOT")
        {
            Toast.makeText(this,"Login unsuccessful",Toast.LENGTH_LONG).show();

        }


    }

    //change layout position from sign up to Main activity class
    private void init() {

        signinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //create intent object to go to 1st layout(main activity class)

                Intent toy = new Intent(signin.this, getData.class);
                //go to 1st layout page
                startActivity(toy);
            }
        });
    }

}



