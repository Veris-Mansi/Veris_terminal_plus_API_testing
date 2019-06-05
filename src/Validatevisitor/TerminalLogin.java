package Validatevisitor;
//valid mobile no
//valid email
//invalid email
//invalid phone
//checkin(phone)
//check-out(phone)
//general(phone)
//validQRcode(phone)
//invalidqrcode(phone)

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import Files.ResourcesTerminalLogin;
//import Files.PayloaddataInvite;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class TerminalLogin {

	String token="";
	
	@Test(groups="TerminalLogin")
	public void terminalLogin()
	{
		token=ResourcesTerminalLogin.login();
		
	}
	@Test(groups="TerminalLogin")
	public void terminalinvalidUsername()
	{
		ResourcesTerminalLogin.invalidusernamelogin();
	}
	@Test(groups="TerminalLogin")
	public void terminalinvalidPasssword()
	{
		ResourcesTerminalLogin.invalidusernamelogin();
	}
}
