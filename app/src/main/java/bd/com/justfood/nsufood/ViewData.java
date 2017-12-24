package bd.com.justfood.nsufood;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.ArrayList;

public class ViewData extends AppCompatActivity {

    final static String address = "http://192.168.1.105/d.php";
    String[] data;
    ListView lv;
    //ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> foodList;
    InputStream is = null;
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        lv = (ListView) findViewById(R.id.listViewID);

        new getData.execute();
    }

    private class getData extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... arg0) {

            try {
                URL url = new URL(address);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                is = new BufferedInputStream(con.getInputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");

                }
                is.close();
                result = sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //parse json data
            try {
                JSONObject jsonObj = new JSONObject(result);

                // Getting JSON Array node
                JSONArray foods = jsonObj.getJSONArray("canteenList");

                for (int index = 0; index < foods.length(); index++) {
                    JSONObject jo = foods.getJSONObject(index);
                    String canteen = jo.getString("cName");

                    String foodType = jo.getString("food");
                    String price = jo.getString("price");
                    HashMap<String, String> item = new HashMap<>();
                    // adding each child node to HashMap key => value
                    item.put("cName", canteen);
                    item.put("food", foodType);
                    item.put("price", price);


                    // adding contact to contact list
                    foodList.add(item);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(viewdata.this, foodListt,
                    R.layout.list_item, new String[]{"canteen", "foood"},
                    new int[]{R.id.email, R.id.mobile});
            lv.setAdapter(adapter);
        }
    }



}
