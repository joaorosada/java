package ex;

public class CalculadoraException extends RuntimeException{
    private Boolean falha;
    public CalculadoraException(String message) {
        super(message);
        this.falha = Boolean.TRUE;
    }

    public Boolean isFalha() {
        return falha;
    }
}
