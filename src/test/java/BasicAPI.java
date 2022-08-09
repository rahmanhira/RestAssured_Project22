import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;

public class BasicAPI {

    @Test
    public void studentSignup() throws JsonProcessingException {

        RestAssured.baseURI="https://qa.taltektc.com/api/";
        //Only for Talenttek
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
        //Only for Talenttek

        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"firstName\" : \"Jhonny\",\n" +
                        "    \"lastName\" : \"Deep\",\n" +
                        "    \"email\"     : \"jhon.dooom123@gmail.com\",\n" +
                        "    \"password\"  : \"123456\",\n" +
                        "    \"confirmPassword\"  : \"123456\",\n" +
                        "    \"dob\"       : {\n" +
                        "        \"year\"      : 2013,\n" +
                        "        \"month\"     : 12,\n" +
                        "        \"day\"       : 31\n" +
                        "    },\n" +
                        "    \"gender\"    : \"male\",\n" +
                        "    \"agree\"     : true\n" +
                        "}'")
                .post("signup");
         System.out.println(response.statusCode());
         System.out.println(response.contentType());
         System.out.println(response.getBody().asString());
        ObjectMapper mp=new ObjectMapper();
        JsonNode body = mp.readTree(response.getBody().asString());
        System.out.println(body.get("message"));
    }


    @Test
    public void getStudentInfo() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
        Response response = RestAssured.given()
                .contentType("application/json")
                .get("student/TTCwdxV8");

        System.out.println(response.getStatusCode());
        //System.out.println(response.getHeaders());
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().asString());
        ObjectMapper mp=new ObjectMapper();
        JsonNode body = mp.readTree(response.getBody().asString());
        System.out.println(body.get("message"));
        System.out.println(body.get("data").get("lastName"));

    }


    @Test

    public void getAllstudentinfo() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
        Response response = RestAssured.given()
                .contentType("application/json")
                .get("students");

        //System.out.println(response.getBody().asString());
        ObjectMapper mp=new ObjectMapper();
        JsonNode body = mp.readTree(response.getBody().asString());
        System.out.println(body.get("data").get(0).get("lastName"));

        for(int i=0;i<body.get("data").size();i++){
            System.out.println(body.get("data").get(i).get("lastName"));
        }

    }



}




