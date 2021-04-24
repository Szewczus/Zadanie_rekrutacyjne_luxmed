package ewa.frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * JavaFX App
 */
@SpringBootApplication
public class App extends Application {

    //private ConfigurableApplicationContext applicationContext;
    private static Scene scene;
    public static PropertyValueFactory propertyValueFactory;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        /*try {
            URL url = new URL("http://localhost:8080/dogowners");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();

            if (responsecode !=200){
                throw new RuntimeException("HttpResponseCode: "+responsecode);
            }
            else {
                Scanner scanner = new Scanner(url.openStream());
                String inline="";
                while (scanner.hasNext()){
                    inline+=scanner.nextLine();
                }
                System.out.println(inline);
            }
        } catch (IOException e) {

        }*/
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
            String inline="";
            while ((output = bufferedReader.readLine())!=null){
                inline+=output;
                //System.out.println(inline+".");
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            System.out.println("ELO\n" + inline + "\nELO");
            String deleted = inline.substring(1,inline.length()-1);
            System.out.println("ELO\n" + deleted + "\nELO");
            Gson gson = gsonBuilder.create();
            Person person = gson.fromJson(deleted, Person.class);
            System.out.println("Person: " + person.getName() +" + " + person.getSurname());



            //ServerConnect connect = new ServerConnect();
            //connect.connect();
            System.out.println(inline);
            String [] s = inline.split(",");
            String [] s1 = s[0].split(":", 2);

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            /*JSONObject json = new JSONObject(sb.toString());
            System.out.println(json);*/

        }
        catch (MalformedURLException e){

        } catch (IOException/* | JSONException*/ e) {
            e.printStackTrace();
        }

        launch();
    }




}
