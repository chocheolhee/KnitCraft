package Toy.KnitCraft.exception;

public class InvalidSigninInformation extends KnitException {

    private static final String MESSAGE = "아이디 또는 비밀번호가 일치하지 않습니다";
    public InvalidSigninInformation() {
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }
}
