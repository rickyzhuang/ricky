package ecc;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/7/13
 */
public class Test extends TestCase {

    public static String CHARSET_UTF8="UTF8";
    public static String CHARSET_UNICODE="UNICODE";
    public static int G_RANK=3;
    public static int F_Q=127;
    public static int A=3;
    public static Point G=  new Point(3,44);

     public static  void  main(String[] agrs) throws UnsupportedEncodingException {
         String str="￥$国abc中";
       byte[] bytes=str.getBytes("UTF8");
         for (int i = 0; i <bytes.length ; i++) {
             System.out.println("第"+i+"个"+bytes[i]);
         }
         System.out.println(new String(bytes,"UTF8"));

     }
    public void testEccString() throws  Exception{
        int a=4;
        int fq=127;
        int gRank=3;
        Point g=new Point(3,44);
        String msg="我是庄钾寅zhuangjiayin!@#123!！|~";
        Point secret= EccUtil.enCode(msg.getBytes(CHARSET_UTF8),gRank, fq, a, g);
        String secretMsg=new String(secret.getBytes(),CHARSET_UTF8);
        System.out.println("明文："+msg);
        System.out.println("密文："+secretMsg);
        byte[] openBytes= EccUtil.deCode(secret,fq, a, g);
        String openStr=new String(openBytes,CHARSET_UTF8);
        System.out.println("解密后文字:"+openStr);
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

    public  void testAain() throws Exception {
        //先模拟一个图形byte[]
        byte[] open =image2Bytes("c:/peppa.jpg");
        //存为文件 -1  6 0
        Point imgSecret= EccUtil.enCode(open,G_RANK, F_Q, A, G);
        buff2Image(imgSecret.getBytes(),"c:/secret.jpg");
        byte[] openBytes=EccUtil.deCode(imgSecret,F_Q,A,G);
        buff2Image(openBytes,"c:/open.jpg");
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


}
