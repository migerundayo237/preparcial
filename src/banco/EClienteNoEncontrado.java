package banco;

public class EClienteNoEncontrado extends Exception {
	public EClienteNoEncontrado(String s) {
		super("El cliente " + s + " no fue encontrado.");
	}
}
