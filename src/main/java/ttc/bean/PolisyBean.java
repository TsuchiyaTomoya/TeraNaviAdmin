package ttc.bean;

public class PolisyBean implements Bean{
    private String polisyId;
    private String polisyDate;
    private String polisyBody;

    
	/**
	* Returns value of polisyId
	* @return
	*/
	public String getPolisyId() {
		return polisyId;
	}

	/**
	* Sets new value of polisyId
	* @param
	*/
	public void setPolisyId(String polisyId) {
		this.polisyId = polisyId;
	}

	/**
	* Returns value of polisyDate
	* @return
	*/
	public String getPolisyDate() {
		return polisyDate;
	}

	/**
	* Sets new value of polisyDate
	* @param
	*/
	public void setPolisyDate(String polisyDate) {
		this.polisyDate = polisyDate;
	}

	/**
	* Returns value of polisyBody
	* @return
	*/
	public String getPolisyBody() {
		return polisyBody;
	}

	/**
	* Sets new value of polisyBody
	* @param
	*/
	public void setPolisyBody(String polisyBody) {
		this.polisyBody = polisyBody;
	}
}
