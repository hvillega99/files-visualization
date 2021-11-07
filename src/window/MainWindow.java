 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author CDHTN
 */
public class MainWindow {
    private BorderPane root;
    private TextField txtDirectory;
    private Pane pane;
    TreeMap<String, Pintura> colorMap;        
       
  
    
    public MainWindow() throws Exception{
        root = new BorderPane();
        createContent();
        colorMap=new TreeMap();
        colorMap=deserializar();
       
       /*colorMap.put("doc", new Pintura(Color.BLUE));
   colorMap.put("docx",new Pintura (Color.BLUE));
       colorMap.put("txt",new Pintura (Color.SKYBLUE));
       colorMap.put("pdf", new Pintura (Color.PINK));
       colorMap.put("pptx",new Pintura (Color.SALMON));
       colorMap.put("xlsx",new Pintura (Color.LIGHTGREEN));
       colorMap.put("mp4", new Pintura(Color.CADETBLUE));
       colorMap.put("mp3",new Pintura (Color.CYAN));
       colorMap.put("avi",new Pintura (Color.GOLD));
       colorMap.put("jpg", new Pintura(Color.DARKTURQUOISE));
       colorMap.put("jpeg",new Pintura (Color.STEELBLUE));
       colorMap.put("zip",new Pintura (Color.VIOLET));
       colorMap.put("rar",new Pintura (Color.CHOCOLATE));
       colorMap.put("exe",new Pintura (Color.DARKGOLDENROD));
       colorMap.put("iso",new Pintura (Color.GREY));
       colorMap.put("py", new Pintura(Color.GREENYELLOW));
       colorMap.put("jar",new Pintura (Color.CRIMSON));*/
             
    }
    
    private void createContent() throws Exception{
       
        createMainContent();
        
    }
    
  
    private void createMainContent(){
       
        Label lblTittle= new Label("Tree Maping");
        lblTittle.setId("titulo");
        HBox hbox = new HBox(5);
        hbox.getChildren().add(lblTittle);
        hbox.setAlignment(Pos.CENTER);
        
        //.................................
         VBox vBoxDirectory= new VBox(5);
         
         Label lblDirectory = new Label("Directorio:");
         HBox hboxDirectory= new HBox(2);
         Button btnDirectory= new Button("Ruta");
         
         txtDirectory = new TextField(readRuta());
         txtDirectory.setPrefWidth(340);
         hboxDirectory.getChildren().addAll(btnDirectory, txtDirectory);
         hboxDirectory.setAlignment(Pos.CENTER);
         Button btnDisplay = new Button("Visualizar");
         Button btnSetting = new Button("Configuración");
         
         HBox hBoxButtons = new HBox(2);
         hBoxButtons.getChildren().addAll(btnDisplay,btnSetting);
         
         vBoxDirectory.getChildren().addAll(lblDirectory, hboxDirectory, hBoxButtons);
         vBoxDirectory.setAlignment(Pos.CENTER);
         hBoxButtons.setAlignment(Pos.CENTER);
         btnDirectory.setOnAction(e-> {
            
                 DirectoryChooser directoryChooser = new DirectoryChooser();
                 directoryChooser.setTitle("Elegir archivo");
                 directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                
                 File dir = directoryChooser.showDialog(new Stage());
                 //System.out.println(dir.getAbsolutePath());
                 
                 if (dir != null) {
                     writeRuta(dir.getAbsolutePath());
                     txtDirectory.setText(dir.getAbsolutePath());
                 } else {
                     txtDirectory.setText(null);
                 }
             
         });
         
         btnDisplay.setOnAction(e->{
             
            String ruta=txtDirectory.getText();
            
            if(ruta==null || ruta.equals("")){
                 Alert dialog = new Alert(AlertType.ERROR);
                 dialog.setTitle("Error");
                 dialog.setHeaderText("Ruta no válida");
                 dialog.setContentText("Ingrese una ruta válida e intente de nuevo");
                 dialog.initStyle(StageStyle.UTILITY);
                 dialog.showAndWait();
            }else{
                
                
               MappingWindow mw = new MappingWindow(ruta,colorMap);
               Scene escena = new Scene(mw.getRoot(),1360,700);
          
              Stage newWindow = new Stage();
              newWindow.setTitle("Tree maping");
              newWindow.setScene(escena);
              newWindow.show();
              newWindow.setResizable(false);

              newWindow.setMaxWidth(1365);
              newWindow.setMaxHeight(729);
                
            }
            
            
        
        
         });
         
         btnSetting.setOnAction(e->{
           SettingWindow sw = new SettingWindow(colorMap);
           Scene escena2 = new Scene(sw.getRoot(),500,180);
          
            Stage newWindow2 = new Stage();
            newWindow2.setTitle("Configuración");
            newWindow2.setScene(escena2);
            newWindow2.show();
            newWindow2.setResizable(false);
            newWindow2.setWidth(500);
            newWindow2.setHeight(400);
            
         
         
         });
         
        //..............................................
        VBox vBoxRoot= new VBox(15);
        vBoxRoot.getChildren().addAll(hbox,vBoxDirectory);
        root.setCenter(vBoxRoot);
   
    }

    private void writeRuta(String ruta){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
           fichero = new FileWriter("ruta.txt");
           pw = new PrintWriter(fichero);
           pw.println(ruta);
            
              } catch (Exception e) {
                e.printStackTrace();
              } finally {
                try {
                   if (null != fichero)
                   fichero.close();
                } catch (Exception e2) {
                  e2.printStackTrace();
                }
              }
    }
    
    private String readRuta(){
       String ruta ="";
        
        try {
          Scanner input = new Scanner(new File("ruta.txt"));
          ruta = input.nextLine();      
          input.close();
              
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return ruta;
    }
    

    public BorderPane getRoot() {
        return root;
    }
    
    private TreeMap<String,Pintura> deserializar() throws ClassNotFoundException{
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        TreeMap<String,Pintura> mapa = null;
        try {
            fis = new FileInputStream("colorMap.dat");
            entrada = new ObjectInputStream(fis);
            mapa = (TreeMap<String,Pintura>) entrada.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        
       return mapa;
    }
    
    
    
}
