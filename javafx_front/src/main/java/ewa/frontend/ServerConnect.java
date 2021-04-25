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

    public static void postDog(Dog dog){

        System.out.println("postDog: "+dog.getName() +" " + dog.getId() + " " + dog.getAge() + " " + dog.getDog_owner_dog() );
        try {
            URL url = new URL("http://localhost:8080/dog");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            /*String input = " {\n" +
                    "        \"name\": \"Kola\",\n" +
                    "        \"age\": \"1\",\n" +
                    "        \"emailDogOwner\": \"ewus9999@gmail.com\"\n" +
                    "}";*/

            String input ="";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            input = gson.toJson(dog);
            System.out.println(gson.toJson(dog));
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }


    }

    public static void postPerson(Person person){
        try {
            URL url = new URL("http://localhost:8080/dogowner");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            /*String input = " {\n" +
                    "        \"name\": \"Kola\",\n" +
                    "        \"age\": \"1\",\n" +
                    "        \"emailDogOwner\": \"ewus9999@gmail.com\"\n" +
                    "}";*/

            String input ="";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            input = gson.toJson(person);
            System.out.println(gson.toJson(person));
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void deletePerson(Person person){
        try {
            URL url = new URL("http://localhost:8080/delete/dogowner");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");

            String input ="";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            input = gson.toJson(person);
            System.out.println(gson.toJson(person));
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void deleteDog(Dog dog){
        try {
            URL url = new URL("http://localhost:8080/delete/dog");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");

            String input ="";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            input = gson.toJson(dog);
            System.out.println(gson.toJson(dog));
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
            }


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void editPerson(Person person){
        try {
            URL url = new URL("http://localhost:8080/edit/dogowner");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input ="";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            input = gson.toJson(person);
            System.out.println("hello: "+gson.toJson(person));
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    public static void editDog(Dog dog){
        try {
            URL url = new URL("http://localhost:8080/edit/dog");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input ="";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            input = gson.toJson(dog);
            System.out.println("hello: "+gson.toJson(dog));
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    public static List<Dog> getDogownerDogs(Person person){
        List<Dog> list = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/get/dogowner/dogs");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            /*String input = " {\n" +
                    "        \"name\": \"Kola\",\n" +
                    "        \"age\": \"1\",\n" +
                    "        \"emailDogOwner\": \"ewus9999@gmail.com\"\n" +
                    "}";*/

            String input ="";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            input = gson.toJson(person);
            System.out.println("gson: "+gson.toJson(person));
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            System.out.println("....");

            /*if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }*/

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            String INPUT ="";
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                INPUT+=output;
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            gson = gsonBuilder.create();

            list = gson.fromJson(INPUT, new TypeToken<List<Dog>>() {}.getType());


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return list;
    }
}
