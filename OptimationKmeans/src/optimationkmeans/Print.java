/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimationkmeans;

/**
 *
 * @author asus
 */
public class Print {
    public void printArray(int[] array){
        for(int i=0; i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    public void printArray(int[][] array){
        for(int i=0; i<array.length;i++){
            for(int j=0; j<array[i].length;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void printArray(float[] array){
        for(int i=0; i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    public void printArray(float[][] array){
        for(int i=0; i<array.length;i++){
            for(int j=0; j<array[i].length;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void printArray(double[] array){
        for(int i=0; i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    public void printArray(double[][] array){
        for(int i=0; i<array.length;i++){
            for(int j=0; j<array[i].length;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
