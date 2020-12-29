package exception;

public final class EmailIsTakenException extends Exception{

    public EmailIsTakenException() {
    }

    public EmailIsTakenException(String message) {
        super(message);
    }
}
