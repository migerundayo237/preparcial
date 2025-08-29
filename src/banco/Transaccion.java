package banco;

import java.time.LocalDateTime;

public class Transaccion {
	private String codigo;
	private LocalDateTime fechaTransaccion;
	private double valorImplicado;

	public Transaccion(String codigo, LocalDateTime fechaTransaccion, double valorImplicado) {
		this.codigo = codigo;
		this.fechaTransaccion = fechaTransaccion;
		this.valorImplicado = valorImplicado;
	}

	public String getCodigo() {
		return codigo;
	}

	public LocalDateTime getFechaTransaccion() {
		return fechaTransaccion;
	}

	public double getValorImplicado() {
		return valorImplicado;
	}
}
