package streaming;

public class Pelicula extends Contenido {
	private int duracion;

	public Pelicula(String titulo, String genero, int calificacion, int duracion) {
		super(titulo, genero, calificacion);
		this.duracion = duracion;
	}

	public int getDuracion() {
		return duracion;
	}
	
	@Override
	public String getInfo() {
		return "Película: " + super.getTitulo() + " - duración: " + duracion + " minutos.";
	}
}
