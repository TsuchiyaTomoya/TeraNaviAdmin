package ttc.bean;

public class DirectMessageBean implements Bean{
    private String messageId;
    private String toUserId;
    private String fromUserId;
    private String messageBody;
    private String date;

    /**
    * Returns value of messageId
    * @return
    */
    public String getMessageId() {
    	return messageId;
    }

    /**
    * Sets new value of messageId
    * @param
    */
    public void setMessageId(String messageId) {
    	this.messageId = messageId;
    }

    /**
    * Returns value of toUserId
    * @return
    */
    public String getToUserId() {
    	return toUserId;
    }

    /**
    * Sets new value of toUserId
    * @param
    */
    public void setToUserId(String toUserId) {
    	this.toUserId = toUserId;
    }

    /**
    * Returns value of fromUserId
    * @return
    */
    public String getFromUserId() {
    	return fromUserId;
    }

    /**
    * Sets new value of fromUserId
    * @param
    */
    public void setFromUserId(String fromUserId) {
    	this.fromUserId = fromUserId;
    }

    /**
    * Returns value of messageBody
    * @return
    */
    public String getMessageBody() {
    	return messageBody;
    }

    /**
    * Sets new value of messageBody
    * @param
    */
    public void setMessageBody(String messageBody) {
    	this.messageBody = messageBody;
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
}
