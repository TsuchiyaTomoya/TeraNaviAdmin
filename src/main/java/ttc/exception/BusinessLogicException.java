package ttc.exception;

public class BusinessLogicException extends PresentationException{
    public BusinessLogicException(String mess,Throwable t){
        super(mess,t);
    }
}
