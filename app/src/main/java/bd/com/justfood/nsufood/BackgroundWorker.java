package bd.com.justfood.nsufood;

/**
 * Created by ACM-Bimal on 12/9/2017.
 */

        import android.app.AlertDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    //context variable can hold any object type variable
    public static String result = "";
    Context context;
    AlertDialog alertDialog;
    static String login_url = "http://192.168.1.105/login.php";
    static String register_url = "http://192.168.1.105/register.php";

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    //
    protected String doInBackground(String... params)
    {
        String type = params[0];
        if(type=="login")
            return helperfunction(params,login_url);
        else if(type=="register")
            return helperfunction(params,register_url);
        return null;
    }

    private String helperfunction(String [] s,String st_url)
    {
        String type=s[0];
        try {

            URL url = new URL(st_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data="";
            if(type=="login")
            {
                post_data=postdataLogFunction(s);

            }
            else if(type=="register")
            {
                post_data=postdataRegFunction(s);
            }

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private String postdataLogFunction(String [] s) throws UnsupportedEncodingException
    {
        String ID = s[1];
        String pass = s[2];
        String post_data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8") + "&"
                + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
        return post_data;
    }

    private String postdataRegFunction(String [] s) throws UnsupportedEncodingException
    {
        String name = s[1];
        String ID = s[2];
        String varsityName = s[3];
        String mobile = s[4];
        String pass = s[5];

        String post_data=URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                +URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8") + "&"
                +URLEncoder.encode("vName", "UTF-8") + "=" + URLEncoder.encode(varsityName, "UTF-8") + "&"
                +URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8") + "&"
                + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");

        return post_data;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}