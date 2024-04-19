package modelo;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;

public class Calculadora {

	//Big Decimal lo de suma y todo eso

	// Atributos
	private double num1;
	private double num2;

	private String numActual;
	private String operacion;

	private boolean esNumResultado;

	private String numMemoria;
	private String numCopyPaste;

	// Constructor
	public Calculadora() {
		this.num1 = 0;
		this.num2 = 0;
		this.numActual = "0";
		this.numMemoria = "0";
		this.operacion = "";
	}


	// Getters and Setters
	public void setNum1(double num1) {
		this.num1 = num1;
	}

	public void setNum2(double num2) {
		this.num2 = num2;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public void setNumActual(String numActual) {
		this.numActual = numActual;
	}

	public void setNumMemoria(String numMemoria) {
		this.numMemoria = numMemoria;
	}



	public double getNum1() {
		return num1;
	}


	public double getNum2() {
		return num2;
	}


	public String getNumActual() {
		return numActual;
	}


	public String getOperacion() {
		return operacion;
	}


	public boolean isEsNumResultado() {
		return esNumResultado;
	}


	public String getNumMemoria() {
		return numMemoria;
	}


	public String getNumCopyPaste() {
		return numCopyPaste;
	}


	// Operaciones Aritmeticas
	public double sumar() {

		double res = num1 + num2;

		return res;
	}

	public double restar() {
		return this.num1 - this.num2;

	}

	public double multiplicar() {
		return this.num1 * this.num2;
	}

	public double dividir() throws DivisionPorCeroException {

		if (this.num2 == 0) {

			throw new DivisionPorCeroException();
		}else {
			return this.num1 / this.num2;
		}
	}

	public void porcentaje() {

	}

	public void calcular () throws DivisionPorCeroException {

		num2 = Double.parseDouble(numActual);
		switch (operacion) {
		case "+":
			numActual = String.valueOf(sumar());
			break;

		case "-":
			numActual = String.valueOf(restar());
			break;

		case "*":
			numActual = String.valueOf(multiplicar());
			break;

		case "/":
			if (num2 == 0) {
				throw new DivisionPorCeroException();
			}
			numActual = String.valueOf(dividir());
			break;
		default:
			break;
		}
	}

	public double raiz() throws RaizNegativaException {
		double num = Double.parseDouble(numActual);
		if (num < 0) {
			throw new RaizNegativaException();
		}
		return Math.sqrt(num);
	}



	public double inversa() throws DivisionPorCeroException {
		if (num1 == 0) {
			throw new DivisionPorCeroException();
		}
		return 1 / num1;
	}

	// Cambiar signo al valor actual
	public void cambiarSigno() {

		if (!numActual.isEmpty()) {

			if (this.numActual.charAt(0) == '-') {

				this.numActual = numActual.substring(1);
			}else {
				this.numActual = "-" + numActual;
			}
		}
	}

	// Resetear todas las variables
	public void reset() {
		num1 = 0;
		num2 = 0;
		numActual = "";
		operacion = "";
		esNumResultado = false;
		numMemoria = "";
		numCopyPaste = "";
	}

	public void clear() {

		num1 = 0;		
		num2 = 0;
		numActual = "0";
		operacion = "";		
	}

	public String concatenar(String numero) {
		return numActual + numero;
	}

	public void retroceder() {

		if (this.numActual.length() > 0) {
			this.numActual = numActual.substring(0, numActual.length() - 1);
		}

	}



	// Método para insertar un número en el numActual
	public void insertarNumero(String numero) {

		if (this.numActual.contains(".") && numero.equals(".")) {

		}else if (this.numActual.equals("0") ) {
			this.numActual = numero;
		}else {
			numActual += numero;
		}

	}

	public String asignarOperacion(String operacion) {
		setOperacion(operacion);
		setNum1(Double.parseDouble(getNumActual()));
		setNumActual("");

		return numActual;
	}




	public void clearError() {
		this.numActual = "0";

	}

	public void memoryClear() {
		this.numActual = "0";
	}

	public void memoryStorage() {
		numMemoria = numActual;
	}

	public void memoryRecall() {
		numActual = numMemoria;
	}

	public void memorySumar() {

	}

	public void memoryRestar() {

	}

	// Calcular Operaciones

}
