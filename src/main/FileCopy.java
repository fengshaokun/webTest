


import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;


public class FileCopy {




    public static String hexStringToByte(String hex) {
        String result = "";
        int i = Integer.parseInt(hex, 16);
             result = Integer.toBinaryString(i);
            while(result.length() < (hex.length()*4)){
                result = "0"+result;
            }
        return result;
    }

    public  static  String test(String hex){
        StringBuffer s = new StringBuffer();
        if(hex.length()%4 !=0){
             return "";
        }
        while(hex.length()>=4){
             s.append(hexStringToByte(hex.substring(0, 4)));
            hex=hex.substring(4);
        }
        return s.toString();
    }


    public static long convertToDecimal(String binary) {
        long s = Long.valueOf(binary, 2);
        return s;
    }



    public static void main(String[] args)  {


        try {
            String str = test("4166");
            String sStr = str.substring(0,1);
            String eStr = str.substring(1,12);
            String mStr = str.substring(12,str.length());
            long en = convertToDecimal(eStr);
            int e = (int)en-1023;
            long mn = convertToDecimal(mStr);
            int s = Integer.valueOf(sStr);
            double v=0;
            if (s==0){
                 v = (Math.pow(-1, 0)) * (Math.pow(2, e))*(1+(mn/Math.pow(2, 52)));
            }else v = -(Math.pow(-1, 0)) * (Math.pow(2, e))*(1+(mn/Math.pow(2, 52)));
            BigDecimal bigDecimal = new BigDecimal(v);
            System.out.println(bigDecimal.toPlainString());

            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            String scoreStr = nf.format(v);
            System.out.println(scoreStr);


        } catch (NumberFormatException ex) {

        }


    }
}
