package by.bsuir.verlet.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

import by.bsuir.verlet.helper.ViewAnimationHelper;
import by.bsuir.verlet.model.Triangle;

/**
 * The type Custom view.
 */
public class CustomView extends View implements ViewAnimationHelper {

    private static final int TRIANGLE_SIZE = 200;
    private static final double ROTATION_ANGLE = 5;
    private static final double DT = 0.1;
    private int[] xVerts = new int[3];
    private int[] yVerts = new int[3];
    private Paint trianglePaint;
    private Path trianglePath;
    private Triangle triangle;
    private ValueAnimator animator;
    private double t = 0;
    private double angle = 0;

    /**
     * Instantiates a new Custom view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        trianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        trianglePath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void start(Triangle triangle) {
        this.triangle = triangle;
        AtomicInteger prevX = new AtomicInteger(triangle.getX());
        AtomicInteger prevY = new AtomicInteger(triangle.getY());
        calculateVertexes();
        stopAnimation();
        animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(100000);
        animator.addUpdateListener(animation -> {
            trianglePath = new Path();
            t += DT;
            angle += ROTATION_ANGLE;
            int tempX = triangle.getX();
            int tempY = triangle.getY();
            int x = (int) (2 * triangle.getX() - prevX.get() +
                    triangle.getAX() * t * t + 0.5);
            int y = (int) (2 * triangle.getY() - prevY.get() +
                    triangle.getAY() * t * t + 0.5);

            prevX.set(tempX);
            triangle.setX(x);
            prevY.set(tempY);
            triangle.setY(y);

            calculateVertexes();
            calculateRotation(angle);

            trianglePath.moveTo(xVerts[0], yVerts[0]);
            for (int i = 0; i < 3; i++) {
                trianglePath.lineTo(xVerts[i], yVerts[i]);
            }

            trianglePath.close();
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(trianglePath, trianglePaint);
    }

    private void calculateVertexes() {
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    xVerts[i] = triangle.getX() - TRIANGLE_SIZE / 2;
                    yVerts[i] = triangle.getY() - TRIANGLE_SIZE / 2;
                    break;
                case 1:
                    xVerts[i] = triangle.getX() + TRIANGLE_SIZE / 2;
                    yVerts[i] = triangle.getY() - TRIANGLE_SIZE / 2;
                    break;
                case 2:
                    xVerts[i] = triangle.getX() + TRIANGLE_SIZE / 2;
                    yVerts[i] = triangle.getY() + TRIANGLE_SIZE / 2;
                    break;
            }
            if (xVerts[2] > getWidth() || yVerts[2] > getHeight() ||
                    yVerts[1] < 0) {
                stopAnimation();
                invalidate();
            }
        }
    }


    private void calculateRotation(double angle) {
        double cosA = Math.cos(angle * Math.PI / 180);
        double sinA = Math.sin(angle * Math.PI / 180);
        for (int i = 0; i < 3; i++) {
            int x = (int) (triangle.getX() + (xVerts[i] - triangle.getX()) *
                    cosA - (yVerts[i] - triangle.getY()) * sinA + 0.5);
            int y = (int) (triangle.getY() + (yVerts[i] - triangle.getY()) *
                    cosA + (xVerts[i] - triangle.getX()) * sinA + 0.5);
            xVerts[i] = x;
            yVerts[i] = y;
        }
    }

    private void stopAnimation() {
        if (animator != null && animator.isStarted()) {
            animator.cancel();
            t = 0;
            angle = 0;
        }
    }
}
