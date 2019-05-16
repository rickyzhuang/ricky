package ecc;

import lombok.Data;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2019/5/16
 */
@Data
public class LongRgb {
    int i;
    int j;
    long rgb;

    public LongRgb() {

    }

    public LongRgb(int i, int j, long rgb) {
        this.i=i;
        this.j=j;
        this.rgb=rgb;

    }
}
