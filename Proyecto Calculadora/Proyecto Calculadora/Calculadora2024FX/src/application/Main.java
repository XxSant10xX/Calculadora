package application;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilidades.I18N;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Indicar el idioma
			Locale locale = new Locale("es");
			ResourceBundle bundle = ResourceBundle.getBundle("strings", locale);

			
			// Cargar la ventana
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vista/Calculadora.fxml"), bundle);

			// Cargar la Scene
			Scene scene = new Scene(root);

			// Asignar hoja de estilos 
			scene.getStylesheets().add("/vista/css/estilos.css");

			// Asignar propiedades al Stage
			//primaryStage.setTitle("Calculadora Uwu");
			primaryStage.titleProperty().bind(I18N.createStringBinding("form.titulo"));

			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.DECORATED);
			
			//Asignar icono de la aplicacion
			primaryStage.getIcons().add(new Image(getClass().getResource("/vista/img/icon.png").toExternalForm()));


			// Asignar la scene y mostrar
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}