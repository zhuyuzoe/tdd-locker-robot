package cn.xpbootcamp.gilded_rose.exception;

public class InsufficientLockersException extends RuntimeException {
    public InsufficientLockersException(String message) {
        super(message);
    }
}
