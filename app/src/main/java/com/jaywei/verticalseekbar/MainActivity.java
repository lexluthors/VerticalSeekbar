package com.jaywei.verticalseekbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaywei.PureVerticalSeekBar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, PureVerticalSeekBar.OnSlideChangeListener
{
	private TextView tvCurrentTemper, tvCurrentBrightness, tvMainTitle;
	private ImageView ivBack;
	private PureVerticalSeekBar mPureVerticalSeekBar;
	private PureVerticalSeekBar mPureVerticalSeekBar_circle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initEvents();
		initData();
	}

	private void initViews()
	{

		tvCurrentTemper = (TextView) findViewById(R.id.tv_current_temper);
		tvCurrentBrightness = (TextView) findViewById(R.id.tv_current_brightness);
		mPureVerticalSeekBar = (PureVerticalSeekBar) findViewById(R.id.vpb_inner_3);
		mPureVerticalSeekBar_circle = (PureVerticalSeekBar) findViewById(R.id.seekbar_circle);
		tvCurrentTemper.setOnClickListener(this);
		tvCurrentBrightness.setOnClickListener(this);
	}

	private void initEvents()
	{
		// ivBack.setOnClickListener(this);
		mPureVerticalSeekBar.setOnSlideChangeListener(this);
		mPureVerticalSeekBar_circle.setOnSlideChangeListener(this);
	}

	private void initData()
	{
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.tv_current_brightness:
				mPureVerticalSeekBar.setColor(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.TRANSPARENT);//设置渐变颜色
				mPureVerticalSeekBar.setVertical_color(Color.RED);//设置滑竿的颜色，单一颜色
				mPureVerticalSeekBar.setDragable(true);//设置是否可以拖动

				mPureVerticalSeekBar_circle.setColor(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.TRANSPARENT);//设置渐变颜色
//				mPureVerticalSeekBar_circle.setVertical_color(Color.YELLOW);//设置滑竿的颜色，单一颜色
				mPureVerticalSeekBar_circle.setDragable(true);//设置是否可以拖动
				break;
			case R.id.tv_current_temper:
				mPureVerticalSeekBar.setVertical_color(Color.GRAY, Color.BLUE);//设置滑竿的颜色，上下两个颜色
				mPureVerticalSeekBar.setDragable(true);//设置是否可以拖动
				mPureVerticalSeekBar.setCircle_color(Color.BLUE);//设置圆形滑块颜色

				mPureVerticalSeekBar_circle.setVertical_color(Color.YELLOW, Color.RED);//设置滑竿的颜色，上下两个颜色
				mPureVerticalSeekBar_circle.setDragable(true);//设置是否可以拖动

				break;
		}

	}

	@Override
	public void OnSlideChangeListener(View view, float progress)
	{
		Log.e("OnSlideChangeListener",""+progress);
		switch (view.getId()){
			case R.id.vpb_inner_3:
				break;
			case R.id.seekbar_circle:
				break;
		}
	}

	@Override
	public void onSlideStopTouch(View view, float progress)
	{
		Log.e("onSlideStopTouch>>>>",""+progress);
		switch (view.getId()){
			case R.id.vpb_inner_3:
				break;
			case R.id.seekbar_circle:
				break;
		}
	}
}
