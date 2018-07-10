package httpComm;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class HttpComm {

    public static JSONObject signIn(String email, String password){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("http://127.0.0.1:8080/signin")
                    .header("accept", "application/json")
                    .field("email", email)
                    .field("password", password)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new JSONObject(jsonResponse.getBody().toString());
    }
}
