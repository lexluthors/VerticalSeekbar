package com.jaywei.verticalseekbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, VerticalSeekBar.OnSlideChangeListener
{
	private TextView tvCurrentTemper, tvCurrentBrightness, tvMainTitle;
	private ImageView ivBack;
	private VerticalSeekBar vpbInnerTemper;
	private VerticalSeekBar vpbBrightness;

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
		vpbInnerTemper = (VerticalSeekBar) findViewById(R.id.vpb_inner_temper);
		vpbBrightness = (VerticalSeekBar) findViewById(R.id.vpb_brightness);
		// vpbInnerTemper.setColor(Color.RED,Color.BLUE, Color.TRANSPARENT);
		// vpbBrightness.setColor(Color.BLUE,Color.BLUE, Color.TRANSPARENT);
		tvCurrentTemper.setOnClickListener(this);
		tvCurrentBrightness.setOnClickListener(this);
		// vpbInnerTemper.setColor(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE,
		// Color.TRANSPARENT);
		// vpbBrightness.setColor(Color.BLUE, Color.WHITE, Color.YELLOW, Color.BLUE,
		// Color.TRANSPARENT);
	}

	private void initEvents()
	{
		// ivBack.setOnClickListener(this);
		vpbInnerTemper.setOnSlideChangeListener(this);
		vpbBrightness.setOnSlideChangeListener(this);
	}

	private void initData()
	{
		vpbInnerTemper.setProgress(50);
		vpbBrightness.setProgress(70);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.tv_current_brightness:
				vpbInnerTemper.setColor(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.TRANSPARENT);
				vpbBrightness.setColor(Color.BLUE, Color.WHITE, Color.YELLOW, Color.BLUE, Color.TRANSPARENT);
				break;
			case R.id.tv_current_temper:
				vpbInnerTemper.setColor(Color.RED, Color.BLUE, Color.TRANSPARENT);
				vpbBrightness.setColor(Color.BLUE, Color.BLUE, Color.TRANSPARENT);
				break;
		}

	}

	@Override
	public void OnSlideChangeListener(View view, float progress)
	{

	}

	@Override
	public void onSlideStopTouch(View view, float progress)
	{
		int viewId = view.getId();
		switch (viewId)
		{
			case R.id.vpb_inner_temper:
				if (progress < 0)
				{
					progress = 0;
				}
				if (progress > 100)
				{
					progress = 100;
				}
				Log.e("\"progress= \" + progress", "progress= " + progress);
				// Toast.makeText(MainActivity.this,"progress= " +
				// progress,Toast.LENGTH_SHORT).show();
				break;

			case R.id.vpb_brightness:
				if (progress < 0)
				{
					progress = 0;
				}
				if (progress > 100)
				{
					progress = 100;
				}
				Log.e("\"progress= \" + progress", "progress1111= " + progress);
				// Toast.makeText(MainActivity.this,"progress1= " +
				// progress,Toast.LENGTH_SHORT).show();
				break;
		}

	}
}
