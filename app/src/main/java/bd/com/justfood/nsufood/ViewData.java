package bd.com.justfood.nsufood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewData extends AppCompatActivity {

    final static String address="http://192.168.1.105/d.php";
    String[] data;
    ListView lv;
    ArrayAdapter<String> adapter;
    InputStream is=null;
    String line=null;
    String result=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        lv=(ListView)findViewById(R.id.listViewID);
        getData();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);

    }
    protected void getData() {

        try{
            URL url=new URL(address);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb= new StringBuilder();
            while((line=br.readLine())!=null)
            {
                sb.append(line+"\n");

            }
            is.close();
             result=sb.toString();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //parse json data
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            data = new String[ja.length()];
            for(int index=0;index<ja.length();index++)
            {
                jo = ja.getJSONObject(index);
                data[index] = jo.getString("ID");
                //data[index] = jo.getString("name");
                //data[index] = jo.getString("vName");
                //data[index] = jo.getString("mobile");
                //data[index] = jo.getString("pass");
            }

        } catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

}
