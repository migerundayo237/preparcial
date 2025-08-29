package banco;

public class EValorNegativo extends Exception {
	public EValorNegativo() {
		super("El valor ingresado no puede ser negativo.");
	}
}
