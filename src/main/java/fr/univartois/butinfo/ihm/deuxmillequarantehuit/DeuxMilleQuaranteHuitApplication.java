package fr.univartois.butinfo.ihm.deuxmillequarantehuit;

import fr.univartois.butinfo.ihm.deuxmillequarantehuit.controller.DeuxMilleQuaranteHuitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class DeuxMilleQuaranteHuitApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/deux-mille-quarante-huit-view.fxml"));
        Parent viewContent = fxmlLoader.load();

		Scene scene = new Scene(viewContent, (500), 600);
        DeuxMilleQuaranteHuitController controller = fxmlLoader.getController();
        scene.setOnKeyPressed(controller::handleKeyPress);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.setTitle("2048");

        stage.show();
   }

    public static void main(String[] args) {
        launch();
    }

}
