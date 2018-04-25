package barBossHouse;

class UnlawfulActionException extends RuntimeException {
    public UnlawfulActionException() {
    }

    public UnlawfulActionException(String message) {
        super(message);
    }

    public UnlawfulActionException(String message, Throwable cause) {
        super(message, cause);
    }
}