package com.jaywei.verticalseekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Tony on 2017/8/25.
 */

public class VerticalSeekBar extends View
{
	private static final String TAG = VerticalSeekBar.class.getSimpleName();
	private int startColor = Color.GRAY;
	private int middleColor = Color.GRAY;
	private int endColor = Color.GRAY;
	private int thumbColor = Color.BLACK;
	private int thumbBorderColor = Color.TRANSPARENT;
	private int colorArray[] = { startColor, middleColor, endColor };
	private float x, y;
	private float mRadius;
	private float progress;
	private float maxCount = 100f;
	private float sLeft, sTop, sRight, sBottom;
	private float sWidth, sHeight;
	private LinearGradient linearGradient;
	private Paint paint = new Paint();
	protected OnSlideChangeListener onSlideChangeListener;

	private static final int DEFAULT_CIRCLE_RADIUS = 0;
	private static final int DEFAULT_CIRCLE_COLOR = Color.GRAY;
	private static final int vertical = 0;
	private static final int horizontal = 1;

	private int circle_radius = DEFAULT_CIRCLE_RADIUS;

	private int seekbar_orientation = vertical;
	private int vertical_color = Color.GRAY;
	private int image_background = 0;
	private boolean dragable = true;

	public VerticalSeekBar(Context context)
	{
		this(context, null);
	}

	public VerticalSeekBar(Context context, AttributeSet attrs)
	{
		this(context, attrs,0);
	}

	public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VerticalSeekBar, defStyle, 0);

		circle_radius = a.getDimensionPixelSize(R.styleable.VerticalSeekBar_circle_radius, DEFAULT_CIRCLE_RADIUS);
		thumbColor = a.getColor(R.styleable.VerticalSeekBar_circle_color, DEFAULT_CIRCLE_COLOR);
		dragable = a.getBoolean(R.styleable.VerticalSeekBar_dragable, true);
		seekbar_orientation = a.getInt(R.styleable.VerticalSeekBar_seekbar_orientation, vertical);
		vertical_color = a.getColor(R.styleable.VerticalSeekBar_vertical_color, Color.GRAY);
		image_background = a.getResourceId(R.styleable.VerticalSeekBar_image_background, 0);
		a.recycle();
		Log.e("seekbar_orientation>>>",seekbar_orientation+"");
		setCircle_color(thumbColor);
		setVertical_color(vertical_color);
	}

	public void setCircle_color(int circle_color)
	{
		this.thumbColor = circle_color;
		invalidate();
	}

	public void setVertical_color(int vertical_color)
	{
		this.vertical_color = vertical_color;
		this.startColor = vertical_color;
		this.middleColor = vertical_color;
		this.endColor = vertical_color;
		colorArray[0] = startColor;
		colorArray[1] = middleColor;
		colorArray[2] = endColor;
		invalidate();
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
	}

	public void setColor(int startColor, int middleColor, int endColor, int thumbColor, int thumbBorderColor)
	{
		this.startColor = startColor;
		this.middleColor = middleColor;
		this.endColor = endColor;
		this.thumbColor = thumbColor;
		this.thumbBorderColor = thumbBorderColor;
		colorArray[0] = startColor;
		colorArray[1] = middleColor;
		colorArray[2] = endColor;
		invalidate();
	}

	public void setColor(int verticalColor, int thumbColor, int thumbBorderColor)
	{
		this.startColor = verticalColor;
		this.middleColor = verticalColor;
		this.endColor = verticalColor;
		this.thumbColor = thumbColor;
		this.thumbBorderColor = thumbBorderColor;
		colorArray[0] = startColor;
		colorArray[1] = middleColor;
		colorArray[2] = endColor;
		invalidate();
	}

	public void setColor(int verticalColor, int thumbColor)
	{
		this.startColor = verticalColor;
		this.middleColor = verticalColor;
		this.endColor = verticalColor;
		this.thumbColor = thumbColor;
		colorArray[0] = startColor;
		colorArray[1] = middleColor;
		colorArray[2] = endColor;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		Log.e("onDraw>>>",seekbar_orientation+"onDraw");
		super.onDraw(canvas);
		int h = getMeasuredHeight();
		int w = getMeasuredWidth();

		if (seekbar_orientation == vertical)
		{
			// 竖
			if (circle_radius == 0)
			{
				mRadius = (float) w / 2;
			}
			else
			{
				mRadius = circle_radius;
			}
			sLeft = w * 0.25f; // 背景左边缘坐标
			sRight = w * 0.75f;// 背景右边缘坐标
			sTop = 0;
			sBottom = h;
			sWidth = sRight - sLeft; // 背景宽度
			sHeight = sBottom - sTop; // 背景高度
			x = (float) w / 2;// 圆心的x坐标
			y = (float) (1 - 0.01 * progress) * sHeight;// 圆心y坐标
		}
		else
		{
			if (circle_radius == 0)
			{
				mRadius = (float) h / 2;
			}
			else
			{
				mRadius = circle_radius;
			}

			sLeft = 0; // 背景左边缘坐标
			sRight = w;// 背景右边缘坐标
			sTop = h * 0.25f;
			sBottom = h * 0.75f;
			sWidth = sRight - sLeft; // 背景宽度
			sHeight = sBottom - sTop; // 背景高度
			x = (float) (0.01 * progress) * sWidth;// 圆心的x坐标
			y = (float) h / 2;// 圆心y坐标
		}

		drawBackground(canvas);
		drawCircle(canvas);
		paint.reset();
	}

	private void drawBackground(Canvas canvas)
	{
		RectF rectBlackBg = new RectF(sLeft, sTop, sRight, sBottom);
		linearGradient = new LinearGradient(sLeft, sTop, sWidth, sHeight, colorArray, null, Shader.TileMode.MIRROR);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		// 设置渲染器
		paint.setShader(linearGradient);
		if(seekbar_orientation==vertical){
			canvas.drawRoundRect(rectBlackBg, sWidth / 2, sWidth / 2, paint);
		}else{
			canvas.drawRoundRect(rectBlackBg, sHeight / 2, sHeight / 2, paint);
		}
	}

	private void drawCircle(Canvas canvas)
	{
		Paint thumbPaint = new Paint();

		if(seekbar_orientation==vertical){
			y = y < mRadius ? mRadius : y;// 判断thumb边界
			y = y > sHeight - mRadius ? sHeight - mRadius : y;
			thumbPaint.setAntiAlias(true);
			thumbPaint.setStyle(Paint.Style.FILL);
			thumbPaint.setColor(thumbColor);
//			if(image_background == 0){
//				canvas.drawCircle(x-x*3/4, y+x, mRadius, thumbPaint);
//			}else{
//				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image_background, null);
//				canvas.drawBitmap(bitmap,x, y, thumbPaint);
//			}
			canvas.drawCircle(x, y, mRadius, thumbPaint);
			thumbPaint.setStyle(Paint.Style.STROKE);
			thumbPaint.setColor(thumbBorderColor);
			thumbPaint.setStrokeWidth(2);
			canvas.drawCircle(x, y, mRadius, thumbPaint);
		}else {
			x = x < mRadius ? mRadius : x;// 判断thumb边界
			x = x > sWidth - mRadius ? sWidth - mRadius : x;
			thumbPaint.setAntiAlias(true);
			thumbPaint.setStyle(Paint.Style.FILL);
			thumbPaint.setColor(thumbColor);

//			if(image_background == 0){
//				canvas.drawCircle(x, y, mRadius, thumbPaint);
//			}else{
//				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image_background, null);
//				canvas.drawBitmap(bitmap,0, 0, thumbPaint);
//			}
			canvas.drawCircle(x, y, mRadius, thumbPaint);
			thumbPaint.setStyle(Paint.Style.STROKE);
			thumbPaint.setColor(thumbBorderColor);
			thumbPaint.setStrokeWidth(2);
			canvas.drawCircle(x, y, mRadius, thumbPaint);
		}

		if(image_background == 0){

		}else{
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image_background, null);
			canvas.drawBitmap(bitmap,0, 0, thumbPaint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (!dragable)
		{
			return true;
		}
		if(seekbar_orientation == vertical){
			this.y = event.getY();
			progress = (sHeight - y) / sHeight * 100;
		}else{
			this.x = event.getX();
			progress = (x) / sWidth * 100;
		}

		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP:
				if (onSlideChangeListener != null)
				{
					onSlideChangeListener.onSlideStopTouch(this, progress);
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if (onSlideChangeListener != null)
				{
					onSlideChangeListener.OnSlideChangeListener(this, progress);
				}
				setProgress(progress);
				this.invalidate();
				break;
		}

		return true;
	}

	public interface OnSlideChangeListener
	{
		void OnSlideChangeListener(View view, float progress);

		void onSlideStopTouch(View view, float progress);
	}

	public void setOnSlideChangeListener(OnSlideChangeListener onStateChangeListener)
	{
		this.onSlideChangeListener = onStateChangeListener;
	}

	public void setProgress(float progress)
	{
		this.progress = progress;
		invalidate();
	}
}
