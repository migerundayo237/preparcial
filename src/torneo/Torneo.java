package torneo;

import java.util.Arrays;
import java.util.Comparator;

public class Torneo {
	private Equipo[] equipos;
	
	public Torneo(Equipo[] equipos) {
		this.equipos = equipos;
	}
	
	public void addEquipo(String nombre, int rango, String pais, String entrenador, Jugador[] jugadores) {
		Equipo e = new Equipo(nombre, rango, pais, entrenador, jugadores);
		if(equipos != null) {
			equipos = Arrays.copyOf(equipos, equipos.length + 1);
		}
		else {
			equipos = new Equipo[1];
		}
		equipos[equipos.length - 1] = e;
	}
	
	public void eliminarEquipo(String nombre) {
		int indexAEliminar = devolverIndexEquipo(nombre);
		Equipo[] newEquipos = new Equipo[equipos.length - 1];
		System.arraycopy(equipos, 0, newEquipos, 0, indexAEliminar);
		System.arraycopy(equipos, indexAEliminar + 1, newEquipos, indexAEliminar, equipos.length - indexAEliminar - 1);
		equipos = newEquipos;
	}
	
	public void cambiarRangoEquipo(String nombre, int nuevoRango) {
		Equipo e = buscarEquipo(nombre);
		e.setRango(nuevoRango);
	}
	
	public Equipo buscarEquipo(String nombre) {
		int index = devolverIndexEquipo(nombre);
		return equipos[index];
	}
	
	public int devolverIndexEquipo(String nombre) {
		if(equipos == null) return -1;
		int index = 0;
		while(index < equipos.length && !nombre.equalsIgnoreCase(equipos[index].getNombre())) index++;
		return (index == equipos.length)? -1 : index;
	}
	
	public void addJugador(String nombreEquipo, String apodo, String edad, String rol) {
		Equipo e = buscarEquipo(nombreEquipo);
		e.addJugador(apodo, edad, rol);
	}
	
	public void eliminarJugador(String nombreEquipo, String tag) {
		Equipo e = buscarEquipo(nombreEquipo);
		e.eliminarJugador(tag);
	}
	
	public void actualizarJugador(String nombreEquipo, String tag, String nuevoRol) {
		Equipo e = buscarEquipo(nombreEquipo);
		e.actualizarJugador(tag, nuevoRol);
	}
	
	public Jugador buscarJugador(String nombreEquipo, String tag) {
		Equipo e = buscarEquipo(nombreEquipo);
		return e.buscarJugador(tag);
	}
	
	public void mostrarRanking() {
		Arrays.sort(equipos, Comparator.comparingInt(equipo -> equipo.getRango()));
		for(int i = 0; i < equipos.length; i++) {
			System.out.println(i + 1 + ". " + equipos[i].getNombre());
		}
	}
}
