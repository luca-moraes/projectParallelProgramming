/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conwaysgameoflife;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author eu
 */
public class FileManager {
    public Scanner scan;
    public BufferedWriter buffWrite;
    public String filePath;
    public List<String> lines;
    public Charset encoding = Charset.forName("UTF-8");
    public String outputFile = "/home/eu/Documentos/projectParallelProgramming/ConwaysGameOfLife/src/conwaysgameoflife/saida.txt";
    
    public FileManager(String path){
        filePath = path;
        this.lines = new ArrayList<>();
        this.createFile();
    }
    
    public Matrix readFile() throws FileNotFoundException, IOException{
        scan = new Scanner(new FileReader(this.filePath, encoding))
            .useDelimiter("\\n");
        
        int size = Integer.parseInt(scan.next());
        
        while (scan.hasNext()) {
            lines.add(scan.next());
        }
        
        int[][] array = new int[size][size];
        
        for(int i = 0; i < size; i++){
            String nums = lines.get(i);
            for(int j = 0; j < size; j++){
                array[i][j] = Character.getNumericValue(nums.charAt(j));
            }
        }
        
        scan.close();
        
        Matrix matrix = new Matrix(size, array);
        
        return matrix;
    }
    
    private void createFile(){
        try {
            Path path= Paths.get(this.outputFile);
            Files.delete(path);
        }
        catch (IOException e) {}
        
        try {
            PrintWriter writer = new PrintWriter(this.outputFile, this.encoding);
            writer.println("Output result: ");
            writer.close();
        }
        catch (IOException e) {}
    }
    
    public void escritor(int[][] lastGen) throws IOException {
        buffWrite = new BufferedWriter(new FileWriter(this.outputFile, encoding)); 
        
        for(int i = 0; i < lastGen.length; i++){
            for(int j = 0; j < lastGen.length; j ++){
                buffWrite.append(
                        Integer.toString(lastGen[i][j])
                );
            }
            buffWrite.append("\n");
        }
        
        buffWrite.close();
    }
}
