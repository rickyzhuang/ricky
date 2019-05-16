package image;
import ecc.EccUtil;
import ecc.Point;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2019/5/16
 */
public class ImageTest extends JPanel{
    public static int F_Q=127;
    public static int A=4;
    public static int B=20;

        public static void grayImage(Graphics g) throws IOException{

            //读文件，图片文件放在项目同级目录下
            File file = new File("c:/image/source.jpg");
            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();

            //new 一个 BufferedImage的缓冲区，，即时空的，，只起到缓冲作用，，将相应的图片转换
            BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            BufferedImage encodeImage = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_BINARY);
            //BufferedImage grayImage = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_BINARY);
            for(int i= 0 ; i < width ; i++){
                for(int j = 0 ; j < height; j++){
                    int rgb = image.getRGB(i, j);
                    grayImage.setRGB(i, j, rgb);  //将像素存入缓冲区
                    encodeImage.setRGB(i, j, rgb);  //将像素存入缓冲区
                }
            }

//       将图片存入相应的路径下：
       File greyFile = new File("c:/image/gray.jpg");
       ImageIO.write(grayImage, "jpg", greyFile);
            File encodeFile = new File("c:/image/encodeFile.jpg");
            ImageIO.write(grayImage, "jpg", encodeFile);


            Point basePoint=EccUtil.getFirstBasePoint(A, B, F_Q);
            int basePointRank=EccUtil.getBasePointRank(basePoint, F_Q, A);
            Random random=new Random();
            int receivePrivateKey=random.nextInt(basePointRank-1-1+1)+1;
            System.out.println("接收方秘钥："+receivePrivateKey);
            Point qPoint=EccUtil.createOpenKey(basePoint,A, receivePrivateKey);
            System.out.println("公钥q："+qPoint);

            int sendPrivateKey=random.nextInt(basePointRank-1-1+1)+1;
            System.out.println("发送方秘钥："+sendPrivateKey);


            Point  sourcePoint=new Point();
            sourcePoint.setSourceImage(image);
            Point encodePoint=EccUtil.enCodeImage(sourcePoint,sendPrivateKey,F_Q,A,basePoint,qPoint);
            Point deCodePoint=EccUtil.deCodeImage(encodePoint,A,receivePrivateKey);


            File secretImageFile = new File("c:/image/ecc/secretImage.jpg");
            ImageIO.write(encodePoint.getEncodeImage(), "jpg", secretImageFile);
            File originalImageFile = new File("c:/image/ecc/originalImage.jpg");
            ImageIO.write(deCodePoint.getSourceImage(), "jpg", originalImageFile);

            //画图
            g.drawImage(sourcePoint.getSourceImage(), 0, 0, 380, 400,null);
            g.drawImage(encodePoint.getEncodeImage(),400,0, 380,400,null);
            g.drawImage(deCodePoint.getSourceImage(),800,0, 380,400,null);
        }

        public static void main(String args[]){

            //创建窗口
            JFrame mFrame = new JFrame();
            mFrame.setSize(1200, 500);
            mFrame.setVisible(true);
            mFrame.add(new ImageTest());
        }

        //重写paint 方法 画图
        public void paint(Graphics g){

            try {
                grayImage(g);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

