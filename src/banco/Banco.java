package banco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Banco {
	private Cliente[] clientes;
	private CuentaCorriente[] cuentas;
	
	public Banco(Cliente[] clientes, CuentaCorriente[] cuentas) {
		this.clientes = clientes;
		this.cuentas = cuentas;
	}

	public Cliente[] getClientes() {
		return clientes;
	}
	public CuentaCorriente[] getCuentas() {
		return cuentas;
	}
	
	public void addCliente(String nombre, String apellidos, LocalDate fechaNacimiento, String direccion, String telefono,
			String tipoDoc, String numDoc) {
		Cliente c = new Cliente(nombre, apellidos, fechaNacimiento, direccion, telefono, tipoDoc, numDoc);
		if(clientes != null) clientes = Arrays.copyOf(clientes, clientes.length + 1);
		else clientes = new Cliente[1];
		clientes[clientes.length - 1] = c;
	}
	
	public int devolverIndexCliente(String tipoDoc, String numDoc) {
		if(clientes == null) return -1;
		int index = 0;
		while(index < cuentas.length && (!tipoDoc.equalsIgnoreCase(clientes[index].getTipoDoc()) ||
				!numDoc.equalsIgnoreCase(clientes[index].getNumDoc()))) {
			index++;
		}
		return (index == clientes.length)? -1 : index;
	}
	
	public void eliminarCliente(String tipoDoc, String numDoc) {
		int indexAEliminar = devolverIndexCliente(tipoDoc, numDoc);
		Cliente[] newClientes = new Cliente[clientes.length - 1];
		System.arraycopy(cuentas, 0, newClientes, 0, indexAEliminar);
		System.arraycopy(cuentas, indexAEliminar + 1, newClientes, indexAEliminar, clientes.length - indexAEliminar - 1);
		clientes = newClientes;
	}
	
	public CuentaCorriente buscarCliente(String tipoDoc, String numDoc) {
		int index = devolverIndexCliente(tipoDoc, numDoc);
		return cuentas[index];
	}
	
	public void addCuentaCorriente(String codigo, String clave, LocalDateTime fechaCreacion, double saldo, Cliente cliente,
			Transaccion[] transacciones) {
		CuentaCorriente c = new CuentaCorriente(codigo, clave, fechaCreacion, saldo, cliente, transacciones);
		if(cuentas != null) cuentas = Arrays.copyOf(cuentas, cuentas.length + 1);
		else cuentas = new CuentaCorriente[1];
		cuentas[cuentas.length - 1] = c;
	}
	
	public void addCuentaEspecial(String codigo, String clave, LocalDateTime fechaCreacion, double saldo, Cliente cliente,
			Transaccion[] transacciones) {
		CuentaCorriente c = new CuentaEspecial(codigo, clave, fechaCreacion, saldo, cliente, transacciones);
		if(cuentas != null) cuentas = Arrays.copyOf(cuentas, cuentas.length + 1);
		else cuentas = new CuentaCorriente[1];
		cuentas[cuentas.length - 1] = c;
	}
	
	public int devolverIndexCuenta(String codigo) {
		if(cuentas == null) return -1;
		int index = 0;
		while(index < cuentas.length && !codigo.equalsIgnoreCase(cuentas[index].getCodigo())) {
			index++;
		}
		return (index == cuentas.length)? -1 : index;
	}
	
	public void eliminarCuenta(String codigo) {
		int indexAEliminar = devolverIndexCuenta(codigo);
		CuentaCorriente[] newCuentas = new CuentaCorriente[cuentas.length - 1];
		System.arraycopy(cuentas, 0, newCuentas, 0, indexAEliminar);
		System.arraycopy(cuentas, indexAEliminar + 1, newCuentas, indexAEliminar, cuentas.length - indexAEliminar - 1);
		cuentas = newCuentas;
	}
	
	public CuentaCorriente buscarCuenta(String codigo) {
		int index = devolverIndexCuenta(codigo);
		return cuentas[index];
	}
}
