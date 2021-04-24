package ewa.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class ServerConnect {

    public String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void connect(){
        try {
            URL url = new URL("http://localhost:8080/dogowners");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            if(connection.getResponseCode()!=200){
                throw new RuntimeException("Failed: HTTP error code: " + connection.getResponseCode());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;

            System.out.println("Output from server... \n");
            while ((output = bufferedReader.readLine())!=null){


            }



            connection.disconnect();
        }
        catch (MalformedURLException e){

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
