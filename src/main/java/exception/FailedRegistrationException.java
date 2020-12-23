package exception;

public final class FailedRegistrationException extends Exception {

    public FailedRegistrationException(){
        super();
    }

    public FailedRegistrationException(String message){
        super(message);
    }

}
