package ttc.bean;

public class SignUpKeyBean implements Bean{
    private String signUpKey;
    private String keyStatus;


    /**
    * Returns value of signUpKey
    * @return
    */
    public String getSignUpKey() {
    	return signUpKey;
    }

    /**
    * Sets new value of signUpKey
    * @param
    */
    public void setSignUpKey(String signUpKey) {
    	this.signUpKey = signUpKey;
    }

    /**
    * Returns value of keyStatus
    * @return
    */
    public String getKeyStatus() {
    	return keyStatus;
    }

    /**
    * Sets new value of keyStatus
    * @param
    */
    public void setKeyStatus(String keyStatus) {
    	this.keyStatus = keyStatus;
    }
}
