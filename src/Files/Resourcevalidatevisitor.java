package Files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Resourcevalidatevisitor {

	public static String validateVisitorEmail(String token) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloadValidatevisitor.validateVisitorEmail()).
				when().post("/api/v4/validate-visitor/").
				then().assertThat().statusCode(200).and().extract().response();

		String response = res.asString();

		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
		String user_pk=path.getString("user_pk");
		System.out.println("user_pk "+user_pk);
		return user_pk;
	}
	public static String validateVisitorPhone(String token) {

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloadValidatevisitor.validateVisitorMobile()).
				when().post("/api/v4/validate-visitor/").
				then().assertThat().statusCode(200).and().extract().response();
		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
		String user_pk=path.getString("user_pk");
		System.out.println("user_pk "+user_pk);
		return user_pk;	
	}
	
	public static String validateVisitorQR(String token) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloadValidatevisitor.validQR()).
				when().post("/api/v4/validate-visitor/").
				then().assertThat().statusCode(200).and().extract().response();

		String response = res.asString();

		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
		String user_pk=path.getString("user_pk");
		System.out.println("user_pk "+user_pk);
		return user_pk;
	}
	public static void invalidPhone(String token)
	{

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloadValidatevisitor.invalidMobile()).
				when().post("/api/v4/validate-visitor/").
				then().assertThat().statusCode(400).and().body("visitor[0]", equalTo("Invalid value for visitor, expected email/mobile_no OR qr string.")).extract().response();

		String response = res.asString();

		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
	}
	public static void invalidEmail(String token)
	{

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloadValidatevisitor.invalidEmail()).
				when().post("/api/v4/validate-visitor/").
				then().assertThat().statusCode(400).and().body("visitor[0]", equalTo("Invalid value for visitor, expected email/mobile_no OR qr string.")).extract().response();

		String response = res.asString();

		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
	}
	public static void invalidQR(String token)
	{

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloadValidatevisitor.invalidQR()).
				when().post("/api/v4/validate-visitor/").
				then().assertThat().statusCode(400).and().body("visitor[0]", equalTo( "Invalid value for visitor, expected email/mobile_no OR qr string.")).extract().response();

		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
	}
	public static void invalidActivity(String token)
	{

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloadValidatevisitor.invalidActivity()).
				when().post("/api/v4/validate-visitor/").
				then().assertThat().statusCode(400).and().body("action[0]", equalTo( "\"inqqqq\" is not a valid choice.")).extract().response();

		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
	}
	public static String getVisitorlastactivityData(String v_id,String token)
	{

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloaddataTerminallogin.terminalLogin()).
				when().get("/api/v4/visitor/"+v_id+"/activity/").
				then().assertThat().statusCode(200).extract().response();
		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
		String workflow=path.getString("workflow");
		return workflow;
	}
	public static void getVisitorWFData(String v_id,String workflow_id,String token)
	{

		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				queryParam("action", "in").
				headers("Content-Type","application/json").headers("Authorization","gatekeeper "+token+"").
				body(PayloaddataTerminallogin.terminalLogin()).
				when().get("/api/v4/visitor/"+v_id+"/workflow/"+workflow_id+"/").
				then().assertThat().statusCode(200).extract().response();
		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
		
	}
}
