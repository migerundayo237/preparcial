package torneo;

import java.util.Random;

public class Jugador {
	private String apodo;
	private String edad;
	private String rol;
	private String tag;
	
	public Jugador(String apodo, String edad, String rol) {
		this.apodo = apodo;
		this.edad = edad;
		this.rol = rol;
		tag = generarTag();
	}
	public String getEdad() {
		return edad;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getApodo() {
		return apodo;
	}
	public String getTag() {
		return tag;
	}
	
	public String generarTag() {
		Random r = new Random();
		int numTag = r.nextInt(10000);
		String tag = String.format("%04d", numTag);
		return tag;
	}
}
