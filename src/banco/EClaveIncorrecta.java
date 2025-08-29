package banco;

public class EClaveIncorrecta extends Exception {
	public EClaveIncorrecta() {
		super("La clave introducida es incorrecta.");
	}
}
