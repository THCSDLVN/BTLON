
public class Account {
	private String aid = "";
	private String username = "";
	private String password = "";
	private String fullName = "";
	private String phoneNumber = "";
	private String sex = "";
	private String birthday = "";
	
	public void setAID(String aid){
		this.aid = aid;
	}
	public String getAID(){
		return (aid);
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return (username);
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return (password);
	}
	
	public void setFullname(String fullname){
		this.fullName = fullname;
	}
	public String getFullname(){
		return (fullName);
	}
	
	public void setPhonenumber(String phonenumber){
		this.phoneNumber = phonenumber;
	}
	public String getPhoneNumber(){
		return (phoneNumber);
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	public String getSex(){
		return (sex);
	}
	
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	public String getBirthday(){
		return(birthday);
	}
}
