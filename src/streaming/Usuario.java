package streaming;

import java.util.Arrays;

public class Usuario {
	private String nombreUsuario;
	private String email;
	private String tipoSuscripcion;
	private Contenido[] contenidoVisto;
	
	public Usuario(String nombreUsuario, String email, String tipoSuscripcion) {
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.tipoSuscripcion = tipoSuscripcion;
		this.contenidoVisto = null;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	
	public void setTipoSuscripcion(String tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Contenido[] getContenidoVisto() {
		return contenidoVisto;
	}
	
	public void verContenido(Contenido c) {
		if(contenidoVisto != null) contenidoVisto = Arrays.copyOf(contenidoVisto, contenidoVisto.length + 1);
		else contenidoVisto = new Contenido[1];
		contenidoVisto[contenidoVisto.length - 1] = c;
	}
}
