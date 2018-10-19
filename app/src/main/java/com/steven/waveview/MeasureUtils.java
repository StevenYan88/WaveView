package com.steven.waveview;

import static android.view.View.MeasureSpec;

/**
 * Description:
 * Data：4/19/2018-1:53 PM
 *
 * @author yanzhiwen
 */
public class MeasureUtils {
    /**
     * 用于View的测量
     *
     * @param measureSpec 测量模式和大小
     * @param defaultSize 默认的大小
     * @return
     */
    public static int measureView(int measureSpec, int defaultSize) {
        int measureSize;
        //获取用户指定的大小以及模式
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        //根据模式去返回大小
        if (mode == MeasureSpec.EXACTLY) {
            //精确模式（指定大小以及match_parent）直接返回指定的大小
            measureSize = size;
        } else {
            //UNSPECIFIED模式、AT_MOST模式（wrap_content）的话需要提供默认的大小
            measureSize = defaultSize;
            if (mode == MeasureSpec.AT_MOST) {
                //AT_MOST（wrap_content）模式下，需要取测量值与默认值的最小值
                measureSize = Math.min(measureSize, size);
            }
        }
        return measureSize;
    }

    private int measureSize(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 300;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;

    }

}
