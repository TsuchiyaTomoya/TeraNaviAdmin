package ttc.util.mailer;

public abstract class AbstractMailer{
    private String address;
	private String account;
	private String pass;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public String getAccount(){
		return account;
	}

	public void setPass(String pass){
		this.pass = pass;
	}

	public String getPass(){
		return pass;
	}

	public abstract String sendMail(String toAddress,String title,String mess);
}
