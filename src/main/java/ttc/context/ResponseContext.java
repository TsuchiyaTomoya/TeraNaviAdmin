package ttc.context;

public interface ResponseContext{
	public Object getResult();
	public String getTarget();
	public void setResult(Object bean);
	public void setTarget(String transferInfo);
	public void setResponse(Object response);
	public Object getResponse();
}
