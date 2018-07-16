package ecc;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/7/12
 */
public class Point {
    int x;
    int y;
    byte[] bytes;
    /**
     * 基点的阶
     */
    int n;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
