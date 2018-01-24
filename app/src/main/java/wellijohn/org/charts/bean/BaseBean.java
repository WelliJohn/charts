package wellijohn.org.charts.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: JiangWeiwei
 * @time: 2017/12/21-9:53
 * @email:
 * @desc:
 */
public class BaseBean implements Parcelable {
    private String name;

    private double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.price);
    }

    public BaseBean() {
    }



    public static final Parcelable.Creator<BaseBean> CREATOR = new Parcelable.Creator<BaseBean>() {
        @Override
        public BaseBean createFromParcel(Parcel source) {
            ChildBean childBean = new ChildBean();
            childBean.setName(source.readString());
            childBean.setPrice(source.readDouble());
            return childBean;
        }

        @Override
        public BaseBean[] newArray(int size) {
            return new ChildBean[size];
        }
    };
}
