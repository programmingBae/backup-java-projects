package sample;

import com.google.gson.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static com.sun.corba.se.impl.util.Version.asString;

public class Controller {
    @FXML
    public TextField txtSearch;
    @FXML
    public TextArea txtDefinition;
    @FXML
    public Label txtUrbanDef;
    @FXML
    public Label txtLabelSearch;
    private OkHttpClient client = new OkHttpClient();

    public void urbanize(ActionEvent actionEvent) throws MalformedURLException {
        if (!txtSearch.getText().trim().isEmpty()) {
            txtUrbanDef.setOpacity(1);
            txtLabelSearch.setText(txtSearch.getText());
            txtLabelSearch.setOpacity(1);
            HttpResponse<JsonNode> response = Unirest.get("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term="+txtSearch.getText())
                    .header("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
                    .header("x-rapidapi-key", "bb7ce27c51mshb6d4d413e436e0ep1a6720jsn978740157d14")
                    .asJson();
//Get Response
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(response.getBody().toString());



            try {
            JsonArray tes = je.getAsJsonObject().get("list").getAsJsonArray();
            txtDefinition.setText(tes.get(0).getAsJsonObject().get("definition").toString());
            } catch (Exception exception){
                txtDefinition.setText("Oopsie, nothing found about "+txtSearch.getText());
            }



        }
    }
}
