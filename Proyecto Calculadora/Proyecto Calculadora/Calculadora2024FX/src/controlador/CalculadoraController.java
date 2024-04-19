package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Calculadora;
import utilidades.I18N;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;

public class CalculadoraController implements Initializable {

	@FXML private Button boton_0;
	@FXML private Button boton_1;
	@FXML private Button boton_2;
	@FXML private Button boton_3;
	@FXML private Button boton_4;
	@FXML private Button boton_5;
	@FXML private Button boton_6;
	@FXML private Button boton_7;
	@FXML private Button boton_8;
	@FXML private Button boton_9;

	@FXML private Button boton_mas;
	@FXML private Button boton_menos;
	@FXML private Button boton_multiplicacion;
	@FXML private Button boton_porcentaje;
	@FXML private Button boton_raiz;
	@FXML private Button boton_division;
	@FXML private Button boton_igual;
	@FXML private Button boton_coma;

	@FXML private Button boton_Mmas;
	@FXML private Button boton_Mmenos;
	@FXML private Button boton_c;
	@FXML private Button boton_ce;
	@FXML private Button boton_invertir;
	@FXML private Button boton_masMenos;
	@FXML private Button boton_mc; 
	@FXML private Button boton_mr;
	@FXML private Button boton_ms;
	@FXML private Button boton_borrar;


	@FXML private AnchorPane fondo_0;
	@FXML private Label labelMostrarFecha;
	@FXML private MenuBar menu;
	@FXML private MenuItem menu_AcercaDe;
	@FXML private MenuItem menu_Ayuda;
	@FXML private MenuItem menu_Copiar;
	@FXML private MenuItem menu_Historial;
	@FXML private MenuItem menu_Pegar;
	@FXML private MenuItem menu_Salir;
	@FXML private TextField textFiel_Pantalla;
	@FXML private Menu menu_Edicion;
	@FXML private Label santy;
	@FXML private Menu menu_ver;
	@FXML private Menu idioma;
	@FXML private Menu ayuda;


	@FXML private MenuItem castellano;
	@FXML private MenuItem ingles;
	@FXML private MenuItem valenciano;



	private Calculadora calculadora;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Crear la Calculadora
		calculadora = new Calculadora();

		//Fecha
		actualizarFecha();

		//Idiomas
		menu_AcercaDe.textProperty().bind(I18N.createStringBinding("form.acercade"));
		menu_Historial.textProperty().bind(I18N.createStringBinding("form.historial"));
		menu_Salir.textProperty().bind(I18N.createStringBinding("form.salir"));
		menu_ver.textProperty().bind(I18N.createStringBinding("form.ver"));
		ayuda.textProperty().bind(I18N.createStringBinding("form.ayuda"));
		idioma.textProperty().bind(I18N.createStringBinding("form.idioma"));
		menu_Ayuda.textProperty().bind(I18N.createStringBinding("form.ayuda"));

		ingles.textProperty().bind(I18N.createStringBinding("form.ingles"));
		castellano.textProperty().bind(I18N.createStringBinding("form.castellano"));
		valenciano.textProperty().bind(I18N.createStringBinding("form.valenciano"));



		// Asignar los Eventos de las opciones de menu
		menu_Salir.setOnAction((event) -> salir());
		menu_AcercaDe.setOnAction((event) -> mostrarAcercaDe("/vista/AcercaDe.fxml","Acerca De"));
		menu_Ayuda.setOnAction((event) -> mostrarVentanaAyuda("/vista/Ayuda.fxml", "Ayuda"));


		// Asignar los Eventos a los botones
		boton_mas.setOnMouseClicked((event) -> asignarOperacion("+"));
		boton_menos.setOnMouseClicked((event) -> asignarOperacion("-"));
		boton_multiplicacion.setOnMouseClicked((event) -> asignarOperacion("*"));
		boton_division.setOnMouseClicked((event) -> asignarOperacion("/"));


		boton_raiz.setOnMouseClicked((event) -> {
			try {
				raiz();
			} catch (RaizNegativaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


		boton_masMenos.setOnMouseClicked((event) -> cambiarSigno());
		boton_igual.setOnMouseClicked((event) -> calcular());
		boton_borrar.setOnMouseClicked((event) -> retroceder());
		boton_coma.setOnMouseClicked((event) -> insertarNumero(","));

		boton_c.setOnMouseClicked((event) -> clear());
		boton_ce.setOnMouseClicked((event)-> clearError());

		boton_mc.setOnMouseClicked((event) -> memoryClear());
		boton_mr.setOnMouseClicked((event) -> memoryRecall());
		boton_ms.setOnMouseClicked((event) -> memoryStorage());
		boton_Mmas.setOnMouseClicked((event) -> memorySumar());
		boton_Mmenos.setOnMouseClicked((event) -> memoryRestar());


		valenciano.setOnAction((event) -> setIdioma("ca"));
		castellano.setOnAction((event) -> setIdioma("es"));
		ingles.setOnAction((event) -> setIdioma("en"));

		boton_0.setOnMouseClicked((event) -> insertarNumero("0"));
		boton_1.setOnMouseClicked((event) -> insertarNumero("1"));
		boton_2.setOnMouseClicked((event) -> insertarNumero("2"));
		boton_3.setOnMouseClicked((event) -> insertarNumero("3"));
		boton_4.setOnMouseClicked((event) -> insertarNumero("4"));
		boton_5.setOnMouseClicked((event) -> insertarNumero("5"));
		boton_6.setOnMouseClicked((event) -> insertarNumero("6"));
		boton_7.setOnMouseClicked((event) -> insertarNumero("7"));
		boton_8.setOnMouseClicked((event) -> insertarNumero("8"));
		boton_9.setOnMouseClicked((event) -> insertarNumero("9"));


	}

	void setIdioma(String idioma) {
		I18N.setLocale(new Locale(idioma));
	}

	@FXML
	void pulsarTecla(KeyEvent event) {
		switch (event.getCode()) {
		case DIGIT0: case NUMPAD0: insertarNumero("0"); break;
		case DIGIT1: case NUMPAD1: insertarNumero("1"); break;
		case DIGIT2: case NUMPAD2: insertarNumero("2"); break;
		case DIGIT3: case NUMPAD3: insertarNumero("3"); break;
		case DIGIT4: case NUMPAD4: insertarNumero("4"); break;
		case DIGIT5: case NUMPAD5: insertarNumero("5"); break;
		case DIGIT6: case NUMPAD6: insertarNumero("6"); break;
		case DIGIT7: case NUMPAD7: insertarNumero("7"); break;
		case DIGIT8: case NUMPAD8: insertarNumero("8"); break;
		case DIGIT9: case NUMPAD9: insertarNumero("9"); break;
		case BACK_SPACE: retroceder();break;
		case ENTER: calcular(); break; // Tecla "Enter" para el botón "igual"
		case ADD: asignarOperacion("+"); break; // Tecla "Add" para el botón de "mas"
		case SUBTRACT: asignarOperacion("-"); break; // Tecla "Subtract" para el botón de "menos"
		case MULTIPLY: asignarOperacion("*"); break; // Tecla "Multiply" para el botón de "por"
		case DIVIDE: asignarOperacion("/"); break; // Tecla "Divide" para el botón de "dividir"
		default: break;
		}
	}


	//Manipulacion de los numeros

	private void insertarNumero(String numero) {

		calculadora.insertarNumero(numero);
		mostrarInfo();
	}


	private void asignarOperacion(String operacion) {

		textFiel_Pantalla.setText(calculadora.asignarOperacion(operacion));
		textFiel_Pantalla.requestFocus();


	}

	private void retroceder() {

		calculadora.retroceder(); 
		mostrarInfo();

	}

	private void cambiarSigno() {
		calculadora.cambiarSigno();
		mostrarInfo();
	}

	//Operaciones Matematicas

	private void calcular() {
		try {
			calculadora.calcular();
			mostrarInfo();
		} catch (DivisionPorCeroException e) {
			textFiel_Pantalla.setText(e.getMessage());

		}
	}


	private void raiz() throws RaizNegativaException  {


		try {
			calculadora.setNumActual(String.valueOf(calculadora.raiz()));
			mostrarInfo();
		} catch (RaizNegativaException e) {
			textFiel_Pantalla.setText(e.getMessage());
		}

	}

	private void inversa() {
		try {
			calculadora.porcentaje();

		} catch (Exception e) {

			calculadora.clear();		
		}

	}

	private void porcentaje() {}

	//Otras opciones

	private void mostrarInfo() {
		textFiel_Pantalla.setText(calculadora.getNumActual());
	}



	private void mostrarError(String error) {}

	private void clear() {
		calculadora.clear();
		textFiel_Pantalla.setText("0");
	}

	private void clearError() {
		calculadora.clearError();
		mostrarInfo();
	}

	//Botones De Memoria

	private void memoryClear() {
		    	calculadora.memoryClear();
	}

	private void memoryStorage() {
		    	calculadora.memoryStorage();
	}

	private  void memoryRecall() {
		    	calculadora.memoryRecall();
		    	mostrarInfo();
	}

	private void memorySumar() {
		    	calculadora.memorySumar();
	}

	private void memoryRestar() {
		    	calculadora.memoryRestar();
	}

	//Opciones De Menu
	private void mostrarHistorial() {
		    	//calculadora.mostrarHistorial();
	}

	private void salir() {

		Stage stage = (Stage)menu_Salir.getParentPopup().getOwnerWindow(); 
		stage.close();
	}



	//Mostrar Otra Ventana

	private void mostrarVentanaAyuda(String rutaFXML, String titulo) {
		try{
			//Léeme el source del archivo que te digo fxml y te pongo el path
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
			Parent root = (Parent) fxmlLoader.load();
			//Creame un nuevo Stage (una nueva ventana vacía)
			Stage stage = new Stage();
			//Asignar al Stage la escena que anteriormente hemos leído y guardado en root
			stage.setTitle(titulo);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			//Mostrar el Stage (ventana)
			stage.show();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	private void mostrarAcercaDe(String rutaFXML, String titulo) {

		try{
			//Léeme el source del archivo que te digo fxml y te pongo el path
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
			Parent root = (Parent) fxmlLoader.load();
			//Creame un nuevo Stage (una nueva ventana vacía)
			Stage stage = new Stage();
			//Asignar al Stage la escena que anteriormente hemos leído y guardado en root
			stage.setTitle(titulo);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			//Mostrar el Stage (ventana)
			stage.show();


		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

	private void actualizarFecha() {

		// Obtener la fecha actual osea del dia
		LocalDate fechaActual = LocalDate.now();

		//Darle formato
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Actualizar el texto del Label con la fecha formateada
		labelMostrarFecha.setText(fechaActual.format(formatoFecha));
	}

}
