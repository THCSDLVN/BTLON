
public class Account {
	private String aid = "";
	private String fullName = "";
	private String userName = "";
	private String passWord = "";
	private String phoneNumber = "";
	private String facebook = "";
	private boolean sex;// true is male,false is female
	public String getID(){
		return aid;
	}
	public void setID(String id){
		this.aid = id;
	}
	public String getFullName(){
		return fullName;
	}
	public void setFullName(String fullname){
		this.fullName = fullname;
	}
	public String getUsername(){
		return userName;
	}
	public void setUsername(String username){
		this.userName = username;
	}
	public String getPassword(){
		return passWord;
	}
	public void setPassword(String password){
		this.passWord = password;
	}
	public String getPhonenumber(){
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public String getFacebook(){
		return facebook;
	}
	public void setFacebook(String facebook){
		this.facebook = facebook;
	}
	public boolean getSex(){
		return sex;
	}
	public void setSex(boolean sex){
		this.sex = sex;
	}
}
