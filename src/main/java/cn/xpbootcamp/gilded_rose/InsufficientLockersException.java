package cn.xpbootcamp.gilded_rose;

public class InsufficientLockersException extends RuntimeException {
    InsufficientLockersException(String message) {
        super(message);
    }
}
