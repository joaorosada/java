package trabalho;

public class TelaRoupaException extends RuntimeException {
    private Boolean falha;
    public TelaRoupaException(String message) {
        super(message);
        this.falha = Boolean.TRUE;
    }
    public Boolean isFalha() {
        return falha;
    }
}
