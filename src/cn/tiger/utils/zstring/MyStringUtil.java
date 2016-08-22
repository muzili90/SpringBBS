package cn.tiger.utils.zstring;

public class MyStringUtil {

	public static String strReplaces(String str, String... replaceStr) {

		if (str.indexOf("<code>") != 0) {
			if (replaceStr != null) {
				for (int i = 0; i < replaceStr.length; i++) {
					if (replaceStr[i].equals("<p>")) {
						str = str.replaceAll(replaceStr[i], "");
					}
					if (replaceStr[i].equals("</p>")) {
						str = str.replaceAll(replaceStr[i], "<br/>");
					}
					
					if (replaceStr[i].equals("<pre>")) {
						str = str.replaceAll(replaceStr[i], "");
					}
					
					if (replaceStr[i].equals("</pre>")) {
						str = str.replaceAll(replaceStr[i], "");
					}
					
					if (replaceStr[i].equals("<code>")) {
						str = str.replaceAll(replaceStr[i], "<code class=\"brush:java\" data-lang=\"java\"><br/>");
					}
					if (replaceStr[i].equals("<code class=\"lang-java\">")) {
						str = str.replaceAll(replaceStr[i], "<code class=\"brush:java\" data-lang=\"java\"><br/>");
					}
				}
				System.out.println(str);
			}
			return str;
		}

		return null;
	}
	
	//处理存取生日
	public static String[] getBirthdayYMD(String birthday){
		
		String[] birthdays=new String[3];
		
		birthdays=birthday.split("-");
		
		for(String s:birthdays){
			System.out.println(s);
		}
		
		return birthdays;
	}
	
	public static String getFullBirthday(String[] birthdays){
		
		String birthday="";
		
		for(String s:birthdays){
			birthday=birthday+s+"-";
		}
		
		birthday=birthday.substring(0, birthday.length()-1);

		System.out.println(birthday);
		
		return birthday;
	}
	
	//处理存取地区
		public static String[] getAddressSCQ(String address){
			
			String[] addresses=new String[3];
			
			addresses=address.split("-");
			
			for(String s:addresses){
				System.out.println(s);
			}
			
			return addresses;
		}

		public static String getFullAddress(String[] addresses){
			
			String address="";
			
			for(String s:addresses){
				address=address+s+"-";
			}
			
			address=address.substring(0, address.length()-1);

			System.out.println(address);
			
			return address;
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//getBirthdayYMD("1987-3-11");
		getFullBirthday(null);
	}

}
