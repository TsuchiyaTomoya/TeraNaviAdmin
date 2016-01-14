package ttc.bean;

public class ChatBean implements Bean{
    private String chatId;
    private String userId;
    private String userName;
    private String iconPath;
    private String body;
    private String date;
    private String deleteFlag;


    /**
    * Returns value of chatId
    * @return
    */
    public String getChatId() {
    	return chatId;
    }

    /**
    * Sets new value of chatId
    * @param
    */
    public void setChatId(String chatId) {
    	this.chatId = chatId;
    }

    /**
    * Returns value of userId
    * @return
    */
    public String getUserId() {
    	return userId;
    }

    /**
    * Sets new value of userId
    * @param
    */
    public void setUserId(String userId) {
    	this.userId = userId;
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
    * Returns value of iconPath
    * @return
    */
    public String getIconPath() {
    	return iconPath;
    }

    /**
    * Sets new value of iconPath
    * @param
    */
    public void setIconPath(String iconPath) {
    	this.iconPath = iconPath;
    }

    /**
    * Returns value of body
    * @return
    */
    public String getBody() {
    	return body;
    }

    /**
    * Sets new value of body
    * @param
    */
    public void setBody(String body) {
    	this.body = body;
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
    * Returns value of deleteFlag
    * @return
    */
    public String getDeleteFlag() {
    	return deleteFlag;
    }

    /**
    * Sets new value of deleteFlag
    * @param
    */
    public void setDeleteFlag(String deleteFlag) {
    	this.deleteFlag = deleteFlag;
    }
}
