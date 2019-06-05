package Validatevisitor;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.PayloaddataTerminallogin;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Files.Resourcevalidatevisitor;
import Files.*;
public class ValidateVisitorTest {

	String user_pk="";
	String token="";
	String invalid_token="291b12850a914d0dba55bd5aa62b16d3769797";
	
	@Test(priority=1,groups="TerminalLogin")
	public void terminalinvalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
		headers("Content-Type","application/json").headers("Authorization","gatekeeper "+invalid_token).
		when().post("/api/v2/gatekeeper-login/").
		then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=2,groups="TerminalLogin")
	public void Login()
	{
		token=ResourcesTerminalLogin.login();
		System.out.println("token is "+token);
	}
	
	@Test(priority=3,groups="Validatevisitot")
	public void visitorinvalidToken()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
		headers("Content-Type","application/json").headers("Authorization","gatekeeper "+invalid_token).
		when().post("/api/v4/validate-visitor/").
		then().assertThat().statusCode(401).and().body("detail", equalTo("Invalid token.")).extract().response();
	}
	@Test(priority=4,groups="Validatevisitot")
	public void ValidatevisitorMobile()
	{
		user_pk=Resourcevalidatevisitor.validateVisitorPhone(token);
		System.out.println("user_pk is "+user_pk);
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

}
