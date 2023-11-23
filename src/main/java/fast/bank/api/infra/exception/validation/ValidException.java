package fast.bank.api.infra.exception.validation;

public class ValidException extends RuntimeException{
    public ValidException(String message) {
        super(message);
    }
}
