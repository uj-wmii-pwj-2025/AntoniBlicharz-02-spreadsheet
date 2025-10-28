package uj.wmii.pwj.spreadsheet;
import java.util.Objects;
public class Spreadsheet {
    public static int operations (String[][] input, String type, String ref1, String ref2) {
        int val1 = extractValue(input, ref1);
        int val2 = extractValue(input, ref2);
        int output = 0;
        if (Objects.equals(type, "ADD"))
            output = val1 + val2;
        else if (Objects.equals(type, "SUB"))
            output = val1 - val2;
        else if (Objects.equals(type, "MUL"))
            output = val1 * val2;
        else if (Objects.equals(type, "DIV"))
            output = val1 / val2;
        else if (Objects.equals(type, "MOD"))
            output = val1 % val2;

        return output;
    }

    public static int extractValue (String[][] input, String reference){
        int output;
        if (reference.charAt(0) == '$'){
            int col = reference.charAt(1) - 65;
            reference = reference.substring(2);
            int row = Integer.parseInt(reference) - 1 ;
            reference = input [row][col];
            output = extractValue(input, reference);
        } else if (reference.charAt(0) == '='){
            reference = reference.substring(1);
            String [] args =  reference.split("\\(|,|\\)");
            output = operations(input, args[0], args[1], args[2]);
        }
        else
            output = Integer.parseInt(reference);

        return output;
    }


    public String[][] calculate(String[][] input) {
        String output [][] = new String [input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                output[i][j] = Integer.toString(extractValue(input,input[i][j]));
            }
        }
        return output;
    }
}
