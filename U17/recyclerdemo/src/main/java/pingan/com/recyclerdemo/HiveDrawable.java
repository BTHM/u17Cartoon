package pingan.com.recyclerdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * @author sanshu
 * @data 2017/3/1 22:24
 * @ToDo ${TODO}
 */

public class HiveDrawable extends Drawable {


        // 用于记录边界信息的Rect
        Rect mRect = new Rect();
        Paint        mPaint;
        Path         mPath ;
        BitmapShader mShader;
        Bitmap       mBitmap ;

        public HiveDrawable() {
            this(null) ;
        }

        public HiveDrawable(Bitmap bitmap) {
            init();
            setBitmap(bitmap);
        }

        private void init() {
            initPaint() ;
            initPath() ;
        }

        private void ensurePaint(){
            if (mPaint == null) {
                mPaint = new Paint() ;
            }
        }

        private void ensurePath(){
            if (mPath == null) {
                mPath = new Path() ;
            }
        }

        private void initPaint() {
            ensurePaint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(3f);
        }

        public Bitmap getBitmap() {
            return mBitmap;
        }

        // 设置Bitmap的时候初始化shader，并设置给paint
        public void setBitmap(Bitmap bitmap) {
            this.mBitmap = bitmap;
            if (bitmap == null) {
                mShader =null ;
            } else {
                mShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) ;
                mPaint.setShader(mShader) ;
            }
        }

        // 初始化好Path要走的路径
        private void initPath() {
            ensurePath();
            float l = (float) (mRect.width() / 2);
            float h = (float) (Math.sqrt(3)*l);
            float top = (mRect.height() - h) / 2  ;
            Log.d("HiveDrawable","mRect.width()="+mRect.width()+"mRect.height()="+mRect.height()+"h="+h);

            mPath.reset();
            mPath.moveTo(l/2,top);
            mPath.lineTo(0,h/2+top);
            mPath.lineTo(l/2,h+top);
            mPath.lineTo((float) (l*1.5),h+top);
            mPath.lineTo(2*l,h/2+top);
            mPath.lineTo((float) (l*1.5),top);
            mPath.lineTo(l/2,top);
            mPath.close();
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawPath(mPath,mPaint);
        }

        @Override
        public void setAlpha(int alpha) {
            if (mPaint != null) {
                mPaint.setAlpha(alpha);
            }
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            if (mPaint != null) {
                mPaint.setColorFilter(colorFilter) ;
            }
        }

        @Override
        public int getOpacity() {
            return 0 ;
        }

        // 设置边界信息
        @Override
        public void setBounds(int left, int top, int right, int bottom) {
            super.setBounds(left, top, right, bottom);
            mRect.set(left, top, right, bottom);
            initPath();
            Log.d("HiveDrawable","left="+left+"top="+top+"right="+right+"bottom="+bottom);
        }

        @Override
        public int getIntrinsicWidth() {
            if (mBitmap != null) {
                return mBitmap.getWidth();
            } else {
                return super.getIntrinsicWidth() ;
            }
        }

        @Override
        public int getIntrinsicHeight() {
            if (mBitmap != null) {
                return mBitmap.getHeight() ;
            }
            return super.getIntrinsicHeight();
        }

}
