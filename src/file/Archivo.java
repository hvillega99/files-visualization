/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.File;

/**
 *
 * @author User
 */
public class Archivo {
    private File archivo;
    private long size;

    public Archivo(File archivo) {
        this.archivo = archivo;
        this.size=0;
    }

    public Archivo(File archivo, long size) {
        this.archivo = archivo;
        this.size = size;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
    
    

    
    
    
    
    
}
