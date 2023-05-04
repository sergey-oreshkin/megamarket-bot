package group.megamarket.userservice.exception;

/**
 * Исключение для ненайденного юзера
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
