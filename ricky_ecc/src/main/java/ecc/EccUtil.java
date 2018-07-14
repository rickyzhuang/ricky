package ecc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Describetion
 * y 2= x 3+ 4x + 20    (fq=127)
 *  int a=4;
 int b=20;
 int R=119;
 int fq=127;
 int d=2;
 int gRank=3;
 struct point g={3,44};
 * Created  by  zhuangjiayin
 * Date : 2018/7/12
 */
public class EccUtil {

public  static  int PRIVATE_KEY=2;
public  static  String  CHARSET_UTF8="UTF8";
    /**
     * 判断两个点Point是否一致
     * @param p1
     * @param p2
     * @return
     */
    public  static  boolean PointEqual(Point p1,Point p2){
        if (p1.getX()==p2.getX()&&p1.getY()==p2.getY()) {
             return  true;
        }else{
            return  false;
        }

    }

    /**
     * 获取2~k内的连续数组
     * @param k
     * @return
     */
     public  static int[] getScorpArray(int k){
        int[] res=new int[k-1];
        int count=0;
         for (int i = 2; i <=k ; i++) {
             res[count++]=i;
         }
         return  res;
     }


    /**
     * 判断n是否是素数
     * @param n
     * @return
     */
        public  static  boolean isPrimeNum(int n){
            if (n ==1||n==2) {
                return  true;
            }
            if (n<1){
                return false;
            }
            for (int i = 2; i <n/2+1 ; i++) {
                if (n%2==0) {
                    return false;
                }
            }
            return  true;
        }


    /**
     * 获取椭圆曲线的阶
     * 只是求fq域里有多少个点在上面
     * @param a
     * @param b
     * @param fq
     * @return
     */
    public  static  int  getKeyNum(int a,int b,int fq){
            int n=1;
        for (int i = 0; i <fq ; i++) {
            for (int j = 0; j <fq ; j++) {
                if (i*i-j*j*j+a*j+b==0) {
                    n=n+1;
                }
            }
        }
        return n;
    }

    /**
     * 点相加运算
     * 先求斜率 r 再计算相加后的结果
     *表达式有兴趣的再研究
     * @param p1
     * @param p2
     * @param a
     * @return
     */
    public static   Point  pointAdd(Point p1,Point p2,int a){
        Point p3=new Point();
        int r;
        if (p1.x==p2.x&&p1.y==p2.y){
            r=3*(p1.x*p1.x+a)/(2*p1.y);
        }else{
            r=1*(p2.y-p1.y)/(p2.x-p1.x);
        }
        p3.setX(1*r*r-p1.x-p2.x);
        p3.setY(r*(p1.x-p3.x)-p2.y);
        return p3;
    }

    /**
     * 该方法只是一个p1做一次自加运算
     * @param p1
     * @param a
     * @return
     */
    public  static  Point addSelf(Point p1,int a){
        Point p3=new Point();
        int r=(3*p1.x*p1.x+a)/(2*p1.y);
        p3.x=r*r-p1.x-p1.x;
        p3.y=r*(p1.x-p3.x)-p1.y;
        return p3;
    }

    /**
     * 倍乘 自己加K次  a是椭圆曲线特征函数
     * @param p1
     * @param k
     * @param a
     * @return
     */
    public  static  Point kMulPoint(Point p1,int k,int a){
        Point cache=p1;
        for( int i=1;i<k;i++) {
            p1=EccUtil.pointAdd(p1,cache,a);
        }
        return  p1;
    }

    /**
     * 求基点的阶  可以先不管  这个基点阶为3
     * @param g
     * @param a
     * @param b
     * @param fq
     * @return
     */
    public  static  int getBaseKey(Point g,int a,int b,int fq){
        int n1=5;
        Point cache=new Point();
        if( g.y*g.y==g.x*g.x*g.x+a*g.x+b){
          /*  Random r1 = new Random();

　　　　  int k= r1.nextInt(10);*/
            cache=EccUtil.kMulPoint( g, n1,a);
        }

        if ( g.x==cache.x%fq&&EccUtil.isPrimeNum(n1)){
            return  n1;
        }
        return n1;

    }

    /**
     * 加密方法
     * @param openWord
     * @param gRank
     * @param fq
     * @param a
     * @param g
     * @return
     */
    public  static  Point  enCode(byte[] openWord,int gRank,int fq,int a,Point g){
        Point  p1,p2 , secretWord;
        secretWord=new Point();
        byte[] secretBytes=new byte[openWord.length];
        int k;
        Point q=EccUtil.kMulPoint(g,PRIVATE_KEY,a);// 接收方公布的公钥
        while (true){

                Random r1 = new Random();
//            rand.nextInt(MAX - MIN + 1) + MIN
//            k=(r1.nextInt(gRank-1-1+1)+1) %gRank;
            k=PRIVATE_KEY;
            System.out.println("k=:"+k);
            p1=EccUtil.kMulPoint(g,k,a);                 //   计算p1=kg(x1,y1)      第一点P点    //

            p2=EccUtil.kMulPoint(q,k,a);                 // 计算p2=kq=(x2,y2)  第二个点//
            if (p2.x!=0) break;
        }
//        mw.c=(char)(m1*p2.x)%fq ;            // 加密c=字符与x2作乘//


        System.out.println("encode K:"+k);
        System.out.println("encode p2.x:"+p2.x);
        for (int i = 0; i <openWord.length ; i++) {
//            System.out.println("en"+i+":"+openWord[i]);
            secretBytes[i]=(byte) (openWord[i]*p2.x%fq);  // 强转之后的值 也必须落在byte范围内
//            secretBytes[i]=int2byte(openWord[i]*p2.x);  // 强转之后的值 也必须落在byte范围内
        }
        secretWord.x=p1.x;
        secretWord.y=p1.y;
        secretWord.setBytes(secretBytes);             // 打包(p,c)发送给接受方        //

        return secretWord;
    }

    public static byte int2byte(int x){
        if (x >=0) {
            return (byte) (x%(Byte.MAX_VALUE+1));
        }else{
            return (byte) (x%(Byte.MIN_VALUE));
        }
    }

    /**
     * 解密方法
     * @param secretWord
     * @param n1
     * @param fq
     * @param a
     * @param g
     * @return
     */
    public  static  byte[]   deCode(Point  secretWord,int fq,int a,Point g){
         Point p1=new Point();
         Point p2=new Point();
        byte[] openBytes=new byte[secretWord.getBytes().length];
        char m;
        int i;
        p1.x=secretWord.x;
        p1.y=secretWord.y;
        p2=EccUtil.kMulPoint (p1,PRIVATE_KEY,a);          //    计算dp=(x2,y2)     //
        System.out.println("decode p2.x:"+p2.x);

   /*     m=(mw1.c/p2.x)%fq ;           //      恢复m=c乘x逆 c除以x2             //
        if ((mw1.c%p2.x)!=0) {
            for (i=0;i<fq&&(mw1.c!=(p2.x*i)%fq);i++);     //         ?            //
            m=i;
        }*/
        byte[] secretBytes=secretWord.getBytes();
        for (int j = 0; j <secretBytes.length ; j++) {

//            openBytes[j]=(byte) (secretBytes[j]/p2.x%fq); //      恢复m=c乘x逆 c除以x2             //
            if (true||secretBytes[j]%p2.x!=0) {   //表示不是原值
                for (int k =-128; k <=127 ; k++) {
                    if (secretBytes[j]!=0&&secretBytes[j]==p2.x*k%fq) {
                        openBytes[j]=(byte) k;
//                        System.out.println("de"+j+":"+k);
                        continue;
                    }
                }
            }
        }

        return openBytes;
    }



    /**
     *
     * y 2= x 3+ 4x + 20    (fq=127)
     *  int a=4;
     int b=20;
     int R=119;
     int fq=127;
     int d=2;
     int gRank=3;
     struct point g={3,44};
     * @param args
     */
    public static  void  mainSub (String[] args) throws Exception {
        int a=4;
        int fq=127;
        int gRank=3;
        Point g=new Point(3,44);
        String msg="我是庄钾寅zhuangjiayin!@#123!！|~";
//        String msg="庄钾寅";
        Point secret= EccUtil.enCode(msg.getBytes(CHARSET_UTF8),gRank, fq, a, g);
        String secretMsg=new String(secret.getBytes(),CHARSET_UTF8);
        System.out.println("明文："+msg);
        System.out.println("密文："+secretMsg);
        byte[] openBytes= EccUtil.deCode(secret,fq, a, g);
        String openStr=new String(openBytes,CHARSET_UTF8);
        System.out.println("解密后文字:"+openStr);
        byte[]  imgBytes=new byte[4615];
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
        Point imgSecret= EccUtil.enCode(imgBytes,gRank, fq, a, g);
        outputStream.write(imgSecret.getBytes());
        inputStream.close();
        outputStream.close();
        //把
    }


/*
    public static  void  main (String[] args) throws Exception {
        int a=4;
        int fq=127;
        int gRank=3;
        Point g=new Point(3,44);
        byte[]  imgBytes=new byte[4615];
        File file=new File("c:/peppaSecret.jpg");
        File secretFile=new File("c:/peppaOpen.jpg");
        FileInputStream inputStream=new FileInputStream(file);
        FileOutputStream outputStream=new FileOutputStream(secretFile);


        int i=0;
        int index=0;
        while((i=inputStream.read())!=-1){//把读取的数据放到i中
            imgBytes[index]=(byte) i;
            index++;
        }
        Point imgSecret= EccUtil.enCode(imgBytes,gRank, fq, a, g);
        byte[] openBytes= EccUtil.deCode(imgSecret,fq, a, g);
        inputStream.close();
        outputStream.write(openBytes);
        outputStream.close();
        //把
    }
*/


}

