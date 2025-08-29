package banco;

import java.time.LocalDateTime;
import java.util.Arrays;

public class CuentaEspecial extends CuentaCorriente {
	
	public CuentaEspecial(String codigo, String clave, LocalDateTime fechaCreacion, double saldo, Cliente cliente,
			Transaccion[] transacciones) {
		super(codigo, clave, fechaCreacion, saldo, cliente, transacciones);
	}
	
	@Override
	public void sumarInteres() {
		double sumaDepositos = 0.0;
	    int tomados = 0;
	    if(transacciones != null) {
	    	for(int i = transacciones.length - 1; i >= 0 && tomados < 3; i--) {
		        double v = transacciones[i].getValorImplicado();
		        if (v > 0) {          
		            sumaDepositos += v;
		            tomados++;
		        }
		    }
	    }
	    saldo += sumaDepositos * 0.03;
	    saldo += saldo * 0.01;
	}

	@Override
	public String toString() {
		return "CuentaEspecial [transacciones=" + Arrays.toString(transacciones) + ", codigo=" + codigo + ", clave="
				+ clave + ", fechaCreacion=" + fechaCreacion + ", saldo=" + saldo + ", cliente=" + cliente + "]";
	}
}
