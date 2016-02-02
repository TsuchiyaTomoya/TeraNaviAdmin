package ttc.bean;

import java.util.ArrayList;

public class UserBean implements Bean{
    private String id;
    private String loginId;
    private String userName;
    private String nameKana;
    private String sex;
    private String sexVisibleFlag;
    private String birthDate;
    private String mailAddress;
    private String password;
    private String questionNo;
    private String secretAnswer;
    private String headerPath;
    private String iconPath;
    private BlogBean blog;
    private String adminFlag;
    private String lastLoginDate;
    private String adminLastLoginDate;
    private String userStatus;
    private String lockStartDate;
    private String lockEndDate;
    private String profile;
    private String communityAdminFlag;
    private ArrayList directMessageList;
	private CommunityBean community;


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
	* Returns value of loginName
	* @return
	*/
	public String getLoginId() {
		return loginId;
	}

	/**
	* Sets new value of loginName
	* @param
	*/
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	* Returns value of nameKana
	* @return
	*/
	public String getNameKana() {
		return nameKana;
	}

	/**
	* Sets new value of nameKana
	* @param
	*/
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	/**
	* Returns value of sex
	* @return
	*/
	public String getSex() {
		return sex;
	}

	/**
	* Sets new value of sex
	* @param
	*/
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	* Returns value of sexVisibleFlag
	* @return
	*/
	public String getSexVisibleFlag() {
		return sexVisibleFlag;
	}

	/**
	* Sets new value of sexVisibleFlag
	* @param
	*/
	public void setSexVisibleFlag(String sexVisibleFlag) {
		this.sexVisibleFlag = sexVisibleFlag;
	}

	/**
	* Returns value of birthDate
	* @return
	*/
	public String getBirthDate() {
		return birthDate;
	}

	/**
	* Sets new value of birthDate
	* @param
	*/
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	* Returns value of mailAddress
	* @return
	*/
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	* Sets new value of mailAddress
	* @param
	*/
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	* Returns value of password
	* @return
	*/
	public String getPassword() {
		return password;
	}

	/**
	* Sets new value of password
	* @param
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	* Returns value of question
	* @return
	*/
	public String getQuestionNo() {
		return questionNo;
	}

	/**
	* Sets new value of question
	* @param
	*/
	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	/**
	* Returns value of secretAnswer
	* @return
	*/
	public String getSecretAnswer() {
		return secretAnswer;
	}

	/**
	* Sets new value of secretAnswer
	* @param
	*/
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	/**
	* Returns value of headerPath
	* @return
	*/
	public String getHeaderPath() {
		return headerPath;
	}

	/**
	* Sets new value of headerPath
	* @param
	*/
	public void setHeaderPath(String headerPath) {
		this.headerPath = headerPath;
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
	* Returns value of blog
	* @return
	*/
	public BlogBean getBlog() {
		return blog;
	}

	/**
	* Sets new value of blog
	* @param
	*/
	public void setBlog(BlogBean blog) {
		this.blog = blog;
	}

	/**
	* Returns value of adminFlag
	* @return
	*/
	public String getAdminFlag() {
		return adminFlag;
	}

	/**
	* Sets new value of adminFlag
	* @param
	*/
	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	/**
	* Returns value of lastLoginDate
	* @return
	*/
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	* Sets new value of lastLoginDate
	* @param
	*/
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	* Returns value of adminLastLoginDate
	* @return
	*/
	public String getAdminLastLoginDate() {
		return adminLastLoginDate;
	}

	/**
	* Sets new value of adminLastLoginDate
	* @param
	*/
	public void setAdminLastLoginDate(String adminLastLoginDate) {
		this.adminLastLoginDate = adminLastLoginDate;
	}

	/**
	* Returns value of userStatus
	* @return
	*/
	public String getUserStatus() {
		return userStatus;
	}

	/**
	* Sets new value of userStatus
	* @param
	*/
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	* Returns value of lockStartDate
	* @return
	*/
	public String getLockStartDate() {
		return lockStartDate;
	}

	/**
	* Sets new value of lockStartDate
	* @param
	*/
	public void setLockStartDate(String lockStartDate) {
		this.lockStartDate = lockStartDate;
	}

	/**
	* Returns value of lockEndDate
	* @return
	*/
	public String getLockEndDate() {
		return lockEndDate;
	}

	/**
	* Sets new value of lockEndDate
	* @param
	*/
	public void setLockEndDate(String lockEndDate) {
		this.lockEndDate = lockEndDate;
	}

	/**
	* Returns value of profile
	* @return
	*/
	public String getProfile() {
		return profile;
	}

	/**
	* Sets new value of profile
	* @param
	*/
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	* Returns value of communityAdminFlag
	* @return
	*/
	public String getCommunityAdminFlag() {
		return communityAdminFlag;
	}

	/**
	* Sets new value of communityAdminFlag
	* @param
	*/
	public void setCommunityAdminFlag(String communityAdminFlag) {
		this.communityAdminFlag = communityAdminFlag;
	}

	/**
	* Returns value of directMessageList
	* @return
	*/
	public ArrayList getDirectMessageList() {
		return directMessageList;
	}

	/**
	* Sets new value of directMessageList
	* @param
	*/
	public void setDirectMessageList(ArrayList directMessageList) {
		this.directMessageList = directMessageList;
	}

	public void setCommunity(CommunityBean community){
		this.community = community;
	}

	public CommunityBean getCommunity(){
		return community;
	}
}
