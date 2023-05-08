/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conwaysgameoflife;

/**
 *
 * @author eu
 */
public class Matrix {
    public int size;
    public int[][] board;
    
    public Matrix(int size, int[][] array){
        this.size = size;
        this.board = array;
    }
    
    public int[][] execute(int times) throws InvalidNumberException{
        while(times > 0){
            this.changeGeneration();
            times--;
        }
        
        return this.board;
    }
    
    public void changeGeneration() throws InvalidNumberException{
        int[][] tempBoard = this.board;
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tempBoard[i][j] = verifyRules(i,j);
            }
        }
        
        board = tempBoard;
    }
    
    private int verifyRules(int i, int j) throws InvalidNumberException{
        int vizinhos = this.countNeighbors(i, j);
        
        if(board[i][j] == 1){
            if(vizinhos == 2 || vizinhos ==3){
                return 1;
            }else if(vizinhos < 2 || vizinhos > 3){
                return 0;
            }
        }else if(board[i][j] == 0){
            return vizinhos == 3 ? 1 : 0;
        }
        
        throw new InvalidNumberException("Invalid number in the file! Only 1 and 0 are accepted, plese check the given file.");
    }
    
    private int countNeighbors(int i, int j){
        int vizinhos = 0;
        
        for(int x = (i - 1); x < (i + 2); x++){
            for(int y = (j - 1); y < (j + 2); y++){
                if((x >= 0 && x < size) && (y >= 0 && y < size) && (x != i && y != j)){
                    vizinhos = board[x][y] == 1 ? (vizinhos + 1) : vizinhos;
                }
            }
        }
        
        return vizinhos;
    }
    
}
