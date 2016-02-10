package ttc.bean;

public class CautionBean implements Bean{
    private String id;
    private String date;
    private String title;
    private String cautionBody;
    private String reportPageUrl;
    private UserBean userId;
    private UserBean cautionUserId;

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
    * Returns value of cautionBody
    * @return
    */
    public String getCautionBody() {
    	return cautionBody;
    }

    /**
    * Sets new value of cautionBody
    * @param
    */
    public void setCautionBody(String cautionBody) {
    	this.cautionBody = cautionBody;
    }

    /**
    * Returns value of reportPageUrl
    * @return
    */
    public String getReportPageUrl() {
    	return reportPageUrl;
    }

    /**
    * Sets new value of reportPageUrl
    * @param
    */
    public void setReportPageUrl(String reportPageUrl) {
    	this.reportPageUrl = reportPageUrl;
    }

    /**
    * Returns value of userId
    * @return
    */
    public UserBean getUserId() {
    	return userId;
    }

    /**
    * Sets new value of userId
    * @param
    */
    public void setUserId(UserBean userId) {
    	this.userId = userId;
    }

    /**
    * Returns value of cautionUserId
    * @return
    */
    public UserBean getCautionUserId() {
    	return cautionUserId;
    }

    /**
    * Sets new value of cautionUserId
    * @param
    */
    public void setCautionUserId(UserBean cautionUserId) {
    	this.cautionUserId = cautionUserId;
    }







}
