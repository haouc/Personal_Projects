/**
 * Created by haozhou on 6/28/15.
 */
public class revertString {
    public String reverseWords(String s) {
        s = s.trim();
        System.out.println(s);
        String[] strArr = s.split("\\s+");
        System.out.println(strArr.length);
        StringBuilder sb = new StringBuilder();
        for(int i = strArr.length-1; i >= 0; i--){
                System.out.println(strArr[i]);
                sb.append(strArr[i]);
                if(i != 0) sb.append(" ");

        }
        return sb.toString();
    }
    public static void main(String[] args){
        String str = " a  b  ";
        System.out.print(new revertString().reverseWords(str));
    }
}
