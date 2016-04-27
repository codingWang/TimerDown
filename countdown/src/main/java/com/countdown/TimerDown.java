package com.countdown;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by 杜伟 on 2016/4/26.
 */
public class TimerDown extends View {

    private int[][][] digit = {
            {{0, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1},//0
                    {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 0, 1, 1, 0}, {0, 0, 1, 1, 1, 0, 0}},
            {{0, 0, 0, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0},//1
                    {0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 0}},
            {{0, 1, 1, 1, 1, 1, 0}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 0, 0},//2
                    {0, 0, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1, 1}},
            {{0, 1, 1, 1, 1, 1, 0}, {1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 0},//3
                    {0, 0, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 1, 1, 0}},
            {{0, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 0}, {0, 1, 1, 0, 1, 1, 0}, {1, 1, 0, 0, 1, 1, 0},//4
                    {1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 0, 1, 1, 0}},
            {{1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 0},//5
                    {0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 1, 1, 0}},
            {{0, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 0}, {0, 0, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 0, 0, 0}, {1, 1, 0, 1, 1, 1, 0},//6
                    {1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 1, 1, 0}},
            {{0, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 0, 0},//7
                    {0, 0, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 0, 0, 0}},
            {{0, 1, 1, 1, 1, 1, 0}, {1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 1, 1, 0},//8
                    {0, 1, 1, 1, 1, 1, 0}, {1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 1, 1, 0}},
            {{0, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1},//9
                    {0, 1, 1, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 0, 0}, {0, 0, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0}},

    };

    private int[][] dots = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0},
            {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};

    private int[] colors = {0xff33b5e5, 0xff0099cc, 0xffaa66cc, 0xff9933cc, 0xff99cc00,
            0xff669900, 0xffffbb33, 0xffff8800, 0xffff4444, 0xcc0000};

    private Paint mPaint;
    private Paint colorPaint;
    private int color = 0xff006699;
    private static final int RADIUS = 8;

    private long endTime;//结束时间
    private long currentTime;//系统当前时间,单位：秒
    //当前时间+倒计时的秒数

    private int hour;
    private int minutes;
    private int seconds;


    private int param_years =2016;
    private int param_month = 3;
    private int param_day = 27;
    private int param_hour = 21;
    private int param_minutes = 0;
    private int param_seconds = 0;

    private List<Ball> mBalls = new ArrayList<>();
    private Random random = new Random();

    public TimerDown(Context context) {
        this(context, null);
    }

    public TimerDown(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerDown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(color);

        colorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        colorPaint.setStyle(Paint.Style.FILL);
        colorPaint.setStrokeWidth(8);

        Calendar c = Calendar.getInstance();
        c.set(param_years, param_month, param_day, param_hour, param_minutes, param_seconds);

        endTime = c.getTimeInMillis() / 1000;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = this.getPaddingLeft() + this.getPaddingRight();
        int viewHeight = this.getPaddingTop() + this.getPaddingBottom();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {//指定了确定的尺寸：100dp,match_parent
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {//wrap_content类型
            width = Math.min(viewWidth, widthSize);
        } else {
            width = viewWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(viewHeight, heightSize);
        } else {
            height = viewHeight;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        update();
        render(canvas);

    }

    private void render(Canvas canvas) {

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();
        final int paddingTop = getPaddingTop();
        int width = getWidth()-paddingLeft-paddingRight;
        int height = getHeight()-paddingTop-paddingBottom;

        long time = getTimeDistance();
        hour = (int) (time / 3600);
        minutes = (int) (time - hour * 3600) / 60;
        seconds = (int) time % 60;

        drawDigit(canvas, hour / 10, paddingLeft+0, 120, RADIUS);//十位-小时
        drawDigit(canvas, hour % 10, paddingLeft+15 * (RADIUS + 1), 120, RADIUS);//个位-小时
        drawDots(canvas, 30 * (RADIUS + 1), 120, 8);//冒号

        drawDigit(canvas, minutes / 10, paddingLeft+39 * (RADIUS + 1), 120, RADIUS);//十位-小时
        drawDigit(canvas, minutes % 10, paddingLeft+54 * (RADIUS + 1), 120, RADIUS);//个位-小时
        drawDots(canvas, 69 * (RADIUS + 1), 120, RADIUS);//冒号

        drawDigit(canvas, seconds / 10, paddingLeft+78 * (RADIUS + 1), 120, RADIUS);//十位-小时
        drawDigit(canvas, seconds % 10, paddingLeft+93 * (RADIUS + 1), 120, RADIUS);//个位-小时

        for (int i = 0; i < mBalls.size(); i++) {
            Ball iBall = mBalls.get(i);
            colorPaint.reset();
            colorPaint.setColor(iBall.getColor());
            canvas.drawCircle(iBall.getX(), iBall.getY(), RADIUS, colorPaint);
        }
    }

    private void update() {
        long nextTimeSecond = getTimeDistance();

        int nextHour = (int) nextTimeSecond / 3600;
        int nextMin = (int) (nextTimeSecond - nextHour * 3600) / 60;
        int nextSec = (int) nextTimeSecond % 60;


        int curHour = (int) currentTime / 3600;
        int curMin = (int) (currentTime - curHour * 3600) / 60;
        int curSec = (int) currentTime % 60;

        if (nextTimeSecond != currentTime) {
            currentTime = nextTimeSecond;


            //一下if语句用于判断时钟每一位是否有变动，如果有则添加彩色小球
            if (curHour / 10 != nextHour / 10) {
                addBalls(120, 120, curHour / 10);
            }
            if ((curHour % 10) != (nextHour % 10)) {
                addBalls(15 * (RADIUS + 1), 120, curHour % 10);
            }
            if ((curMin / 10) != (nextMin / 10)) {
                addBalls(39 * (RADIUS + 1), 120, curMin / 10);
            }
            if ((curMin % 10) != (nextMin % 10)) {
                addBalls(54 * (RADIUS + 1), 120, curMin % 10);
            }
            if ((curSec / 10) != (nextSec / 10)) {
                addBalls(78 * (RADIUS + 1), 120, curSec / 10);
            }
            if ((curSec % 10) != (nextSec % 10)) {
                addBalls(93 * (RADIUS + 1), 120, curSec % 10);
            }
        }
        updateBalls();//更新每个小球的参数。如速度和位置
    }


    /**draw the number of the time*/
    private void drawDigit(Canvas canvas, int num, int x, int y, int r) {
        for (int i = 0; i < digit.length; i++) {
            for (int j = 0; j < digit[num][i].length; j++) {
                if (digit[num][i][j] == 1) {
                    canvas.drawCircle(x + j * 2 * (r + 1) + (r + 1), y + i * 2 * (r + 1) + (r + 1), r, mPaint);
                    invalidate();
                }
            }
        }
    }


    /**draw the point between the number*/
    private void drawDots(Canvas canvas, int x, int y, int r) {
        for (int i = 0; i < dots.length; i++) {
            for (int j = 0; j < dots[i].length; j++) {
                if (dots[i][j] == 1) {
                    canvas.drawCircle(x + j * 2 * (r + 1) + (r + 1), y + i * 2 * (r + 1) + (r + 1), r, mPaint);
                    invalidate();
                }
            }
        }
    }


    private void addBalls(int x, int y, int num) {
        for (int i = 0; i < digit.length; i++) {
            for (int j = 0; j < digit[num][i].length; j++) {
                if (digit[num][i][j] == 1) {
                    Ball ball = new Ball();
                    ball.setX(x + j * 2 * (RADIUS + 1) + (RADIUS + 1));
                    ball.setY(y + i * 2 * (RADIUS + 1) + (RADIUS + 1));
                    ball.setG(1.5 + Math.random());
                    ball.setVx(Math.pow(-1, Math.ceil(Math.random() * 1000)) * 4);
                    ball.setVy(-5);
                    ball.setColor(colors[random.nextInt(10)]);
                    mBalls.add(ball);
                }

            }
        }

    }


    /**update the ball's speed,position...infomation*/
    private void updateBalls() {
        for (int i = 0; i < mBalls.size(); i++) {
            Ball iBall = mBalls.get(i);
            iBall.x += iBall.vx;
            iBall.y += iBall.vy;
            iBall.vy += iBall.g;

            if (iBall.y >= getHeight() - RADIUS) {
                iBall.y = getHeight() - RADIUS;
                iBall.vy = -iBall.vy * 0.65;
            }
        }
        //remove the balls out of the screen
        int count = 0;//在屏幕里面的小球数量
        for (int i = 0; i < mBalls.size(); i++) {
            Ball iBall = mBalls.get(i);
            if (iBall.x + RADIUS > 0 && iBall.x - RADIUS < getWidth()) {
                mBalls.set(count,iBall);
                count++;
            }
        }
        Log.i("TAG","=="+count);
        for (int j = count; j < mBalls.size(); j++) {
            mBalls.remove(j);
        }

    }


    private long getTimeDistance() {
        long currentTime = System.currentTimeMillis() / 1000;
        long time = endTime - currentTime;
        return time >= 0 ? time : 0;
    }

    class Ball {
        private int x;
        private int y;
        private double vx;
        private double vy;
        private double g;
        private int color;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public double getVx() {
            return vx;
        }

        public void setVx(double vx) {
            this.vx = vx;
        }

        public double getVy() {
            return vy;
        }

        public void setVy(double vy) {
            this.vy = vy;
        }

        public double getG() {
            return g;
        }

        public void setG(double g) {
            this.g = g;
        }
    }
}
