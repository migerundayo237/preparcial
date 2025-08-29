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
			String tipoDoc, String numDoc) throws EDatoMalDigitado {
		if(nombre.isEmpty() || nombre.isBlank() || nombre == null 
			|| apellidos.isEmpty() || apellidos.isBlank() || apellidos == null
			|| fechaNacimiento == null || direccion.isEmpty() || direccion.isBlank()
			|| direccion == null || telefono.isEmpty() || telefono.isBlank()
			|| telefono == null || tipoDoc.isEmpty() || tipoDoc.isBlank() 
			|| tipoDoc == null || numDoc.isEmpty() || numDoc.isBlank() || numDoc == null) {
			throw new EDatoMalDigitado("Los datos están mal digitados o son nulos.");
		}
		Cliente c = new Cliente(nombre, apellidos, fechaNacimiento, direccion, telefono, tipoDoc, numDoc);
		if(clientes != null) clientes = Arrays.copyOf(clientes, clientes.length + 1);
		else clientes = new Cliente[1];
		clientes[clientes.length - 1] = c;
	}
	
	public int devolverIndexCliente(String tipoDoc, String numDoc) {
		if(clientes == null) return -1;
		int index = 0;
		while(index < clientes.length && (!tipoDoc.equalsIgnoreCase(clientes[index].getTipoDoc()) ||
				!numDoc.equalsIgnoreCase(clientes[index].getNumDoc()))) {
			index++;
		}
		return (index == clientes.length)? -1 : index;
	}
	
	public void eliminarCliente(String tipoDoc, String numDoc) throws EDatoMalDigitado, EArregloVacio, EClienteNoEncontrado {
		if(tipoDoc.isEmpty() || tipoDoc.isBlank() || tipoDoc == null || 
				numDoc.isEmpty() || numDoc.isBlank() || numDoc == null) {
			throw new EDatoMalDigitado("Los datos están mal digitados o son nulos.");
		}
		if(clientes.length == 0 || clientes == null) {
			throw new EArregloVacio("clientes");
		}
		int indexAEliminar = devolverIndexCliente(tipoDoc, numDoc);
		if(indexAEliminar == -1) {
			throw new EClienteNoEncontrado(numDoc);
		}
		Cliente[] newClientes = new Cliente[clientes.length - 1];
		System.arraycopy(cuentas, 0, newClientes, 0, indexAEliminar);
		System.arraycopy(cuentas, indexAEliminar + 1, newClientes, indexAEliminar, clientes.length - indexAEliminar - 1);
		clientes = newClientes;
	}
	
	public Cliente buscarCliente(String tipoDoc, String numDoc) throws EDatoMalDigitado, EClienteNoEncontrado {
		if(tipoDoc.isEmpty() || tipoDoc.isBlank() || tipoDoc == null || 
				numDoc.isEmpty() || numDoc.isBlank() || numDoc == null) {
			throw new EDatoMalDigitado("Los datos están mal digitados o son nulos.");
		}
		int index = devolverIndexCliente(tipoDoc, numDoc);
		if(index == -1) {
			throw new EClienteNoEncontrado(numDoc);
		}
		return clientes[index];
	}
	
	public void addCuentaCorriente(String codigo, String clave, LocalDateTime fechaCreacion, double saldo, String tipoDoc,
			String numDoc, Transaccion[] transacciones) throws EDatoMalDigitado, EClienteNoEncontrado {
		Cliente cliente = buscarCliente(tipoDoc, numDoc);
		CuentaCorriente c = new CuentaCorriente(codigo, clave, fechaCreacion, saldo, cliente, transacciones);
		if(cuentas != null) cuentas = Arrays.copyOf(cuentas, cuentas.length + 1);
		else cuentas = new CuentaCorriente[1];
		cuentas[cuentas.length - 1] = c;
	}
	
	public void addCuentaEspecial(String codigo, String clave, LocalDateTime fechaCreacion, double saldo, String tipoDoc,
			String numDoc, Transaccion[] transacciones) throws EDatoMalDigitado, EClienteNoEncontrado {
		Cliente cliente = buscarCliente(tipoDoc, numDoc);
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
	
	public void depositarDinero(String codCuenta, String codTransaccion, double valor) throws EValorNegativo, EDatoMalDigitado {
		if(valor < 0) {
			throw new EValorNegativo();
		}
		CuentaCorriente c = buscarCuenta(codCuenta);
		c.depositarDinero(codTransaccion, valor);
	}
	
	public void retirarDinero(String codCuenta, String codTransaccion, String clave, double valor) 
			throws EClaveIncorrecta, EValorNegativo, EDatoMalDigitado {
		CuentaCorriente c = buscarCuenta(codCuenta);
		if(!clave.equals(c.getClave())) {
			throw new EClaveIncorrecta();
		}
		if(valor < 0) {
			throw new EValorNegativo();
		}
		c.retirarDinero(codTransaccion, valor);
	}
	
	public void sumarInteres() {
		for(CuentaCorriente c : cuentas) {
			c.sumarInteres();
		}
	}
	
	public static void main(String[] args) {
		Banco b = new Banco(null, null);
		try {
			b.addCliente("Miguel", "Vallejo", LocalDate.of(2006, 5, 23), "dir", "123", "CC", "1034989561");
		} catch (EDatoMalDigitado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			b.addCliente("Martín", "Serna", LocalDate.of(2006, 5, 18), "dir", "123", "CC", "1034989558");
		} catch (EDatoMalDigitado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			b.addCuentaCorriente("1234", "1234", LocalDateTime.now(), 100.0, "CC", "1034989561", null);
		} catch (EDatoMalDigitado | EClienteNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			b.addCuentaEspecial("5678", "5678", LocalDateTime.now(), 40.0, "CC", "1034989558", null);
		} catch (EDatoMalDigitado | EClienteNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Saldo Miguel: " + b.buscarCuenta("1234").getSaldo());
		System.out.println("Saldo Martín: " + b.buscarCuenta("5678").getSaldo());
		try {
			b.depositarDinero("5678", "1", 30.0);
		} catch (EValorNegativo | EDatoMalDigitado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			b.depositarDinero("5678", "1", 30.0);
		} catch (EValorNegativo | EDatoMalDigitado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Saldo Miguel: " + b.buscarCuenta("1234").getSaldo());
		System.out.println("Saldo Martín: " + b.buscarCuenta("5678").getSaldo());
		b.sumarInteres();
		System.out.println("Saldo Miguel: " + b.buscarCuenta("1234").getSaldo());
		System.out.println("Saldo Martín: " + b.buscarCuenta("5678").getSaldo());
	}
}
