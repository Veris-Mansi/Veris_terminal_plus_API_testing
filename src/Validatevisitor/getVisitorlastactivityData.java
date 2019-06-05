package Validatevisitor;
//user of another organization
//different user
//correct user
//invalid gatekeeper token
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.PayloaddataTerminallogin;
import Files.ResourcesTerminalLogin;
import Files.Resourcevalidatevisitor;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getVisitorlastactivityData {
	
	String v_id="8";
	String workflow_id="";
	String token=ResourcesTerminalLogin.login();
	String invalid_token="d4f3f0a0c726e3ce68514609aa756d606511a";
	@Test(priority=1,groups="getVisitorlastactivityData")
	public void getVisitorlastactivityData()
	{
		workflow_id=Resourcevalidatevisitor.getVisitorlastactivityData(v_id, token);
		System.out.println("workflow_id is "+workflow_id);
	}
	@Test(priority=2,groups="getVisitorlastactivityData")
	public void getVisitorlastactivityDatainvalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
		queryParam("action", "in").
		headers("Authorization","gatekeeper "+invalid_token).
		when().get("/api/v4/visitor/"+v_id+"/activity/").
		then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=3,groups="getVisitorlastactivityData")
	public void getlastactivityDatainvalidUser()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
		headers("Authorization","gatekeeper "+token).
		when().get("/api/v4/visitor/300/activity/").
		then().assertThat().statusCode(200).and().extract().response();
		String response = res.asString();
		System.out.println("invalid_user_response "+response);
		if(response == "[]")
		{
			assert true;
		}
	}
	@Test(priority=4,groups="getVisitorlastactivityData")
	public void getlastactivityDatainvalidactivitytype()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "qqqq").
		headers("Authorization","gatekeeper "+token).
		when().get("/api/v4/visitor/"+v_id+"/activity/").
		then().assertThat().statusCode(200).and().extract().response();
		String response = res.asString();
		System.out.println("invalid_type_response "+response);
		
		if(response == "[]")
		{
			assert true;
		}
	}
	
}
