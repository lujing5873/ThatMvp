package com.nhcz500.base.weiget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.RectShape;

/**
 * 正三角
 */
public class TriangleUpShape extends RectShape {
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(buildConvexPath(), paint);
    }

    @Override
    public void getOutline(Outline outline) {
        outline.setConvexPath(buildConvexPath());
    }
    private Path buildConvexPath() {
        Path path = new Path();
        float width=  rect().width();
        float height= rect().height();
        path.moveTo(width/2,0);
        path.lineTo(0, height);
        path.lineTo(width, height);
        return path;
    }
}
