package ttc.bean;

public class RulesBean implements Bean{
    private String ruleId;
    private String ruleDate;
    private String ruleBody;

    
	/**
	* Returns value of ruleId
	* @return
	*/
	public String getRuleId() {
		return ruleId;
	}

	/**
	* Sets new value of ruleId
	* @param
	*/
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	* Returns value of ruleDate
	* @return
	*/
	public String getRuleDate() {
		return ruleDate;
	}

	/**
	* Sets new value of ruleDate
	* @param
	*/
	public void setRuleDate(String ruleDate) {
		this.ruleDate = ruleDate;
	}

	/**
	* Returns value of ruleBody
	* @return
	*/
	public String getRuleBody() {
		return ruleBody;
	}

	/**
	* Sets new value of ruleBody
	* @param
	*/
	public void setRuleBody(String ruleBody) {
		this.ruleBody = ruleBody;
	}
}
