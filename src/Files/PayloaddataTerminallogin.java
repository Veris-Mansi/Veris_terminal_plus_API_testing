package Files;

public class PayloaddataTerminallogin {

	public static String terminalLogin()
	{
		String s="    {\r\n" + 
				"        \"username\": \"TYPE72\",\r\n" + 
				"        \"password\": \"1\"\r\n" + 
				"    }";
		return s;
	}
	public static String invalidusernameterminalLogin()
	{
		String s="    {\r\n" + 
				"        \"username\": \"zzzsss33\",\r\n" + 
				"        \"password\": \"1\"\r\n" + 
				"    }";
		return s;
	}
	public static String invalidpasswordterminalLogin()
	{
		String s="    {\r\n" + 
				"        \"username\": \"TYPE72\",\r\n" + 
				"        \"password\": \"111\"\r\n" + 
				"    }";
		return s;
	}
}
