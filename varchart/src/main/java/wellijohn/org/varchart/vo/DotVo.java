package wellijohn.org.varchart.vo;

/**
 * @author: JiangWeiwei
 * @time: 2017/9/7-14:13
 * @email:
 * @desc: 显示的坐标点
 */
public class DotVo {
    private String x;

    private double y;

    public DotVo(String x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
