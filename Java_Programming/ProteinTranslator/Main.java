import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by Hao on 4/28/2015.
 * This class is the main class which also contains two functions that handles the extracting columns or rows from input file.
 */
public class Main {

    private static Protein sampleProtein = new Protein(); //new object.
    private static ArrayList<String> resultArr;

    //This method is for translating amino acid abbreviations to full names.
    static String Substitution(ArrayList<String> arr){
        String result = "";
        for(String str: arr){
            result = result + str + "\n";
        }
        return result;
    }

    //This method is for extracting rows from input file based on user entered arguments.
    //Since the provided file contains empty rows, I keep those empty rows, which means user may require empty row.
    static String TranslateRows(String[] args){
        int rows = args.length;

        //Taking care invalid arguments.
        if(rows == 0){
            return "No valid request has been made!";
        }
        if(rows < 0) throw new IllegalArgumentException("Need Non-Negative Row Number!");

        //Taking care the required rows from the ArrayList which would be return by calling protein class.
        String result = "";
        for(int i = 0; i < rows; i++){
            int index = Integer.parseInt(args[i]);

            if(index < resultArr.size()){
                result = result + ((resultArr.get(index).length() > 0)? resultArr.get(index):"(This is an empty row!)") + "\n";
            }else{
                int tracker = 0;
                while(tracker < resultArr.get(0).split(",").length){
                    result = result + "-,";
                    tracker++;
                }
                result = result.substring(0,result.length()-1);  //Remove the last comma.
                result += "\n";
            }
        }
        return result;
    }

    //This method is for extracting columns based users' requirements. Since there are empty
    //rows, the method has to take care those.
    static String TranslateColumns(String[] args){
        int columns = args.length;

        //Handling invalid arguments.
        if(columns == 0){
            return "No valid request has been made!";
        }
        if(columns < 0) throw new IllegalArgumentException("Need Non-Negative Row Number!");
        String result = "";
        //Extracting the pointed strings by passed user's input.
        for(int i = 0; i < columns; i++){
            int index = Integer.parseInt(args[i]);
            if(index < resultArr.get(0).split(",").length){
                for(String str: resultArr){
                    String[] strArr = str.split(",");
                    if(str.length() > 0)
                        result = result + strArr[index] + ",";
                }
                result = result.substring(0,result.length()-1);
                result += "\n";
            }else{
                for(String str: resultArr){
                    result += (str.length() > 0)? "-," : "";
                }
                result = result.substring(0,result.length()-1);
                result += "\n";
            }
        }
        return result;
    }

    //main method is for output in new file or console.
    public static void main(String[] args) throws IOException {
		//Take the first argument as file name input.
        resultArr = sampleProtein.trans(args[0]);
		//Change array to only have the column and row numbers.
		for(int i = 0; i < args.length-1; i++){
			args[i] = args[i+1];
		}
		args = Arrays.copyOf(args, args.length-1);

        System.out.println("Column Translated" + "\n" + TranslateColumns(args));
        System.out.println("Row Translated" + "\n" + TranslateRows(args));

        FileWriter writer = new FileWriter("result.csv");
        BufferedWriter bufWriter = new BufferedWriter(writer);
        bufWriter.write(Substitution(resultArr));
        bufWriter.flush();
        writer.close();
        bufWriter.close();
    }


}
