package wellijohn.org.varchart.circlepercentchart.vo;

import android.support.annotation.ColorRes;

/**
 * @author: JiangWeiwei
 * @time: 2017/9/14-14:33
 * @email: wellijohn1991@gmail.com
 * @desc:
 */
public class ArcVo {

    private @ColorRes
    int scanColor;

    private float percentInCircle;

    public ArcVo(int scanColor, float percentInCircle) {
        this.scanColor = scanColor;
        this.percentInCircle = percentInCircle;
    }

    public int getScanColor() {
        return scanColor;
    }

    public void setScanColor(int scanColor) {
        this.scanColor = scanColor;
    }

    public float getPercentInCircle() {
        return percentInCircle;
    }

    public void setPercentInCircle(float percentInCircle) {
        this.percentInCircle = percentInCircle;
    }
}
