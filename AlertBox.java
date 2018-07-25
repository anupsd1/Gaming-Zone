import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	public static void display(String title,String msg)
	{
		Stage window=new Stage();
		
		//Block user interaction with other windows till this window is closed
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label=new Label();
		label.setText(msg);
		Button closeBtn=new Button("Close the window");
		closeBtn.setOnAction(e->window.close());
		
		VBox layout=new VBox(20);
		layout.getChildren().addAll(label,closeBtn);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene=new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		
		
	}

}
