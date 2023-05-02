package Toy.KnitCraft.exception;

public abstract class KnitException extends RuntimeException {
    public KnitException(String message) {
        super(message);
    }

    public KnitException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();
}
