package banco;

import java.time.LocalDateTime;
import java.util.Arrays;

public class CuentaCorriente {
	protected String codigo;
	protected String clave;
	protected LocalDateTime fechaCreacion;
	protected double saldo;
	protected Cliente cliente;
	protected Transaccion[] transacciones;

	public CuentaCorriente(String codigo, String clave, LocalDateTime fechaCreacion, double saldo, Cliente cliente,
			Transaccion[] transacciones) {
		this.codigo = codigo;
		this.clave = clave;
		this.fechaCreacion = fechaCreacion;
		this.saldo = saldo;
		this.cliente = cliente;
		this.transacciones = transacciones;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public String getClave() {
		return clave;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public double getSaldo() {
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public Transaccion[] getTransacciones() {
		return transacciones;
	}
	
	public void addTransaccion(String codigo, LocalDateTime fechaTransaccion, double valorImplicado) {
		Transaccion t = new Transaccion(codigo, fechaTransaccion, valorImplicado);
		if(transacciones != null) transacciones = Arrays.copyOf(transacciones, transacciones.length + 1);
		else transacciones = new Transaccion[1];
		transacciones[transacciones.length - 1] = t;
	}
	
	public int devolverIndexTransaccion(String codigo) {
		if(transacciones == null) return -1;
		int index = 0;
		while(index < transacciones.length && !codigo.equalsIgnoreCase(transacciones[index].getCodigo())) {
			index++;
		}
		return (index == transacciones.length)? -1 : index;
	}
	
	public void eliminarTransaccion(String codigo) {
		int indexAEliminar = devolverIndexTransaccion(codigo);
		Transaccion[] newTransacciones = new Transaccion[transacciones.length - 1];
		System.arraycopy(transacciones, 0, newTransacciones, 0, indexAEliminar);
		System.arraycopy(transacciones, indexAEliminar + 1, newTransacciones, indexAEliminar, transacciones.length - indexAEliminar - 1);
		transacciones = newTransacciones;
	}
	
	public Transaccion buscarTransaccion(String codigo) {
		int index = devolverIndexTransaccion(codigo);
		return transacciones[index];
	}
	
	public void depositarDinero(String codTransaccion, double valor) {
		addTransaccion(codTransaccion, LocalDateTime.now(), valor);
		saldo += valor;
	}
	
	public void retirarDinero(String codTransaccion, double valor) {
		addTransaccion(codTransaccion, LocalDateTime.now(), valor);
		saldo -= valor;
	}
	
	public void sumarInteres() {
		saldo += saldo * 0.03;
	}
}
