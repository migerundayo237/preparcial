package banco;

public class ETransaccionNoEncontrada extends Exception {
	public ETransaccionNoEncontrada(String s) {
		super("La transacción " + s + " no fue encontrada.");
	}
}
