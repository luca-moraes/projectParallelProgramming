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
    public String outputPath = "/home/lmoraes/Desktop/projectParallelProgramming/ConwaysGameOfLife/src/files/";
    public String finalPath;
    public String finalPathDouble;
    public String finalPathQuad;

    
    public FileManager(String path){
        filePath = path;
        this.lines = new ArrayList<>();
    }
    
    public Matrix readFile(int gens) throws FileNotFoundException, IOException{
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
        
        this.createFile(size, gens);
        
        return matrix;
    }
    
    private void createFile(int size, int gens){
        this.finalPath = this.outputPath + String.format("saidaMatriz%dGens%dSeq.txt", size, gens);
        this.finalPathDouble = this.outputPath + String.format("saidaMatriz%dGens%dDouble.txt", size, gens);
        this.finalPathQuad = this.outputPath + String.format("saidaMatriz%dGens%dQuad.txt", size, gens);

        try {
            Path path = Paths.get(finalPath);
            Path pathDouble = Paths.get(finalPathDouble);
            Path pathQuad = Paths.get(finalPathQuad);

            Files.delete(path);
            Files.delete(pathDouble);
            Files.delete(pathQuad);

        }
        catch (IOException e) {}
        
        try {
            PrintWriter writer = new PrintWriter(finalPath, this.encoding);
            PrintWriter writerDouble = new PrintWriter(finalPathDouble, this.encoding);
            PrintWriter writerQuad = new PrintWriter(finalPathQuad, this.encoding);
            
            writer.println("");
            writerDouble.println("");
            writerQuad.println("");
            
            writer.close();
            writerDouble.close();
            writerQuad.close();

        }
        catch (IOException e) {}
    }
    
    public void escritor(int[][] lastGenSeq, int[][] lastGenDouble, int[][] lastGenQuad) throws IOException {
        buffWrite = new BufferedWriter(new FileWriter(this.finalPath, encoding));
        
        for(int i = 0; i < lastGenSeq.length; i++){
            for(int j = 0; j < lastGenSeq.length; j ++){
                buffWrite.append(Integer.toString(lastGenSeq[i][j])
                );
            }
            buffWrite.append("\n");
        }
        
        buffWrite.close();
        
        buffWrite = new BufferedWriter(new FileWriter(this.finalPathDouble, encoding));

        for(int i = 0; i < lastGenDouble.length; i++){
            for(int j = 0; j < lastGenDouble.length; j ++){
                buffWrite.append(Integer.toString(lastGenDouble[i][j])
                );
            }
            buffWrite.append("\n");
        }
        
        buffWrite.close();
        
        buffWrite = new BufferedWriter(new FileWriter(this.finalPathQuad, encoding));
        
        for(int i = 0; i < lastGenQuad.length; i++){
            for(int j = 0; j < lastGenQuad.length; j ++){
                buffWrite.append(Integer.toString(lastGenQuad[i][j])
                );
            }
            buffWrite.append("\n");
        }
        
        buffWrite.close();
    }
    
    public void escritorTable(int size, int gens, float time1, float time2, float time4 ) throws IOException {
        buffWrite = new BufferedWriter(
                new FileWriter((
                        this.outputPath
                        + String.format("resultadoMatriz%dGens%d.txt", size, gens)
                        ), encoding)
        );
        
        buffWrite.append(String.format("Tamanho da matriz: %dX%d\n", size, size));
        buffWrite.append(String.format("Número de Gerações: %d\n", gens));
        buffWrite.append("Quantidade de Threads e Tempo (segs): \n");
        buffWrite.append(String.format("sequencial -> %.3fs\n2 -> %.3fs\n4 -> %.3fs", time1,time2,time4));
        
        buffWrite.close();
    }
}
