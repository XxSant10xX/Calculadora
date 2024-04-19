package excepciones;

public class DivisionPorCeroException extends Exception {
    private static final long serialVersionUID = 4393126391899790064L;

	public DivisionPorCeroException() {
        super("¡No se puede dividir por cero!");
    }
}

