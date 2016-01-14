package ttc.exception;

public class ResourceException extends IntegrationException{
    public ResourceException(String mess,Throwable t){
        super(mess,t);
    }
}
