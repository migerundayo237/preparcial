package banco;

public class EArregloVacio extends Exception {
	public EArregloVacio(String s) {
		super("El arreglo " + s + " está vacío.");
	}
}
