package ecc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/7/13
 */
public class Test {

     public static  void  main(String[] agrs) throws UnsupportedEncodingException {
         String str="￥$国abc中";
       byte[] bytes=str.getBytes("UTF8");
         for (int i = 0; i <bytes.length ; i++) {
             System.out.println("第"+i+"个"+bytes[i]);
         }
         System.out.println(new String(bytes,"UTF8"));


     }
}
