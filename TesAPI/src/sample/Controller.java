package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;

public class Controller {
    @FXML
    public TextArea tesTEXT;
    private OkHttpClient client = new OkHttpClient();





    public void test(ActionEvent actionEvent) {
        Request r = new Request.Builder().url("https://api-mobilespecs.azharimm.site/v2/brands/apple-phones-48?page=2").get().build();

        try {
            Response response = client.newCall(r).execute();
            JSONParser parser = new JSONParser();
            JSONObject jsonArray = (JSONObject) parser.parse(response.body().string());
            tesTEXT.setText(jsonArray.toString());

        } catch (IOException | ParseException e) {
            Alert alertInformation=new Alert(Alert.AlertType.ERROR);
            alertInformation.setContentText(e.getMessage());
            alertInformation.show();
        }
    }
}
