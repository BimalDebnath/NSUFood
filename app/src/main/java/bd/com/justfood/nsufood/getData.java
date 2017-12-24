package bd.com.justfood.nsufood;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class getData extends AppCompatActivity {


     String TAG = getData.class.getSimpleName();
    protected ListView lv;
    ArrayList<HashMap<String, String > > canteenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        canteenList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listViewID);
        new fetchAllData().execute();

    }


    class fetchAllData extends AsyncTask<Void, Void, Void> {
        // Making a request to url and getting response
        String url = "http://192.168.1.105/d.php";
        ArrayList<HashMap<String, String > > canteenList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*Toast.makeText(getdata.this,"Json Data is
                    downloading",Toast.LENGTH_LONG).show();
                    */

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray jsonArray = jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int index = 0; index < jsonArray.length(); index++)
                    {
                        JSONObject c = jsonArray.getJSONObject(index);
                        String cName = c.getString("cName");
                        String food = c.getString("food");
                        String price = c.getString("price");
                        // tmp hash map for single contact
                        HashMap<String, String> canteen = new HashMap<>();

                        // adding each child node to HashMap key => value
                        canteen.put("cName", cName);
                        canteen.put("food", food);
                        canteen.put("price", price);

                        // adding contact to contact list
                        canteenList.add(canteen);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(activity_view_data.this, canteenList,
                    R.layout.activity_custom_list_view, new String[]{ "cName","food","price"},
                    new int[]{R.id.nameID, R.id.foodID, R.id.priceID});
            lv.setAdapter(adapter);
        }
    }

}
