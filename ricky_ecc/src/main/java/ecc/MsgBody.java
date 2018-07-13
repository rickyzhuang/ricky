package ecc;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/7/12
 */
public class MsgBody {
    int x;
    int y;
    char c;

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

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public MsgBody(int x, int y, char c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public MsgBody() {
    }
}
