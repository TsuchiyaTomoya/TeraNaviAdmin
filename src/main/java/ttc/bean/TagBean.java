package ttc.bean;

public class TagBean implements Bean{
    private String id;
    private String name;


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
    * Returns value of name
    * @return
    */
    public String getName() {
    	return name;
    }

    /**
    * Sets new value of name
    * @param
    */
    public void setName(String name) {
    	this.name = name;
    }
}
