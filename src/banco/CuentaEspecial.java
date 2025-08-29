package banco;

import java.time.LocalDateTime;

public class CuentaEspecial extends CuentaCorriente {
	private Transaccion[] transacciones;
	
	public CuentaEspecial(String codigo, String clave, LocalDateTime fechaCreacion, double saldo, Cliente cliente,
			Transaccion[] transacciones) {
		super(codigo, clave, fechaCreacion, saldo, cliente, transacciones);
	}
	
	@Override
	public void sumarInteres() {
		double sumaDepositos = 0.0;
	    int tomados = 0;
	    for(int i = transacciones.length - 1; i >= 0 && tomados < 3; i--) {
	        double v = transacciones[i].getValorImplicado();
	        if (v > 0) {          
	            sumaDepositos += v;
	            tomados++;
	        }
	    }
	    saldo += sumaDepositos * 0.03;
	    saldo += saldo * 0.01;
	}
}
