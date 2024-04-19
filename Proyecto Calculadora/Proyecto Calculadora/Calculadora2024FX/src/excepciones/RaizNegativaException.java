package excepciones;

public class RaizNegativaException extends Exception {
    private static final long serialVersionUID = 1L;

	public RaizNegativaException() {
        super("No se puede calcular la raíz de un número negativo!");
    }
}

