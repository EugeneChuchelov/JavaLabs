package barBossHouse;

public class NoFreeTableException extends Exception {
    public NoFreeTableException() {
    }

    public NoFreeTableException(String message) {
        super(message);
    }

    public NoFreeTableException(String message, Throwable cause) {
        super(message, cause);
    }
}