//invalid vsitor
//invalid workflow
//invalid token
//getting workflow data

package Validatevisitor;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.PayloaddataTerminallogin;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Files.*;
public class getVisitorWorkflowData {

	String token=ResourcesTerminalLogin.login();
	String invalid_token="5f3c2e03ca3569080f2fffdfa3de0e47194e0";
	String v_id="8";
	String workflow_id="1";
	
	@Test(priority=1,groups="getVisitorWorkflowData")
	public  void getVisitorWorkflowdata()
	{
		Resourcevalidatevisitor.getVisitorWFData(v_id, workflow_id, token);
	}
	@Test(priority=2,groups="getVisitorWorkflowData")
	public void getVisitorWFDATAinvalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
		headers("Authorization","gatekeeper "+invalid_token).
		when().get("/api/v4/visitor/"+v_id+"/workflow/"+workflow_id+"/").
		then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=3,groups="getVisitorWorkflowData")
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
	}	@Test(priority=4,groups="getVisitorWorkflowData")
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
	}	@Test(priority=5,groups="getVisitorWorkflowData")
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
