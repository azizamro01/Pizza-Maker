/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

/**
 *
 * @author lenovo
 */
public class FileLogger extends myLogger{
    File file;

    public FileLogger(File file) {
        this.file = file;
    }

  
    @Override
    public void log(String message) {
        FileWriter fw = null;
        try {
            fw=new FileWriter(this.file,true);
            fw.write(message+"\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                fw.close();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                
            }
        }
    }
    
    
}
