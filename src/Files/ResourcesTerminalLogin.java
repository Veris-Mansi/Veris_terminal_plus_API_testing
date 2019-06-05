package Files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResourcesTerminalLogin {

	public static String login()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").
				body(PayloaddataTerminallogin.terminalLogin()).
				when().post("/api/v2/gatekeeper-login/").
				then().assertThat().statusCode(200).and().extract().response();

		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
		String token=path.getString("token");
		System.out.println("token "+token);
		return token;
	}
	public static void invalidusernamelogin()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").
				body(PayloaddataTerminallogin.invalidusernameterminalLogin()).
				when().post("/api/v2/gatekeeper-login/").
				then().assertThat().statusCode(400).and().body("non_field_errors[0]", equalTo("Unable to log in with provided credentials.")).extract().response();

		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
	}
	public static void invalidpasswordlogin()
	{
		RestAssured.baseURI="https://sandbox.veris.in";
		Response res =given().
				headers("Content-Type","application/json").
				body(PayloaddataTerminallogin.invalidpasswordterminalLogin()).
				when().post("/api/v2/gatekeeper-login/").
				then().assertThat().statusCode(400).and().body("non_field_errors[0]", equalTo("Unable to log in with provided credentials.")).extract().response();

		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
	}
}
