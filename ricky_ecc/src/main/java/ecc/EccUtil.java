package ecc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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
     * 求域上所有的点
     * y 2= x 3+ 4x + 20    (fq=127)
     * 只是求fq域里有多少个点在上面
     * @param a
     * @param b
     * @param fq
     * @return
     */
    public  static List<Point> getAllPoint(int a, int b, int fq){
        Point point;
        List<Point> list=new ArrayList<Point>();
        for (int i = -fq; i <=fq ; i++) {
            for (int j = -fq; j <=fq ; j++) {
                if (i*i==j*j*j+a*j+b) {//点在曲线上
                    point=new Point();
                    point.y=i;
                    point.x=j;
                    list.add(point);
                }
            }
        }
        return list;
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
//        if (p1.x==p2.x&&p1.y==p2.y){
        if (p1.x==p2.x){
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
     *  *y 2= x 3+ 4x + 20    (fq=127)
     * 根据椭圆特征求基点
     * 要求 1、该点要在曲线上，2  该点经过n次点乘后 得到单元o。3，这个n必须得是素数
     * @param a
     * @param b
     * @param fq
     * @return
     */
    public  static  List<Point> getBasePointList(int a,int b,int fq){
      List<Point> allPoint=EccUtil.getAllPoint(a,b,fq);
      List<Point> basePoint=new ArrayList<Point>();
        if (allPoint == null) {
            return null;
        }
        Point nextPoint;
        for (Point point: allPoint) {
            for (int i = 2; i <=fq ; i++) {
                nextPoint=EccUtil.kMulPoint(point,i,a);
                if (nextPoint.x==point.x&&isPrimeNum(i)) {
                    point.n=i;
                    basePoint.add(point);
                    continue;
                }
            }
        }
        return basePoint;
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


        System.out.println("encode K:"+k);
        System.out.println("encode p2.x:"+p2.x);
        for (int i = 0; i <openWord.length ; i++) {
            secretBytes[i]=int2byte(openWord[i]*p2.x);  //  加密c=字符与x2作乘 强转之后的值 也必须落在byte范围内
        }
        secretWord.x=p1.x;
        secretWord.y=p1.y;
        secretWord.setBytes(secretBytes);             // 打包(p,c)发送给接受方        //

        return secretWord;
    }

    /**
     * translate int to byte
     * when decode  do it reversal
     *Java中取余运算具有如下性质：对所有int数值a和所有非零int数值b满足：
     　　(a / b ) * b + (a % b) == a;
     　　这意味着当取余操作返回一个非零的结果时。它与左操作数具有相同的正负符号
     * @param x
     * @return
     */
    public static byte int2byte(int x){
        if (x >=0) {
            return (byte) (x%(128));
        }else{
            return (byte) (x%(129));
        }
    }

    /**
     * 解密方法
     * @param secretWord
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

        byte[] secretBytes=secretWord.getBytes();
        for (int j = 0; j <secretBytes.length ; j++) { // 恢复m=c乘x逆 c除以x2             //

            byte  temp=secretBytes[j];

            if (temp>=0) {
                for (int k = 0; k <=127 ; k++) {
                    if (temp==p2.x*k%128) {
                        openBytes[j]=(byte) k;
                        continue;
                    }
                }
            }else {
                for (int k = -128; k <0 ; k++) {
                    if (temp==p2.x*k%129) {
                        openBytes[j]=(byte) k;
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





}

