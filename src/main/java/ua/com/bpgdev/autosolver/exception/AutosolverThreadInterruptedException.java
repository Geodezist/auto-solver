package ua.com.bpgdev.autosolver.exception;

public class AutosolverThreadInterruptedException extends RuntimeException {
    public AutosolverThreadInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
