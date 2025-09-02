package galeria;

public class Escultura extends Obra {
	private String material;

	public Escultura(String titulo, String artista, String anio, String material) {
		super(titulo, artista, anio);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}
	
	@Override
	public String getInfo() {
		return "Escultura: " + titulo + " por " + artista + ", " + material;
	}
}
