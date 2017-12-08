package wellijohn.org.varchart.utils;

import android.util.Log;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/21-14:53
 * @email:
 * @desc: 获取比最大double除以等分的个数的最近整数的Integer
 */
public class DoubleUtils {
    public static final String TAG = "DoubleUtils";

    public static double getLargerInterger(double paramMaxDouble, int paramDividerN) {
        if (paramMaxDouble > 1) {
            double perDivDouble = paramMaxDouble / paramDividerN;
            String perDivString = String.valueOf(perDivDouble);

            int firstNumber = Integer.valueOf(perDivString.substring(0, 1))+1;
            int intDigits = perDivString.indexOf(".");
            if (intDigits == -1) {
                intDigits = perDivString.length();
            }
            Log.d(TAG, "intDigits: " + intDigits + "," + "firstNumber: " + firstNumber);

            if (intDigits == 1) {
                return 10;
            }
            return firstNumber * Math.pow(10, intDigits - 1);
        } else {
            return getLargerInterger(paramMaxDouble * 100, paramDividerN) / 100;

        }

    }
}
