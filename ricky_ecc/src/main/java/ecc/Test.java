/*
package ecc;

import com.sun.javafx.scene.control.skin.EmbeddedTextContextMenuContent;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

*/
/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/7/13
 *//*

public class Test extends TestCase {

    public static String CHARSET_UTF8="UNICODE";
    public static String CHARSET_UNICODE="UNICODE";
    public static int G_RANK=3;
    public static int F_Q=127;
    public static int A=4;
    public static int B=20;
    public static Point G=  new Point(3,44);

     public static  void  main(String[] agrs) throws UnsupportedEncodingException {
         String str="￥$国abc中";
       byte[] bytes=str.getBytes("UTF8");
         for (int i = 0; i <bytes.length ; i++) {
             System.out.println("第"+i+"个"+bytes[i]);
         }
         System.out.println(new String(bytes,"UTF8"));

     }

    public void testEccParam() throws  Exception{
        System.out.println("getAllPoint:"+ EccUtil.getAllPoint(A, B,F_Q));
        System.out.println("getAllPoint:共有几个数"+ EccUtil.getAllPoint(A, B,F_Q).size());
        System.out.println("getBasePointList:"+ EccUtil.getBasePointList(A, B,F_Q));
        System.out.println("getBasePointList 共有几个数:"+ EccUtil.getBasePointList(A, B,F_Q).size());

    }
    public void testEccString() throws  Exception{
        int a=4;
        int fq=127;
        int gRank=3;
        Point g=new Point(3,44);
//        String msg="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+~{}|:<>?~！@#￥%……&*（）——+：《》？";
        String msg="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String msg="-1-1-1-1-1";
        Point secret= EccUtil.enCode(msg.getBytes(CHARSET_UTF8),gRank, fq, a, g);
        String secretMsg=new String(secret.getBytes(),CHARSET_UTF8);
        System.out.println("明文："+msg);
        System.out.println("密文："+secretMsg);
        byte[] openBytes= EccUtil.deCode(secret,fq, a, g);
        String openStr=new String(openBytes,CHARSET_UTF8);
        System.out.println("解密："+openStr);
    }

    public void testImg()throws  Exception{
        byte[]  imgBytes=new byte[11031];
        File file=new File("c:/peppa.jpg");
        File secretFile=new File("c:/peppaSecret.jpg");
        FileInputStream inputStream=new FileInputStream(file);
        FileOutputStream outputStream=new FileOutputStream(secretFile);
        int i=0;
        int index=0;
        while((i=inputStream.read())!=-1){//把读取的数据放到i中
            imgBytes[index]=(byte) i;
            index++;
        }
        Point imgSecret= EccUtil.enCode(imgBytes,G_RANK, F_Q, A, G);
        outputStream.write(imgSecret.getBytes());
        inputStream.close();
        outputStream.close();
    }
    public static byte[] image2Bytes(String imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
        return bytes;
    }

    public static void copy(String imgSrc) throws Exception {
        imgSrc="c:/peppa.jpg";
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
    }

    public  void testImgFun() throws Exception {
        //先模拟一个图形byte[]
        byte[] open =image2Bytes("c:/peppa.jpg");
        //存为文件 -1  6 0
        Point imgSecret= EccUtil.enCode(open,G_RANK, 129, A, G);
        buff2Image(imgSecret.getBytes(),"c:/secret.jpg");
        byte[] openBytes=EccUtil.deCode(imgSecret,129,A,G);
        buff2Image(openBytes,"c:/open.jpg");
        for (int i = 0; i <open.length ; i++) {
            if (open[i]!=openBytes[i]) {
                System.out.println("====================================i="+i+","+open[i]+","+openBytes[i]);
            }
        }
        System.out.println("Hello World!");
    }


    public void testEccInt() throws  Exception{
        int a=4;
        int fq=127;
        int gRank=3;
        Point g=new Point(3,44);
        String msg="-1";
        Point secret= EccUtil.enCode(msg.getBytes(CHARSET_UTF8),gRank, fq, a, g);
        String secretMsg=new String(secret.getBytes(),CHARSET_UTF8);
        System.out.println("明文："+msg);
        System.out.println("密文："+secretMsg);
        byte[] openBytes= EccUtil.deCode(secret,fq, a, g);
        String openStr=new String(openBytes,CHARSET_UTF8);
        System.out.println("解密后文字:"+openStr);
    }

   public static  void buff2Image(byte[] b,String tagSrc) throws Exception {
        FileOutputStream fout = new FileOutputStream(tagSrc);
        //将字节写入文件
        fout.write(b);
        fout.close();
    }

    */
/**
     * Java中取余运算具有如下性质：对所有int数值a和所有非零int数值b满足：
     　　(a / b ) * b + (a % b) == a;
     　　这意味着当取余操作返回一个非零的结果时。它与左操作数具有相同的正负符号
     *//*

    public  void testMod(){
        int a=44*44%127;
        int b=(3*3*3+4*3+20)%127;
        System.out.println( a);
        System.out.println( b);
    }
    public void testAllBytes(){
        byte[] allBytes=new byte[256];
        int index=0;
        for (byte i = -128; index<256 ; i++,index++) {
            allBytes[index]=i;
//            System.out.println(index+"==="+i);
        }
//        System.out.println("加密前:"+new String(allBytes));
        Point enPoint= EccUtil.enCode(allBytes, G_RANK, 129, A, G);
//        System.out.println("加密后："+new String(enPoint.getBytes()));
        byte[] after=EccUtil.deCode(enPoint,129,A,G);
//        System.out.println("解密后："+new String(after));
        for (int i = 0; i <after.length ; i++) {
            System.out.println("after"+i+":"+after[i]);
        }

    }

    public void testByte119(){
        byte[] allBytes=new byte[1];
        allBytes[0]=119;
        Point enPoint= EccUtil.enCode(allBytes, G_RANK, 129, A, G);
//        System.out.println("加密后："+new String(enPoint.getBytes()));
        byte[] after=EccUtil.deCode(enPoint,129,A,G);
//        System.out.println("解密后："+new String(after));
        for (int i = 0; i <after.length ; i++) {
            System.out.println("after"+i+":"+after[i]);
        }

    }

    public void  testEccParamTest(){
        List<Point> allList= EccUtil.getAllPoint(A,20,F_Q );
        List<Point> basePointList= EccUtil.getBasePointList(A,20,F_Q );
        System.out.println(basePointList);
    }

    public void testMask(){
        Integer  a=0x01;
        Integer  b=0x11;
//        Integer.toBinaryString("1111111111");
        System.out.println("a"+Integer.toBinaryString(a));
        System.out.println("~a"+Integer.toBinaryString(~a));
        System.out.println("a|b"+Integer.toBinaryString(a|b));
        System.out.println("a&b"+Integer.toBinaryString(a&b));
        System.out.println("a^b"+Integer.toBinaryString(a^b));
        System.out.println("5的二进制"+Integer.toBinaryString(5));
        System.out.println("5的bitCount"+Integer.bitCount(5));
    }
    public void testMask1(){
        String  totalMask  ="1111111111";
        String  selectMask="1010101010";
        Float  percent=0.8f;
        String target1="1111111010";//  应该是100%命中  1  3   5 7 9
        String target2="0111111010";// 应该是80%命中  3 5 7   9
        System.out.println("5的bitCount"+Integer.bitCount(5));
        System.out.println("5的bitCount"+Integer.bitCount(5));
    }
    public void testMask2(){
        String  totalMask  ="1111111111";
        String  selectMask="1010101010";
        Float  percent=0.8f;
        String target1="1111111010";//  应该是100%命中  1  3   5 7 9
        String target2="0111111010";// 应该是80%命中  3 5 7   9
        char[] selectArray=selectMask.toCharArray();
        char[] target1Array=target1.toCharArray();
        System.out.println("5的bitCount"+Integer.bitCount(5));

        Date d=new Date();
        d.setTime(1542072117413L);
        System.out.println(d);
    }

}
*/
