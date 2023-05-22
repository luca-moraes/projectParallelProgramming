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
    public static void main(String[] args) throws IOException, InvalidNumberException, InterruptedException {
        // lendo o arquivo e pegando a matriz
        String filePath = "/home/lmoraes/Desktop/projectParallelProgramming/ConwaysGameOfLife/src/files/teste.txt";
        int gens = 10000;
        
        if(args.length == 2){
            gens = Integer.parseInt(args[0]);
            filePath = args[1];
        }
        
        FileManager fileManager = new FileManager(filePath);

        Matrix baseMatrix = fileManager.readFile(gens);
        
        Matrix matrixSeq = baseMatrix;
        
        //-----------------------------------------------------------------------------------------
        
        //configurando a matrix de 2 threads
        Matrix matrix2 = baseMatrix;
        int halfMatrix2 = (matrix2.board.length/2);
        int matrix2Size = matrix2.board.length;
                
        Runner doubleRun1 = new Runner(matrix2, gens, 0, halfMatrix2, 0, matrix2Size);
        Runner doubleRun2 = new Runner(matrix2, gens, halfMatrix2, matrix2Size, 0, matrix2Size);
        Thread doubleThread1 = new Thread(doubleRun1);
        Thread doubleThread2 = new Thread(doubleRun2);
        
        //-----------------------------------------------------------------------------------------


        //configurando a matrix de 4 threads
        Matrix matrix4 = baseMatrix;
        int halfMatrix4 = (matrix4.board.length/2);
        int matrix4Size = matrix4.board.length;
        
        Runner quadRun1 = new Runner(matrix4, gens, 0, halfMatrix4, 0, halfMatrix4);
        Runner quadRun2 = new Runner(matrix4, gens, halfMatrix4, matrix4Size, 0, halfMatrix4);
        Runner quadRun3 = new Runner(matrix4, gens, 0, halfMatrix4, halfMatrix4, matrix4Size);
        Runner quadRun4 = new Runner(matrix4, gens, halfMatrix4, matrix4Size, halfMatrix4, matrix4Size);
        
        Thread quadThread1 = new Thread(quadRun1);
        Thread quadThread2 = new Thread(quadRun2);
        Thread quadThread3 = new Thread(quadRun3);
        Thread quadThread4 = new Thread(quadRun4);
        
        //-----------------------------------------------------------------------------------------

        //tempo sequencial
        long seqTimeInit = System.currentTimeMillis();
        
        int[][] finalBoard = matrixSeq.execute(gens);
        
        long seqTimeEnd = System.currentTimeMillis();
        
        long seqFinalTime = seqTimeEnd - seqTimeInit;
        System.out.println(seqFinalTime);
        
        //-----------------------------------------------------------------------------------------
        
        //double threads
        long doubleTimeInit = System.currentTimeMillis();
        
        doubleThread1.run();
        doubleThread2.run();
        
        doubleThread1.join();
        doubleThread2.join();
        
        long doubleTimeEnd = System.currentTimeMillis();
        
        
        long doubleFinalTime = doubleTimeEnd - doubleTimeInit;
        System.out.println(doubleFinalTime);
        
        //-----------------------------------------------------------------------------------------
        
        //quad threads
        long quadTimeInit = System.currentTimeMillis();
        
        quadThread1.run();
        quadThread2.run();
        quadThread3.run();
        quadThread4.run();
        
        quadThread1.join();
        quadThread2.join();
        quadThread3.join();
        quadThread4.join();
        
        long quadTimeEnd = System.currentTimeMillis();
        
        
        long quadFinalTime = quadTimeEnd - quadTimeInit;
        System.out.println(quadFinalTime);
        
        //-----------------------------------------------------------------------------------------

        float t1 = Float.valueOf(seqFinalTime) / 1000;
        float t2 = Float.valueOf(doubleFinalTime) / 1000;
        float t4 = Float.valueOf(quadFinalTime) / 1000;
        
        fileManager.escritor(finalBoard, matrix2.board, matrix4.board);
        fileManager.escritorTable(finalBoard.length, gens, t1, t2, t4);
    }
    
}
