package ttc.command;

import ttc.context.RequestContext;
import ttc.context.ResponseContext;

import ttc.exception.BusinessLogicException;

public abstract class AbstractCommand{
	private RequestContext reqContext;

	public void init(RequestContext reqc){
		reqContext = reqc;
	}

	public RequestContext getRequestContext(){
		return reqContext;
	}

	public abstract ResponseContext execute(ResponseContext resc)throws BusinessLogicException;
}
