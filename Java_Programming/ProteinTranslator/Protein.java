/**
 * Created by Hao on 4/28/2015.
 * This class is designed for handling the inputed file and trnalating the Amino Acid 
 * abbreviations to full names. The class provides a function to transfer the Amino Acids
 * and save valid amino acids in strings which are stored in ArrayList. The function validates
 * amino acids by comparing the abbreviation with those standards saved in HashMap as Keys.
 * If match is found, the corresponding value will be saved in string. The function returns
 * an ArrayList which can be further processed by other functions.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Protein {
    //Building a HashMap to store the standard names of amino acids.
    private final HashMap<String, String> AAMAP = new HashMap<String, String>(){{
        put("ALA", "Alanine");
        put("ARG", "Arginine");
        put("ASN", "Asparagine");
        put("ASP", "Aspartic");
        put("CYS", "Cysteine");
        put("GLU", "Glutamic");
        put("GLN", "Glutamine");
        put("GLY", "Glycine");
        put("HIS", "Histidine");
        put("ILE", "Isoleucine");
        put("LEU", "Leucine");
        put("LYS", "Lysine");
        put("MET", "Methionine");
        put("PHE", "Phenylalanine");
        put("PRO", "Proline");
        put("SER", "Serine");
        put("THR", "Threonine");
        put("TRP", "Tryptophan");
        put("TYR", "Tyrosine");
        put("VAL", "Valine");}
    };

    Protein(){
        synchronized(this){};
    }

    //the method read, validates, translates, and stores data from inputed file to ArrayList. 
    ArrayList<String> trans(String input) throws IOException {
        ArrayList<String> strArr = new ArrayList<String>();
        FileReader reader = new FileReader(input);
        BufferedReader bufReader = new BufferedReader(reader);
        String line;

        //Read line by line from the file.
        while((line = bufReader.readLine()) != null){
            String newLine = "";
            String result = "";
            //Since words could be seperated by various non-letter characters, the read string is changed to char array.
            for(char c:line.toCharArray()){
                //only letter will be handled.
                if(Character.isLetter(c)){
                    newLine += c;
                }
            }
            List<String> arr = new ArrayList<String>();
            int i = 0;
            //Since amino acids' abbreviations are always three letters format (at least in this case, sometime could be one-letter),
            //the character array is processed to three-letter string. Those strings are stored in ArrayList.
            while(i < newLine.length()-2){
                String str = "" + newLine.charAt(i) + newLine.charAt(i+1) + newLine.charAt(i+2);
                arr.add(str);
                i+=3;
            }

            //Only valid three-letter strings are translated to full names, invalid ones are replaced by "-".
            //Strings are stored in an ArrayList which is returned when the method is called.
            for(String item: arr){
                if(AAMAP.containsKey(item)){
                    result = result + AAMAP.get(item) + ",";
                }else result = result + "-,";
            }
            result = (result.length() == 0)?"":result.substring(0, result.length()-1);
            strArr.add(result);
        }
        reader.close();
        bufReader.close();

        return strArr;
    }

}
