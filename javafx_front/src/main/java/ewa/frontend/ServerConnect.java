package ewa.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class ServerConnect {

    public String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public static List<Person> connectToDogOwner(){
        List<Person> list = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/dogowners");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            if(connection.getResponseCode()!=200){
                throw new RuntimeException("Failed: HTTP error code: " + connection.getResponseCode());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String output="";
            //System.out.println("Output from server... \n");
            String inline="";
            while ((output = bufferedReader.readLine())!=null){
                inline+=output;

            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();

            list = gson.fromJson(inline, new TypeToken<List<Person>>() {}.getType());
            //list.forEach(x->System.out.println(x.getEmail() + " "+ x.getName()));

        }
        catch (MalformedURLException e){

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Dog> connectToDog(){
        List<Dog> list = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/dogs");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            if(connection.getResponseCode()!=200){
                throw new RuntimeException("Failed: HTTP error code: " + connection.getResponseCode());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String output="";
            System.out.println("Output from server... \n");
            String inline="";

            while ((output = bufferedReader.readLine())!=null){
                inline+=output;
            }
            System.out.println(inline);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            list = gson.fromJson(inline, new TypeToken<List<Dog>>() {}.getType());
            list.forEach(x->System.out.println("Dog: " + x.getId() + " "+x.getName() + " " + x.getAge() + " " + x.getDog_owner_dog()));

        }
        catch (MalformedURLException e){

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
