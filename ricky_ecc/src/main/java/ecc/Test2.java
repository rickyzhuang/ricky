package ecc;

import junit.framework.TestCase;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/7/13
 */
public class Test2 extends TestCase {

    public static String CHARSET_UTF8="UNICODE";
    public static String CHARSET_UNICODE="UNICODE";
    public static int G_RANK=3;
//    public static int F_Q=16777213;
//    public static int F_Q=5003;
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

     public void testGetBigPrime(){
         for (int i = 5000; i <Integer.MAX_VALUE/128 ; i++) {
             if (EccUtil.isPrimeNum(i)){
                 System.out.println(i);
             }
         }

     }

    public void testEccParam() throws  Exception{
        System.out.println("getAllPoint:"+ EccUtil.getAllPoint(A, B,F_Q));
        System.out.println("getAllPoint:共有几个数"+ EccUtil.getAllPoint(A, B,F_Q).size());
//        System.out.println("getBasePointList:"+ EccUtil.getBasePointList(A, B,F_Q));
//        System.out.println("getBasePointList 共有几个数:"+ EccUtil.getBasePointList(A, B,F_Q).size());

    }
    public void testEccString() throws  Exception{
/*        int a=4;
        int fq=127;
        int gRank=3;
        Point g=new Point(3,44);*/
        String msg="我我的中国人我爱中国abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+~{}|:<>?~！@#￥%……&*（）——+：《》？";
//        String msg="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String msg="-1-1-1-1-1";
        Point basePoint=EccUtil.getFirstBasePoint(A, B, F_Q);
//        Point basePoint=new Point(36,46);
        int basePointRank=EccUtil.getBasePointRank(basePoint, F_Q, A);
        Random random=new Random();
        int receivePrivateKey=random.nextInt(basePointRank-1-1+1)+1;
//         receivePrivateKey=2;
        System.out.println("接收方秘钥："+receivePrivateKey);
        Point qPoint=EccUtil.createOpenKey(basePoint,A, receivePrivateKey);
        System.out.println("公钥q："+qPoint);

        int sendPrivateKey=random.nextInt(basePointRank-1-1+1)+1;
//         sendPrivateKey=2;
        System.out.println("发送方秘钥："+sendPrivateKey);
        Point secret= EccUtil.enCode(msg.getBytes(CHARSET_UTF8),sendPrivateKey,F_Q, A,basePoint,qPoint);
        String secretMsg=new String(secret.getBytes(),CHARSET_UTF8);
//        String secretMsg=secret.getSecretInts();
        System.out.println("明文："+msg);
        System.out.println("密文："+secretMsg);
        System.out.println("openBytes："+msg.getBytes(CHARSET_UTF8));
        System.out.println("getSecretInts："+secret.getSecretInts());
        byte[] openBytes= EccUtil.deCode(secret, A, receivePrivateKey);
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
        //存为文件 -1  6 0
        Point basePoint=EccUtil.getFirstBasePoint(A, B, F_Q);
        int basePointRank=EccUtil.getBasePointRank(basePoint, F_Q, A);
        Random random=new Random();
        int receivePrivateKey=random.nextInt(basePointRank-1-1+1)+1;
        System.out.println("接收方秘钥："+receivePrivateKey);
        Point qPoint=EccUtil.createOpenKey(basePoint,A, receivePrivateKey);
        System.out.println("公钥q："+qPoint);

        int sendPrivateKey=random.nextInt(basePointRank-1-1+1)+1;
        System.out.println("发送方秘钥："+sendPrivateKey);
        Point imgSecret= EccUtil.enCode(null,sendPrivateKey,F_Q, A,basePoint,qPoint);
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
        String source="c:/peppa.jpg";
        String des="c:/aaa.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(source)));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(des)));
        while(bis.read() != -1) {
            bos.write(bis.read()+1);
        }

        //存为文件 -1  6 0
        Point basePoint=EccUtil.getFirstBasePoint(A, B, F_Q);
        int basePointRank=EccUtil.getBasePointRank(basePoint, F_Q, A);
        Random random=new Random();
        int receivePrivateKey=random.nextInt(basePointRank-1-1+1)+1;
        System.out.println("接收方秘钥："+receivePrivateKey);
        Point qPoint=EccUtil.createOpenKey(basePoint,A, receivePrivateKey);
        System.out.println("公钥q："+qPoint);

        int sendPrivateKey=random.nextInt(basePointRank-1-1+1)+1;
        System.out.println("发送方秘钥："+sendPrivateKey);
        Point secret= EccUtil.enCode(open,sendPrivateKey,F_Q, A,basePoint,qPoint);

        byte[] openBytes= EccUtil.deCode(secret, A, receivePrivateKey);
        String openStr=new String(openBytes,CHARSET_UTF8);
        System.out.println("解密："+openStr);

        buff2Image(secret.getBytes(),"c:/secret.jpg");
        buff2Image(openBytes,"c:/open.jpg");
    }

    public static  void buff2Image(byte[] b,String tagSrc) throws Exception {
        FileOutputStream fout = new FileOutputStream(tagSrc);
        //将字节写入文件
        fout.write(b);
        fout.close();
    }

    public void testGamb(){
         int winRatio=95 ;
         int prizeRatio=5;
         int loseRatio=5;
         int loseCost=100;
         int n=1;
         while (true){
//             winRatio*prizeRatio
         }

    }


}
