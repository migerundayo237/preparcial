package streaming;

import java.util.Arrays;

public class Plataforma {
	private Usuario[] usuarios;
	private Contenido[] contenido;
	
	public Plataforma(Usuario[] usuarios, Contenido[] contenido) {
		super();
		this.usuarios = usuarios;
		this.contenido = contenido;
	}
	
	public Usuario[] getUsuarios() {
		return usuarios;
	}
	public Contenido[] getContenido() {
		return contenido;
	}
	
	public void addUsuario(String nombreUsuario, String email, String tipoSuscripcion) {
		Usuario u = new Usuario(nombreUsuario, email, tipoSuscripcion);
		if(usuarios != null) usuarios = Arrays.copyOf(usuarios, usuarios.length + 1);
		else usuarios = new Usuario[1];
		usuarios[usuarios.length - 1] = u;
	}
	
	public void eliminarUsuario(String nombreUsuario) {
		int indexAEliminar = devolverIndexUsuario(nombreUsuario);
		Usuario[] newUsuarios = new Usuario[usuarios.length - 1];
		System.arraycopy(usuarios, 0, newUsuarios, 0, indexAEliminar);
		System.arraycopy(usuarios, indexAEliminar + 1, newUsuarios, indexAEliminar, usuarios.length - indexAEliminar - 1);
		usuarios = newUsuarios;
	}
	
	public Usuario buscarUsuario(String nombreUsuario) {
		int index = devolverIndexUsuario(nombreUsuario);
		return usuarios[index];
	}
	
	public void actualizarSuscripcion(String nombreUsuario, String nuevaSuscripcion) {
		Usuario u = buscarUsuario(nombreUsuario);
		u.setTipoSuscripcion(nuevaSuscripcion);
	}
	
	public int devolverIndexUsuario(String nombreUsuario) {
		if(usuarios == null) return -1;
		int index = 0;
		while(index < usuarios.length && !nombreUsuario.equals(usuarios[index].getNombreUsuario())) index++;
		return (index == usuarios.length)? -1 : index;
	}
	
	public void addPelicula(String titulo, String genero, int calificacion, int duracion) {
		Contenido p = new Pelicula(titulo, genero, calificacion, duracion);
		if(contenido != null) contenido = Arrays.copyOf(contenido, contenido.length + 1);
		else contenido = new Contenido[1];
		contenido[contenido.length - 1] = p;
	}
	
	public void addSerie(String titulo, String genero, int calificacion, int episodios) {
		Contenido s = new Serie(titulo, genero, calificacion, episodios);
		if(contenido != null) contenido = Arrays.copyOf(contenido, contenido.length + 1);
		else contenido = new Contenido[1];
		contenido[contenido.length - 1] = s;
	}
	
	public void eliminarContenido(String titulo) {
		int indexAEliminar = devolverIndexContenido(titulo);
		Contenido[] newContenido = new Contenido[contenido.length - 1];
		System.arraycopy(contenido, 0, newContenido, 0, indexAEliminar);
		System.arraycopy(contenido, indexAEliminar + 1, newContenido, indexAEliminar, contenido.length - indexAEliminar - 1);
		contenido = newContenido;
	}
	
	public Contenido buscarContenido(String titulo) {
		int index = devolverIndexContenido(titulo);
		return contenido[index];
	}
	
	public void actualizarRating(String titulo, int nuevaCalificacion) {
		Contenido c = buscarContenido(titulo);
		c.setCalificacion(nuevaCalificacion);
	}
	
	public int devolverIndexContenido(String titulo) {
		if(contenido == null) return -1;
		int index = 0;
		while(index < contenido.length && !titulo.equalsIgnoreCase(contenido[index].getTitulo())) index++;
		return (index == contenido.length)? -1 : index;
	}
	
	public void verContenido(String nombreUsuario, String titulo) {
		Usuario u = buscarUsuario(nombreUsuario);
		Contenido c = buscarContenido(titulo);
		u.verContenido(c);
	}
	
	public void mostrarReporteMensual() {
		System.out.println("Usuarios: ");
		for(Usuario u : usuarios) {
			System.out.println("\n" + u.getNombreUsuario());
		}
		System.out.println("Contenido disponible: ");
		for(Contenido c : contenido) {
			System.out.println("\n" + c.getInfo());
		}
	}
}
