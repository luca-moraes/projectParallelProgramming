/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conwaysgameoflife;

import java.io.IOException;

/**
 *
 * @author eu
 */
public class ConwaysGameOfLife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidNumberException {
        // TODO code application logic here
        String filePath;
        int gens = 3;
        
        if(args.length == 2){
            gens = Integer.parseInt(args[0]);
            filePath = args[1];
        }
        
        FileManager fileManager = new FileManager("/home/eu/Documentos/projectParallelProgramming/ConwaysGameOfLife/src/conwaysgameoflife/teste.txt");

        Matrix matrix = fileManager.readFile();
        
        fileManager.escritor(matrix.execute(gens));
    }
    
}
