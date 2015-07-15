/**
 * Created by haozhou on 6/28/15.
 */
public class BitAdd {
    public int rangeBitwiseAnd(int m, int n) {
        //Brian Kernighan ALG
        if (m > n){
            int temp = m;
            m = n;
            n = temp;
        }

        while(n > m){
            n &= (n-1);
        }

        return m & n;
//        if(m > n){
//            int temp = m;
//            m = n;
//            n = temp;
//        }
////        int interval = n - m;
//        int result = 0;
//        int tempOne = m;
//        for (int i = m+1; i <= n; i++){
//
//            int tempTwo = i;
//            for(int j = 0; j < 32; j++){
//                result = tempOne & tempTwo;
//                result >>>= 1;
//                tempTwo >>>= 1;
//                System.out.println(result);
//            }
//            System.out.println("outloop:" + result +" index: "+ i);
//
//        }
//        return result;
    }
    public static void main(String[] args){
        System.out.print(new BitAdd().rangeBitwiseAnd(0, 7));
    }
}
