package banco;

public class ECuentaNoEncontrada extends Exception {
	public ECuentaNoEncontrada(String s) {
		super("La cuenta " + " no fue encontrada.");
	}
}
