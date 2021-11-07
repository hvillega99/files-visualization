/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import java.io.File;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import file.Archivo;
import tree.Tree;
import static tree.Tree.build;

/**
 *
 * @author CDHTN
 */
public class MappingWindow {
    private String ruta;
    private FlowPane root;
    private TreeMap<String,Pintura> colorMap;
    
    
    
    public MappingWindow(String ruta, TreeMap<String,Pintura> colorMap){
        root = new FlowPane();
        this.ruta = ruta;
        this.colorMap=colorMap;
        createContent();
        
    }
    
    private void createContent(){
        Pane pane = new Pane();
        
        File fichero=new File(ruta);
        Archivo ar=new Archivo(fichero);
        Tree<Archivo> tree=new Tree(ar);
        build(tree);
           
        try {
          buildRectangules(pane,tree,1360,700,1,0,0);
          root.getChildren().add(pane);
        } catch (Exception ex) {
          Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private void buildRectangules(Pane pane,Tree<Archivo> tree,double ancho,double largo,int direccion,double coordenadaX,double coordenadaY) throws Exception{
        double total=tree.getRoot().getContent().getSize();
        if(direccion==1){
            for (int i = 0; i < tree.getRoot().getList().size(); i++) {
                double size=tree.getRoot().getList().get(i).getRoot().getContent().getSize();
                double ancho1=(size/total)*ancho;
                if(tree.getRoot().getList().get(i).getRoot().getContent().getArchivo().isDirectory()){
                    buildRectangules(pane,tree.getRoot().getList().get(i),ancho1,largo,2,coordenadaX,coordenadaY);
                    coordenadaX=coordenadaX+ancho1;
                }
                else{
                    String pal;
                    Rectangle rectangle = new Rectangle();
                    rectangle.setWidth(ancho1); //ancho 
                    rectangle.setHeight(largo); //largo
                    String fileName = tree.getRoot().getContent().getArchivo().getName();
                    fileName= tree.getRoot().getList().get(i).getRoot().getContent().getArchivo().getName();
                    String[] list = fileName.split("\\.");
                    rectangle.setFill(selectColor(list[list.length-1]));
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setX(coordenadaX);
                    rectangle.setY(coordenadaY);
                    Tooltip t=new Tooltip("Nombre del archivo: "+fileName+"\n"+"Peso en megabytes: "+size/(1024*1024));
                    Tooltip.install(rectangle, t);
                    
                    
                    
                    coordenadaX=coordenadaX+ancho1;
                    pane.getChildren().add(rectangle);
                    pal=fileName;
                    
                   rectangle.setOnMouseClicked(event -> {
                       
                       Alert alert=new Alert(AlertType.INFORMATION);
                       alert.setTitle("Informacion");
                       alert.setContentText("Nombre del archivo: "+pal+"\n"+"Peso en megabytes: "+size/(1024*1024));
                       alert.setHeaderText(null);
                       alert.showAndWait();

                   }); 
                }
            }
        }
        else{
            for (int i = 0; i < tree.getRoot().getList().size(); i++) {
                double size=tree.getRoot().getList().get(i).getRoot().getContent().getSize();
                double largo1=(size/total)*largo;
                if(tree.getRoot().getList().get(i).getRoot().getContent().getArchivo().isDirectory()){
                    buildRectangules(pane,tree.getRoot().getList().get(i),ancho,largo1,1,coordenadaX,coordenadaY);
                    coordenadaY=coordenadaY+largo1;
                }
                else{
                    String pal;
                    Rectangle rectangle = new Rectangle();
                    rectangle.setWidth(ancho); //ancho 
                    rectangle.setHeight(largo1); //largo
                     String fileName = tree.getRoot().getContent().getArchivo().getName();
                    fileName= tree.getRoot().getList().get(i).getRoot().getContent().getArchivo().getName();
                    String[] list = fileName.split("\\.");
                    rectangle.setFill(selectColor(list[list.length-1]));
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setX(coordenadaX);
                    rectangle.setY(coordenadaY);
                    
                    Tooltip t=new Tooltip("Nombre del archivo: "+fileName+"\n"+"Peso en megabytes: "+size/(1024*1024));
                    Tooltip.install(rectangle, t);
                    
                    coordenadaY=coordenadaY+largo1;
                    pane.getChildren().add(rectangle);
                    
                    pal=fileName;
                   
                    rectangle.setOnMouseClicked(event -> {
                        
                        
                        
                       Alert alert=new Alert(AlertType.INFORMATION);
                       alert.setTitle("Informacion");
                       alert.setContentText("Nombre del archivo: "+pal+"\n"+"Peso en megabytes: "+size/(1024*1024));
                       alert.setHeaderText(null);
                       alert.showAndWait();
                          
          
                   });
                }
            }
        }
        
    }
    
  
    private Color selectColor(String extension){
    
       if(colorMap.containsKey(extension)){
           double red = colorMap.get(extension).getRed();
           double green = colorMap.get(extension).getGreen();
           double blue = colorMap.get(extension).getBlue();
           return new Color(red,green,blue,1);
       }else{
           return Color.ALICEBLUE;
       }
          
   }

    public FlowPane getRoot() {
        return root;
    }
    
    
}
