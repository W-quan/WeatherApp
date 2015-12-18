package com.wzq.weatherapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MaxMinTempView extends View {
    private  int viewWidth;
    private  int viewHight;

    private int maxT = 35;
    private int minT = 25;
    private int aT = 0;
    private int iT = 0;

    private Paint paintA;
    private Paint paintB;
    private Paint paintC;

    private int yStart = 150;
    private int yMin = 150;
    private int yMax = 150;

    public MaxMinTempView(Context context) {
        super(context);
    }

    public MaxMinTempView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaxMinTempView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintA = new Paint();
        paintA.setAntiAlias(true);
        paintA.setStrokeWidth(3);
        paintA.setTextSize(25);

        paintB = new Paint();
        paintB.setAntiAlias(true);
        paintB.setColor(0xff000000);
        paintB.setStrokeWidth((float) 0.5);

        paintC = new Paint();
        paintC.setAntiAlias(true);
        paintC.setStrokeWidth(50);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0, viewHight/2, viewWidth, viewHight/2, paintA);
//        canvas.drawLine(20, 150, 320, 150, paintA);
        canvas.drawLine(viewWidth/2 - 50, 40, viewWidth/2 - 50, viewHight - 40, paintB);
        canvas.drawLine(viewWidth/2 + 50, 40, viewWidth/2 + 50, viewHight - 40, paintB);

        canvas.drawLine(viewWidth/2 - 50 - 25, yMin, viewWidth/2 - 50 - 25, viewHight/2, paintC);
        canvas.drawLine(viewWidth/2 + 50 + 25, yMax, viewWidth/2 + 50 + 25, viewHight/2, paintC);

        canvas.drawText("Min" + iT + "°", viewWidth/2 - 110, yMin - 10, paintA);
        canvas.drawText("Max" + aT + "°", viewWidth/2 + 50, yMax - 10, paintA);

        if (minT > 0) {
            if (yMin > yStart - minT * 2.5) {
                yMin -= 2;
            }
            if (iT < minT) {
                iT++;
            }
        } else {
            if (yMin < yStart + minT * 2.5) {
                yMin += 2;
            }
            if (iT > minT) {
                iT--;
            }
        }

        if (maxT > 0) {
            if (yMax > yStart - maxT * 2.5) {
                yMax -= 2;
            }
            if (aT < maxT) {
                aT++;
            }
        } else {
            if (yMax < yStart + maxT * 2.5) {
                yMax += 2;
            }
            if (aT > maxT) {
                aT--;
            }
        }

        if (yMin > yStart - minT * 2.5 || yMin < yStart + minT * 2.5
                || yMax > yStart - maxT * 2.5 || yMax < yStart + maxT * 2.5
                || iT < minT || iT > minT || aT < maxT || aT > maxT) {
            invalidate();
        }
    }

    public void setTemp(String max, String min) {
        String[] a = max.split("℃");
        this.maxT = Integer.parseInt(a[0]);
        String[] b = min.split("℃");
        this.minT = Integer.parseInt(b[0]);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        viewWidth = measureSize(widthMeasureSpec);
        viewHight = measureSize(heightMeasureSpec);
    }

    private int measureSize(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // Default size if no limits are specified.
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            // Calculate the ideal size of your
            // control within this maximum size.
            // If your control fills the available
            // space return the outer bound.
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            // If your control can fit within these bounds return that
            // value.
            result = specSize;
        }
        return result;
    }
}
