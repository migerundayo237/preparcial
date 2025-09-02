package streaming;

public class Serie extends Contenido {
	private int episodios;

	public Serie(String titulo, String genero, int calificacion, int episodios) {
		super(titulo, genero, calificacion);
		this.episodios = episodios;
	}

	public int getEpisodios() {
		return episodios;
	}
	
	@Override
	public String getInfo() {
		return "Serie: " + super.getTitulo() + " - episodios: " + episodios;
	}
}
