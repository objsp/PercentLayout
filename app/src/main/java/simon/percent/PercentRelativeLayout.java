package simon.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016-05-11 0011.
 */
public class PercentRelativeLayout extends RelativeLayout {
    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    //测量自己
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 拿到自己宽高
        int widthHint = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightHint = View.MeasureSpec.getSize(heightMeasureSpec);
        for (int i = 0; i < this.getChildCount(); i++) { // 遍历所有子控件
            View child = this.getChildAt(i);
            //
            ViewGroup.LayoutParams params = child.getLayoutParams();
            float widthPercent = 0;
            float heightPercent = 0;
            if (params instanceof PercentRelativeLayout.LayoutParams) {
                // 拿到父控件宽高
                widthPercent = ((PercentRelativeLayout.LayoutParams) params).getWidthPercent();
                heightPercent = ((PercentRelativeLayout.LayoutParams) params).getHeightPercent();

            }
            if (widthPercent == 0 || heightPercent == 0) {
                continue;
            }
            // 比例公式得到百分比
            params.width = (int) (widthPercent * widthHint);
            params.height = (int) (heightPercent * heightHint);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        private float widthPercent;
        private float heightPercent;

        public float getWidthPercent() {
            return widthPercent;
        }

        public void setWidthPercent(float widthPercent) {
            this.widthPercent = widthPercent;
        }

        public float getHeightPercent() {
            return heightPercent;
        }

        public void setHeightPercent(float heightPercent) {
            this.heightPercent = heightPercent;
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.percentRelativeLayout);
            widthPercent = array.getFloat(R.styleable.percentRelativeLayout_layout_widthPercent, widthPercent);
            heightPercent = array.getFloat(R.styleable.percentRelativeLayout_layout_heightPercent, heightPercent);
            array.recycle();
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

    }

}
