/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorArchivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
public class ManejoArchivos {
    
    public  ArrayList<String> leerArchivo(String rutaArchivo) {
        
        ArrayList<String> contenido = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(rutaArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int cont = 0;
            ArrayList<Double> matrizi;
            while ((linea = br.readLine()) != null) {
                contenido.add(linea);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        return contenido;
    }
    
    public boolean guardarArchivo(ArrayList<String> lineas,String ruta) {
        File archivo = new File(ruta);//archivo que se sobreescribirá
        
        
        archivo.delete();
        try
        {
                if(archivo.createNewFile())
                {

                    FileWriter escritor = new FileWriter(archivo, false);
                    BufferedWriter bf = new BufferedWriter(escritor);
                    PrintWriter pw = new PrintWriter(bf, true);

                    for (int i = 0; i < lineas.size(); i++) {

                        escritor.append(lineas.get(i) + "\n");//agrega la linea(estudiante )al archivo.

                    }
                    escritor.close();
                    return true;
                }
                else
                {
                    System.out.println("[fallo] No se pudo crear el archivo");
                    return false;
                }
        }
        catch(Exception e)
        {
            System.out.println("[fallo] Ocurrio un error mientras se guardaba el archivo: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<String> obtenerArchivosDirectorio(String ruta)
    {
        
          ArrayList<String>archivos=new ArrayList<>();
            File f = new File(ruta);
            if (f.exists()){
                
                File[] ficheros = f.listFiles();
                for (int x = 0; x < ficheros.length; x++) {
                   // System.out.println(ficheros[x].getName());
                    archivos.add(ficheros[x].getName());
                }
                
                return archivos;
            }
            else {
                System.out.println("[fallo] El directorio : '" + ruta + "' no existe.");
                return null;
            }
    }
    
    public boolean eliminarArchivo(String ruta)
    {
        File archivo = new File(ruta);//archivo que se sobreescribirá
        if (archivo.exists())
        {
            if(archivo.delete())
            {
                System.out.println("[ok] Se elimino el archivo ' " + ruta+ "' correctamente.");
                return  true;
            }
            else
            {
                System.out.println("[fallo] Ocurrio un error al tratar de eliminar el arhivo ' " + ruta+" ' , ¿Tiene permisos para realizar esta accion?");
                 return  false;
            }
        }
        else
        {
            System.out.println("[fallo] No se encontro el archivo ' " + ruta+" ' , no se puede realizar la eliminacion.");
            return  false;
        }
    }
    
    
}
