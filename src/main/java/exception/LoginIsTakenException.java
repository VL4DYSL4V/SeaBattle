package exception;

public final class LoginIsTakenException extends Exception{

    public LoginIsTakenException(){
        super();
    }

    public LoginIsTakenException(String message){
        super(message);
    }

}
