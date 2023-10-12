// Java Program to Illustrate reading from
// FileReader using FileReader class
 
// Importing input output classes
import java.io.*;
import java.util.Scanner;
 
// Main class
// ReadingFromFile
public class Project1 {
 
    // Main driver method
    public static void main(String[] args) throws Exception
    {
        int numEquations = 0;
        //Getting the coeffiecents from the equations 
        //===================================================================
        int n = 1;
        Scanner scn = new Scanner(System.in);
        while(n==1){
            System.out.println("How many linear equations to solve (=<10):");
            numEquations = scn.nextInt();
            if(numEquations <= 10){
                n = 0;           
            }
            else
                System.out.println("Inavlid amount. Must be 10 or less");
        }

        //create the original matrix, its empty originally
        int[][] matrix = new int[numEquations][4];
        
        n =1;
        System.out.println("(1) Manually Input File or (2) Give Path to File");
        int ans = scn.nextInt();
        String fileName = "";
        while (n==1){
            if(ans == 1){
                int equationnumber =0;
                while(equationnumber < numEquations){
                    matrix[equationnumber] = getEquation();
                    equationnumber++;
                }
                printMatrix(matrix, equationnumber);
                n=4;
            }
            else if(ans ==2){
                System.out.println("Enter text file path:");
                //fileName = scn.nextLine();
                fileName= "C:\\Users\\adria\\Desktop\\Vscode\\ProgrammingProject1\\equations.txt";
                //C:\\Users\\adria\\Desktop\\Vscode\\ProgrammingProject1\\equations.txt
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));

                    String line;
                    int rowIndex = 0;
                    while ((line = reader.readLine()) != null && rowIndex < numEquations){
                        matrix[rowIndex] = parseline(line);
                        rowIndex++;
                    }

                    reader.close();

                    printMatrix(matrix, rowIndex);
                } catch (IOException e){
                    
                }
                n =3;
            }else{
                System.out.println("INVALID RESPONSE");
            }
        }
        scn.close();
        
        
        // GAUSS ELIMINATION BEGINS
        System.out.println("");

        //FOWARD ELIMINATION
            //(n-1)steps 
            //1st step: Look at first row, first column
            int greatestVal= matrix[0][0];
            int EquaNum =0;
        for(int i = 0; i<numEquations; i++){
            if(greatestVal < matrix[i][0])
                greatestVal = matrix[i][0];
                EquaNum = i;
        }
        if(EquaNum !=0){
            for(int i =0; i<4; i++){
                int temp = matrix[0][i];
                matrix[0][i] = matrix[EquaNum][i];
                matrix[EquaNum][i]= temp;
            }
        }
        printMatrix(matrix, numEquations);

        
    }

    private static int[] getEquation() {
        int[] array = new int[4];
        Scanner scn = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
                if(i ==0){
                System.out.println("Enter value for x");
                array[i] = scn.nextInt();
                }
                if(i ==1){
                System.out.println("Enter value for y");
                array[i] = scn.nextInt();
                }
                if(i ==2){
                System.out.println("Enter value for z");
                array[i] = scn.nextInt();
                }
                if(i ==3){
                System.out.println("Enter the sum");
                array[i] = scn.nextInt();
                }
            }
        
        return array;

    }

    private static void printMatrix(int[][] matrix, int rowIndex) {
        for (int i = 0; i < rowIndex; i++) {
            System.out.print("[");
            for (int j = 0; j < 4; j++) {
                if(j==3){
                    System.out.print(matrix[i][j]);
                }
                else{
                System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println("]");
        }
    }

    private static int[] parseline(String line) {
        String[] tokens = line.split(" ");
        int[] array = new int[4];

        for (int i = 0; i < 4; i++) {
            if (i < tokens.length) {
                array[i] = Integer.parseInt(tokens[i]);
            } else {
                // Handle the case where there are not enough numbers in the line
                array[i] = 0;
            }
        }
        
        return array;
    }

}
