package streaming;

public abstract class Contenido {
	protected String titulo;
	protected String genero;
	protected int calificacion;
	
	public Contenido(String titulo, String genero, int calificacion) {
		this.titulo = titulo;
		this.genero = genero;
		this.calificacion = calificacion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getGenero() {
		return genero;
	}
	
	public abstract String getInfo();
}
