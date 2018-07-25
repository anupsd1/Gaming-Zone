//import java.awt.Rectangle;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameMenuDemo  extends Application {

	private GameMenu gameMenu;
	protected Scene scene2;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane root=new Pane();
		root.setPrefSize(800, 600);
		
		InputStream is=Files.newInputStream(Paths.get("C:\\Java\\Game Development2\\images\\mainMenuImg.jpg"));
		Image img=new Image(is);
		is.close();
		
		ImageView imgView=new ImageView(img);
		//imgView.setFitWidth(800);
		//imgView.setFitHeight(600);
		gameMenu=new GameMenu();
		
		
		root.getChildren().addAll(imgView,gameMenu);
		
		
		scene2=new Scene(root);
		
		primaryStage.setScene(scene2);
		primaryStage.show();
		primaryStage.setFullScreen(true);
		
		
	}
	
	private class GameMenu extends Parent
	{
		public GameMenu()
		{
			VBox menu0=new VBox(10);
			VBox menu1=new VBox(10);
			
			menu0.setTranslateX(500);
			menu0.setTranslateY(100);
			
			menu1.setTranslateX(500);
			menu1.setTranslateX(500);
			
			final int offset=400;
			
			menu1.setTranslateX(offset);
			
			MenuButton btnResume=new MenuButton("MARIO");
			btnResume.setOnMouseClicked(event ->{
				/*try
				{
					Runtime.getRuntime().exec("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\mario6.exe", null, new File("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\"));
				}
				catch(Exception ex) {}
				*/
				new MenuTest().run();
				
			});
			
			MenuButton btnOption=new MenuButton("OPTIONS");
			btnOption.setOnMouseClicked(event->{});
			
			MenuButton btnExit=new MenuButton("EXIT");
			btnExit.setOnMouseClicked(event->{});
			
			
			menu0.getChildren().addAll(btnResume,btnOption,btnExit);
			
			/*Rectangle bg=new Rectangle(500,200);
			//bg.setTranslateX(500);
			//bg.setTranslateY(0);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);
			
			getChildren().addAll(bg,menu0);		*/
			getChildren().addAll(menu0);
			
			
		}
	}
	
	
	private static class MenuButton extends StackPane
	{
		private Text text;
		public  MenuButton(String name)
		{
			text=new Text(name);
			text.setFont(text.getFont().font(50));
			text.setFill(Color.GREEN);
			
			
			Rectangle bg=new Rectangle(250,300);
			bg.setOpacity(0.6);
			bg.setFill(Color.BLACK);
			bg.setEffect(new GaussianBlur(0.5));
			
			setAlignment(Pos.TOP_CENTER);
			setRotate(-0.5);
			getChildren().addAll(bg,text);
			
			setOnMouseEntered(event-> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});
			
			setOnMouseExited(event-> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.GREEN);
			});
			
			DropShadow drop=new DropShadow(50,Color.WHITE);
			drop.setInput(new Glow());
			
			setOnMousePressed(event-> setEffect(drop));
			setOnMouseReleased(event-> setEffect(null));
			
			
			
			
		}
	}
	
	public static void main(String args[])
	{
		launch(args);
		
	}

}
