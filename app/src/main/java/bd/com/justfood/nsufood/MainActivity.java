package bd.com.justfood.nsufood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //go to sign in layout
    public void clickSignIn(View view)
    {
        //start the program under the register button
        startActivity(new Intent(MainActivity.this,signin.class));
    }

    //go to sign up layout
    public void clickSignUp(View view)
    {
        //start the program under the register button
        startActivity(new Intent(MainActivity.this,signup.class));
    }
}
