import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class newLogin extends Application{
	
	private GameMenu gameMenu;
	Scene scene2,scene3,scene4;
	Label lusername;
	static String userid=null;
	static String highscore=null;
	TextField t2;
	Rectangle btnRect=new Rectangle(200,100);
	MenuButton btnMario=new MenuButton("MARIO");
	Button log=new Button("LOGIN!");
	int res;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane rootPane=new StackPane();
		//rootPane.setPrefSize(800, 600);
		
		InputStream is=Files.newInputStream(Paths.get("C:\\Java\\Game Development2\\images\\mainMenuImg.jpg"));
		Image img=new Image(is);
		is.close();
		
		ImageView imgView=new ImageView(img);
		//rootPane.getChildren().addAll(imgView);
			
		//HBox hLayout=new HBox(200);
		Scene scene=new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setFullScreen(true);
			
		//primaryStage.setFullScreenExitKeyCombination(KeyCombination.ModifierValue.valueOf("escape"));
		lusername =new Label("Enter Username");
		//Text lusername=new Text();
		lusername.setText("Enter username");
		//lusername.setFont(lusername.getFont().font("Bold",50));
		lusername.setFont(Font.font(null,FontWeight.BOLD,50));
		lusername.setTranslateX(-150);
		//lusername.setTranslateY(100);
		lusername.setTextFill(Color.LIGHTGREEN);
		
		//Text text=new Text(20,0,"");
		TextArea t=new TextArea();
		t2=new TextField();
		t2.setMaxWidth(500);
		//Button b2=new Button("INVALID USERNAME/PASSWORD. PLEASE REGISTER IF NEW USER");
		/*text.setText("Enter username");
		text.setFont(text.getFont().font(20));
		text.setTextFill(Color.GREEN);*/
		//text.setFill(Color.WHITE);
		t2.setTranslateX(300);
		//t2.setTranslateY(70);
		
		Label lpassword=new Label("Enter password");
		lpassword.setTranslateX(-150);
		lpassword.setTranslateY(70);
		lpassword.setFont(Font.font(null,FontWeight.BOLD,50));
		//lpassword.setFont(lpassword.getFont().font("BOLD",50));
		lpassword.setTextFill(Color.LIGHTGREEN);
		
		PasswordField p=new PasswordField();
		p.setMaxWidth(500);
		p.setTranslateX(300);
		p.setTranslateY(70);
		
		Text b1=new Text("LOGIN");
		b1.setFill(Color.AZURE);
		//b1.setTranslateX(70);
		//b1.setTranslateY(140);
		
		b1.getFont();
		//b1.setTextAlignment(Pos.BOTTOM_CENTER);
		//b1.setFont(b1.getFont().font(50));
		b1.setFont(Font.font(50));
		Rectangle r=new Rectangle(200,100);
		r.setOpacity(0.5);
		r.setFill(Color.ANTIQUEWHITE);
		r.setEffect(new GaussianBlur(0.5));
		r.setTranslateY(200);
		b1.setTranslateY(200);
		r.setOnMouseEntered(e->{
			r.setFill(Color.DARKGREY);
		});
		r.setOnMouseExited(e->{
			r.setFill(Color.ANTIQUEWHITE);
		});
		/*r.setOnMouseClicked(e->{
			GameMenuDemo gameMenu=new GameMenuDemo();
			Scene scene2=gameMenu.scene;
			primaryStage.setScene(scene2);
			primaryStage.show();
			
			
		});*/
		//hLayout.getChildren().addAll(lusername,t2);

		Button regButton=new Button("REGISTRATION");
		regButton.setTranslateY(400);
		rootPane.getChildren().addAll(imgView,lusername,t2,b1,lpassword,p,r,regButton);
		
		regButton.setOnAction(e->{
			
			try {
				showRegistration();
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			primaryStage.setScene(scene3);
			primaryStage.setFullScreen(true);
			
		});
		
		log.setOnMouseClicked(event->{
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setFullScreen(true);
			
		});
		
		btnRect.setOnMouseClicked(event->{
			//Class.forName(arg0)
			/*try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:/java//Game Development2//Database1.mdb");
				PreparedStatement pst=con.prepareStatement("insert into Database values(?,?)");
				pst.setString(1, uname);
				pst.setString(2, pwd);
				
				res=pst.executeUpdate();
				if(res>0)
				{
					primaryStage.setScene(scene);
					primaryStage.show();
					primaryStage.setFullScreen(true);
					
				}
				pst.close();
				con.close();
				//10st.executeUpdate("insert into Database values(?,?,?);");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			*/
		});
		
		///FROM GAMEMENUDEMO!!!
		
		StackPane root=new StackPane();
		//root.setPrefSize(800, 600);
		
		InputStream is1=Files.newInputStream(Paths.get("C:\\Java\\Game Development2\\images\\mainMenuImg.jpg"));
		Image img1=new Image(is1);
		is1.close();
		
		ImageView imgView1=new ImageView(img1);
		//imgView.setFitWidth(800);
		//imgView.setFitHeight(600);
		gameMenu=new GameMenu();


		root.getChildren().addAll(imgView1,gameMenu);
		
		scene2=new Scene(root);		
		
		
		
		
		/*primaryStage.setScene(scene2);
		primaryStage.show();
		primaryStage.setFullScreen(true);*/
		
		r.setOnMouseClicked(e->{
			try 
			{
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con=DriverManager.getConnection("jdbc:ucanaccess://C://Program Files//Java//jdk1.8.0_131//bin//GameDB.mdb");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from User");
				while (rs.next())
				{
					String s1=rs.getString(1);
					String s2=rs.getString(2);

					System.out.print(s1+"\t");
					System.out.println(s2);
					String uname=t2.getText();
					String pwd=p.getText();
					System.out.println("HEYY!!!"+s1);
					System.out.println("uname="+uname);
					System.out.println("pwd="+pwd);
					//rs.close();
					//String pad=passwordField.getText();
					if(s1.equals(uname) && s2.equals(pwd))
					{
						userid=uname;
						System.out.println("USERID ="+userid);
						//HighScores obj=new HighScores(userid,highscore);
						System.out.println("Before ps");
						//PreparedStatement ps2=con.prepareStatement("select * from Game Mario where Username=?");
						//st=con.createStatement();
						PreparedStatement st2=con.prepareStatement("select * from `Game Mario` where Username='"+uname+"'");
						//ResultSet rs2=st2.executeQuery("select * from `Game Mario` where Username='"+uname+"'");
						ResultSet rs2=st2.executeQuery();
						//ps2.setString(1, uname);
						System.out.println("After ps.");
						//highscore=null;
						while(rs2.next()) 
						{
							//userid="Anup"
							highscore=rs2.getString(4);
						}
						//ResultSet rs2=ps2.executeQuery();
						//String highscore=rs2.getString(2);
						//HighScores obj=new HighScores(userid,highscore);

						//new GameManager().setId(userid, highscore);
						createfile g21= new createfile();
						g21.openFile();
						g21.addRecords(highscore);
						g21.closeFile();

						
						System.out.println("Highscore="+highscore);
						//primaryStage.close();
						primaryStage.setScene(scene2);
						primaryStage.show();
						//	primarySta	e.show();
						primaryStage.setFullScreen(true);
						//rs.close();
						rs2.close();
					}
					
					/*else
					{
						Button b2=new Button("INVALID USERNAME/PASSWORD");
						b2.setTranslateY(400);
						b2.setFont(b2.getFont().font(80));
						t2.setText("");
						p.setText("");
						rootPane.getChildren().add(b2);
					}*/
				}
				Button b2=new Button("INVALID USERNAME/PASSWORD. PLEASE REGISTER IF NEW USER");
				b2.setTranslateY(-500);
				b2.setFont(b2.getFont().font(50));
				t2.setText("");
				p.setText("");
				long ti=System.currentTimeMillis();
				int i=0;
				rootPane.getChildren().add(b2);
				con.close();
			}catch(Exception ex) {}
			
		});
		
		
		/*btnMario.setOnMouseClicked(event ->{
			marioMenu();
			primaryStage.setScene(scene4);
			primaryStage.setFullScreen(true);
			try
			{
				//Runtime.getRuntime().exec("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\mario6.exe", null, new File("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\"));
			}
			catch(Exception ex) {}


		});*/
	}
	
	private class GameMenu extends Parent
	{
		public GameMenu()
		{
			HBox menu0=new HBox(20);
			VBox menu1=new VBox(20);
			
			menu0.setTranslateX(200);
			menu0.setTranslateY(100);
			
			menu1.setTranslateX(500);
			menu1.setTranslateX(500);
			
			final int offset=400;
			
			//menu0.setTranslateX(offset);
			
			//MenuButton btnResume=new MenuButton("MARIO");
			btnMario.setTranslateX(-50);
			//btnResume.setOnMouseClicked(event ->{
			btnMario.setOnMouseClicked(event->{
							//HighScores obj=new HighScores(userid,highscore);
							//marioMenu();
							String line;
							try
							{
								
								Process p=Runtime.getRuntime().exec("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\GameTest32.exe", null, new File("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\"));
								BufferedReader input=new BufferedReader(new InputStreamReader(p.getInputStream()));
								OutputStreamWriter oStream=new OutputStreamWriter(p.getOutputStream());
								while((line=input.readLine()) != null) {}
								input.close();
								
								//System.out.println("Running");
								/*File f=new File("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\tests.jar");
								URLClassLoader urlCl=new URLClassLoader(new URL[] {f.toURL()},System.class.getClassLoader());
								Class log4jClass=urlCl.loadClass("org.apache.log4j.Logger");
								*/
								
								//System.out.println("Stopped");
								ReadFile r=new ReadFile();
								String s2=null;
								if(r.openFile("chinese2.txt"))
								{
									s2=r.readFile();
								
								
									String s1=highscore;
									int num1=Integer.parseInt(s1);
									int num2=Integer.parseInt(s2);
									int finalscore=num1;
									if(num1<num2)
									{
										finalscore=num2;
									}
									Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
									Connection con=DriverManager.getConnection("jdbc:ucanaccess://C://Program Files//Java//jdk1.8.0_131//bin//GameDB.mdb");
									PreparedStatement pst=con.prepareStatement("update `Game Mario` set Score='"+finalscore+"' where Username='"+userid+"'");
									int x=pst.executeUpdate();
									if(x>0) {}
									pst.close();
									con.close();
									
									
								}
								DeleteFile nu=new DeleteFile();
								nu.del("chinese2.txt");
								
							}
							catch(Exception ex) {}
							//scoreSet();
							
							//new MenuTest().run();
			});
			
			MenuButton btnOption=new MenuButton("FLAPPY BIRD");
			btnOption.setTranslateX(50);
			btnOption.setOnMouseClicked(event->{
				try {
					Runtime.getRuntime().exec("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\tests3.exe", null, new File("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			MenuButton btnExit=new MenuButton("BRICK BREAKER");
			btnExit.setTranslateX(100);
			
			btnExit.setOnMouseClicked(event->{
				try {
					Runtime.getRuntime().exec("C:\\\\Program Files\\\\java\\\\jdk1.8.0_131\\\\bin\\\\tests2.exe", null, new File("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			
			menu0.getChildren().addAll(btnMario,btnOption,btnExit);
			
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
			
			
			Rectangle bg=new Rectangle(500,100);
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
	
	
	
	private class loginDetails extends Parent
	{
		Text text;
		
		public loginDetails(String name)
		{
			text.setText(name);
			
			
		}
		
		
		
	}
	
	
	public void showRegistration() throws IOException
	{
		StackPane bg=new StackPane();
		
		VBox rCenter=new VBox();
		HBox rMenu1=new HBox();
		//rMenu1.setPrefSize(500, 100);
		
		
		Label rName=new Label("Enter Name");
		rName.setText("Enter Name");
		rName.setFont(Font.font(null,FontWeight.BOLD,50));
		rName.setTextFill(Color.LIGHTGREEN);
		//rName.setTranslateX(-30);
		//rName.setTranslateY(50);
		
		TextField nameText=new TextField();
		nameText.setMaxWidth(500);
		nameText.setTranslateX(50);
		nameText.setTranslateY(20);
		
		rMenu1.getChildren().addAll(rName,nameText);
		rMenu1.setTranslateX(300);
		rMenu1.setTranslateY(300);
		
		HBox rMenu2=new HBox();
		//rMenu2.setPrefSize(500, 100);
		
		
		Label rUser=new Label("Enter Username");
		rUser.setTextFill(Color.LIGHTGREEN);
		rUser.setFont(Font.font(null,FontWeight.BOLD,50));
		//re.setTranslateX(-50);
		//rAge.setTranslateY(50);
		
		TextField userText=new TextField();
		userText.setMaxWidth(500);
		userText.setTranslateX(100);
		userText.setTranslateY(20);
		
		
		rMenu2.getChildren().addAll(rUser,userText);
		rMenu2.setTranslateX(300);
		rMenu2.setTranslateY(300);

		HBox rMenu3=new HBox();
		//rMenu3.setPrefSize(500, 100);
		
		Label rPass=new Label("Enter Password");
		rPass.setFont(Font.font(null,FontWeight.BOLD,50));
		rPass.setTextFill(Color.LIGHTGREEN);
		//rEmail.setTranslateX(-50);
		//rEmail.setTranslateY(50);
		
		TextField passText=new TextField();
		passText.setMaxWidth(500);
		passText.setTranslateX(70);
		passText.setTranslateY(20);
		
		rMenu3.getChildren().addAll(rPass,passText);
		rMenu3.setTranslateX(300);
		rMenu3.setTranslateY(300);

		//btnRect=new Rectangle(200,100);
		btnRect.setTranslateX(500);
		btnRect.setTranslateY(300);
		//btnRect.setOpacity(0.4);
		btnRect.setOpacity(0.5);
		btnRect.setFill(Color.ANTIQUEWHITE);
		btnRect.setEffect(new GaussianBlur(0.5));
		
		btnRect.setOnMouseEntered(e->{
			btnRect.setFill(Color.DARKGREY);
		});
		btnRect.setOnMouseExited(e->{
			btnRect.setFill(Color.ANTIQUEWHITE);
		});
		
	
		/*btnRect.setOnMouseEntered(event->{
			
			});
		*/
		Button submitBtn=new Button("SUBMIT");
		submitBtn.setFont(Font.font(null,FontWeight.BOLD,50));
		submitBtn.setTranslateX(500);
		submitBtn.setTranslateY(380);
		//submitBtn.setDisable(true);
		InputStream is2;
			is2 = Files.newInputStream(Paths.get("C:\\Java\\Game Development2\\images\\mainMenuImg.jpg"));
			Image img2=new Image(is2);
			is2.close();
			ImageView imgView2=new ImageView(img2);
		
		//log=new Button("LOGIN!");
		log.setFont(Font.font(null,FontWeight.BOLD,50));
		log.setTranslateX(800);
		log.setTranslateY(270);
		
		rCenter.getChildren().addAll(rMenu1,rMenu2,rMenu3,submitBtn,log);
		bg.getChildren().addAll(imgView2,rCenter);
		
		submitBtn.setOnMouseClicked(event->{
			try {
				System.out.println("Submit is clicked");
				String uname=userText.getText();
				String pwd=passText.getText();
				
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con=DriverManager.getConnection("jdbc:ucanaccess://C://Program Files//Java//jdk1.8.0_131//bin//GameDB.mdb");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				//st.executeQuery("Select * from User");
				ResultSet rs=st.executeQuery("select * from User");
				//int x=rs.getInt(1);
				long y=0;
				/*while(rs.next())
				{
					//int x=rs.getString(1);
					int x=
					y=x++;
				
				}*/
				
				rs.last();
				//long x=rs.getInt("ID");
				String x=rs.getString("ID");
				int n=Integer.parseInt(x);
				
				y=n+1;
				
				
				
				PreparedStatement pst=con.prepareStatement("insert into User values (?,?,?)");
				//pst.
				//pst.setInt(1, Integer.parseInt(x));
				pst.setString(1, uname);
				pst.setString(2, pwd);
				pst.setString(3, String.valueOf(y));
				
				res=pst.executeUpdate();
				if(res>0)
				{
					System.out.println("RECORD INSERTED");
					submitBtn.setDisable(true);
				}
				
				/*if(res>0)
				{
					primaryStage.setScene(scene);
					primaryStage.show();
					primaryStage.setFullScreen(true);
					
				}*/
				pst.close();
				con.close();
				
				//10st.executeUpdate("insert into Database values(?,?,?);");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		});
		
		scene3=new Scene(bg);
		
		
	}
	
	
	public void marioMenu() {
		StackPane newPane=new StackPane();
		HBox hor=new HBox();
		hor.setTranslateY(500);
		
		Text resume=new Text("RESUME");
		resume.setFont(Font.font(null,FontWeight.BOLD,50));
		resume.setTranslateX(500);
		
		Text newgame=new Text("NEW GAME");			
		newgame.setFont(Font.font(null,FontWeight.BOLD,50));
		newgame.setTranslateX(1000);
		hor.getChildren().addAll(resume,newgame);
		//newPane.getChildren().addAll(hor);
		scene4=new Scene(hor);
	}
	/*public void scoreSet()
	{
		System.out.println("IN SCORESET!!");
		//newLogin nl=new newLogin();
		
		//String userid=newLogin.userid;
		//String userid=nl.userid;
		//String sscore=newLogin.highscore;
		//String sscore=nl.highscore;
		//int hscore=Integer.valueOf(sscore);
		int count=GameManager.count;
		int hscore=Integer.valueOf(highscore);
		if(hscore<count)
		{
			System.out.println("In if!!");
			try
			{
				System.out.println("Loading drivers and creating connection");
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:/java//Game Development2//GameDB.mdb");
				System.out.println("Before preparestament");
				PreparedStatement pst=con.prepareStatement("update `Game Mario` set Score='"+count+"' where Username='"+userid+"'");
				int x=pst.executeUpdate();
				if(x>0)
					System.out.println("Updated");
				pst.close();
				con.close();
			}catch(Exception ex) {}
		}
	}
	
	public static void scoreSet()
	{
		//int count=GameManager.checkScore();
		try
		{
			System.out.println("Loading drivers and creating connection");
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:/java//Game Development2//GameDB.mdb");
			System.out.println("Before preparestament");
			PreparedStatement pst=con.prepareStatement("update `Game Mario` set Score='"+count+"' where Username='"+userid+"'");
			int x=pst.executeUpdate();
			if(x>0)
				System.out.println("Updated");
			pst.close();
			con.close();
		}catch(Exception ex) {}

	}*/
	
	public static void main(String args[])
	{
		launch(args);
		
	}
}
