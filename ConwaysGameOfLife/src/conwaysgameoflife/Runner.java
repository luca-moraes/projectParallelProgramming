/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conwaysgameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eu
 */
public class Runner implements Runnable{
    public Matrix matrix;
    public int gens;
    
    public int initLine;
    public int endLine;
    public int initColumn;
    public int endColumn;

    public Runner(Matrix matrix, int gens, int initLine, int endLine, int initColumn, int endColumn) {
        this.matrix = matrix;
        this.gens = gens;
        this.initLine = initLine;
        this.endLine = endLine;
        this.initColumn = endLine;
        this.endColumn = endColumn;
    }

    @Override
    public void run() {
        while(gens > 0){
            try {
                matrix.updateBoard(
                        matrix.changeGenerationDistrict(initLine, endLine, initColumn, endColumn)
                        ,initLine, endLine, endLine, endColumn
                );
            } catch (InterruptedException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidNumberException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
            gens--;
        }
    }
}
