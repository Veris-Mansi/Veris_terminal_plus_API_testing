//invalid Token
//invallid String()
package Validatevisitor;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Files.*;
public class MeetingWithAPI {

	String user_pk="";
	String token=ResourcesTerminalLogin.login();
	String invalid_token="291b12850a914d0dba55bd5aa62b16d3769797";
	String meeting_with="sha";
	String invalid_meeting_with="sh";

	@Test(priority=1,groups="MeetingWithAPI")
	public void MeetingWithTest()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("contact", meeting_with).
				headers("Authorization","gatekeeper "+token).
				when().get("/api/v2/meeting-with/").
				then().assertThat().statusCode(200).and().body("name[0]", equalTo("Shaka Kumar")).extract().response();
		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
	}
	@Test(priority=2,groups="MeetingWithAPI")
	public void invalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("contact", meeting_with).	
				headers("Authorization","gatekeeper "+invalid_token).
				when().get("/api/v2/meeting-with/").
				then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=3,groups="MeetingWithAPI")
	public void invalidMeetingWith()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("contact", invalid_meeting_with).	
				headers("Authorization","gatekeeper "+token).
				when().get("/api/v2/meeting-with/").then().assertThat().statusCode(200).and().extract().response();
		String response = res.asString();
		System.out.println("Invalid string response "+response);
		if(response == "[]")
		{
			assert true;
		}
	
	}
}
