package ttc.bean;

public class ContactBean implements Bean{
    private String id;
    private String userName;
    private String title;
    private String contactBody;
    private String date;
    private String address;
    private String category;


    /**
    * Returns value of id
    * @return
    */
    public String getId() {
    	return id;
    }

    /**
    * Sets new value of id
    * @param
    */
    public void setId(String id) {
    	this.id = id;
    }

    /**
    * Returns value of userName
    * @return
    */
    public String getUserName() {
    	return userName;
    }

    /**
    * Sets new value of userName
    * @param
    */
    public void setUserName(String userName) {
    	this.userName = userName;
    }

    /**
    * Returns value of title
    * @return
    */
    public String getTitle() {
    	return title;
    }

    /**
    * Sets new value of title
    * @param
    */
    public void setTitle(String title) {
    	this.title = title;
    }

    /**
    * Returns value of contactBody
    * @return
    */
    public String getContactBody() {
    	return contactBody;
    }

    /**
    * Sets new value of contactBody
    * @param
    */
    public void setContactBody(String contactBody) {
    	this.contactBody = contactBody;
    }

    /**
    * Returns value of date
    * @return
    */
    public String getDate() {
    	return date;
    }

    /**
    * Sets new value of date
    * @param
    */
    public void setDate(String date) {
    	this.date = date;
    }

    /**
    * Returns value of address
    * @return
    */
    public String getAddress() {
    	return address;
    }

    /**
    * Sets new value of address
    * @param
    */
    public void setAddress(String address) {
    	this.address = address;
    }

    /**
    * Returns value of category
    * @return
    */
    public String getCategory() {
    	return category;
    }

    /**
    * Sets new value of category
    * @param
    */
    public void setCategory(String category) {
    	this.category = category;
    }
}
