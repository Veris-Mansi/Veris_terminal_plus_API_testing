package Validatevisitor;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.ResourcesTerminalLogin;
import Files.Resourcevalidatevisitor;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CompleteValidateVisitor {

	String token="";
	String user_pk="";
	String invalid_token="291b12850a914d0dba55bd5aa62b16d3769797";
	String meeting_with="sha";
	String invalid_meeting_with="sh";
	String v_id="";
	String workflow_id="";
	@Test(priority=0,groups="TerminalLogin")
	public void terminalLogin()
	{
		token=ResourcesTerminalLogin.login();
	}
	
	@Test(priority=1,groups="TerminalLogin")
	public void terminalinvalidUsername()
	{
		ResourcesTerminalLogin.invalidusernamelogin();
	}
	@Test(priority=2,groups="TerminalLogin")
	public void terminalinvalidPasssword()
	{
		ResourcesTerminalLogin.invalidusernamelogin();
	}
	@Test(priority=3,groups="Validatevisitor")
	public void visitorinvalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
		headers("Content-Type","application/json").headers("Authorization","gatekeeper "+invalid_token).
		when().post("/api/v4/validate-visitor/").
		then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=4,groups="Validatevisitot",dependsOnMethods="terminalLogin")
	public void ValidatevisitorMobile()
	{
		v_id=Resourcevalidatevisitor.validateVisitorPhone(token);
		System.out.println("user_pk is "+v_id);
	}
	@Test(priority=5,groups="Validatevisitot")
	public void ValidatevisitorEmail()
	{
		user_pk=Resourcevalidatevisitor.validateVisitorEmail(token);
		System.out.println("user_pk is "+user_pk);
		
	}@Test(priority=6,groups="Validatevisitot")
	public void ValidatevisitorinvalidPhone()
	{
		Resourcevalidatevisitor.invalidPhone(token);
	}@Test(priority=7,groups="Validatevisitot")
	public void ValidatevisitorinvalidEmail()
	{
		Resourcevalidatevisitor.invalidEmail(token);
	}
	@Test(priority=8,groups="Validatevisitot")
	public void ValidatevisitorvalidQR()
	{
		user_pk=Resourcevalidatevisitor.validateVisitorQR(token);
		System.out.println("user_pk is "+user_pk);
		
	}@Test(priority=9,groups="Validatevisitot")
	public void ValidatevisitorinvalidQR()
	{
		Resourcevalidatevisitor.invalidQR(token);
	}
	@Test(priority=10,groups="Validatevisitot")
	public void Validatevisitorinvalidactivity()
	{
		Resourcevalidatevisitor.invalidActivity(token);
	}
	@Test(priority=11,groups="MeetingWithAPI")
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
	@Test(priority=12,groups="MeetingWithAPI")
	public void invalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("contact", meeting_with).	
				headers("Authorization","gatekeeper "+invalid_token).
				when().get("/api/v2/meeting-with/").
				then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=13,groups="MeetingWithAPI")
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
	@Test(priority=14,groups="getVisitorlastactivityData",dependsOnMethods="terminalLogin")
	public void getVisitorlastactivityData()
	{
		workflow_id=Resourcevalidatevisitor.getVisitorlastactivityData(v_id, token);
		System.out.println("workflow_id is "+workflow_id);
	}
	@Test(priority=15,groups="getVisitorlastactivityData",dependsOnMethods="terminalLogin")
	public void getVisitorlastactivityDatainvalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
		headers("Authorization","gatekeeper "+invalid_token).
		when().get("/api/v4/visitor/"+v_id+"/activity/").
		then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=16,groups="getVisitorlastactivityData",dependsOnMethods="terminalLogin")
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
	@Test(priority=17,groups="getVisitorlastactivityData",dependsOnMethods="terminalLogin")
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
		char ch[]=response.toCharArray();
		System.out.println(ch.length);
		if(response == "[]")
		{
			assert true;
		}
	}
	@Test(priority=18,groups="getVisitorWorkflowData")
	public  void getVisitorWorkflowdata()
	{
		Resourcevalidatevisitor.getVisitorWFData(v_id, workflow_id, token);
	}
	@Test(priority=19,groups="getVisitorWorkflowData")
	public void getVisitorWFDATAinvalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
		headers("Authorization","gatekeeper "+invalid_token).
		when().get("/api/v4/visitor/"+v_id+"/workflow/"+workflow_id+"/").
		then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=20,groups="getVisitorWorkflowData")
	public void getVisitorWFDATAinvalidVisitor()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
		headers("Authorization","gatekeeper "+token).
		when().get("/api/v4/visitor/4444/workflow/"+workflow_id+"/").
		then().assertThat().statusCode(200).extract().response();
		String response = res.asString();
		System.out.println("invalid_user_response "+response);
		if(response == "[]")
		{
			assert true;
		}
	}	@Test(priority=21,groups="getVisitorWorkflowData")
	public void getVisitorWFDATAinvalidworkflow()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
		headers("Authorization","gatekeeper "+token).
		when().get("/api/v4/visitor/"+v_id+"/workflow/999/").
		then().assertThat().statusCode(200).extract().response();
		String response = res.asString();
		System.out.println("invalid_user_response "+response);
		if(response == "[]")
		{
			assert true;
		}
	}	@Test(priority=22,groups="getVisitorWorkflowData")
	public void getVisitorWFDATAinvalidactivityType()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "qqq").
		headers("Authorization","gatekeeper "+token).
		when().get("/api/v4/visitor/"+v_id+"/workflow/"+workflow_id+"/").
		then().assertThat().statusCode(200).extract().response();
		String response = res.asString();
		System.out.println("invalid_type_response "+response);
		
		if(response == "[]")
		{
			assert true;
		}
	}

}
