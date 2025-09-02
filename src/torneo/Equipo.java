package torneo;

import java.util.Arrays;

public class Equipo {
	private String nombre;
	private int rango;
	private String pais;
	private String entrenador;
	private Jugador[] jugadores;
	
	public Equipo(String nombre, int rango, String pais, String entrenador, Jugador[] jugadores) {
		this.nombre = nombre;
		this.rango = rango;
		this.pais = pais;
		this.entrenador = entrenador;
		if(jugadores.length > 6) {
			throw new IllegalArgumentException("Un equipo no puede tener más de 6 miembros.");
		}
		this.jugadores = jugadores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRango() {
		return rango;
	}

	public void setRango(int rango) {
		this.rango = rango;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public Jugador[] getJugadores() {
		return jugadores;
	}
	
	public void addJugador(String apodo, String edad, String rol) {
		Jugador j = new Jugador(apodo, edad, rol);
		if(jugadores != null && jugadores.length > 6) {
			jugadores = Arrays.copyOf(jugadores, jugadores.length + 1);
		}
		else {
			jugadores = new Jugador[1];
		}
		jugadores[jugadores.length - 1] = j;
	}
	
	public void eliminarJugador(String tag) {
		int indexAEliminar = devolverIndexJugador(tag);
		Jugador[] newJugadores = new Jugador[jugadores.length - 1];
		System.arraycopy(jugadores, 0, newJugadores, 0, indexAEliminar);
		System.arraycopy(jugadores, indexAEliminar + 1, newJugadores, indexAEliminar, jugadores.length - indexAEliminar - 1);
		jugadores = newJugadores;
	}
	
	public void actualizarJugador(String tag, String nuevoRol) {
		Jugador j = buscarJugador(tag);
		j.setRol(nuevoRol);
	}
	
	public Jugador buscarJugador(String tag) {
		int index = devolverIndexJugador(tag);
		return jugadores[index];
	}
	
	public int devolverIndexJugador(String tag) {
		if(jugadores == null) return -1;
		int index = 0;
		while(index < jugadores.length && !tag.equalsIgnoreCase(jugadores[index].getTag())) index++;
		return (index == jugadores.length)? -1 : index;
	}
}
