package Toy.KnitCraft.exception;

public class AlreadyExistsEmailException extends KnitException{

    private static final String MESSAGE = "이미 존재하는 이메일입니다.";

    public AlreadyExistsEmailException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
