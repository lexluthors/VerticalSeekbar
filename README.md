# PureVerticalSeekBar

#**一个好用的竖直方向的seekbar，可以设置颜色，滑块背景，**

![](https://github.com/lexluthors/VerticalSeekbar/blob/master/app/testdemo2.gif)


   圆形滑块布局：

    <news.jaywei.com.pureverticalseekbar.PureVerticalSeekBar
            android:id="@+id/seekbar_circle"
            android:layout_width="13dp"
            android:layout_height="300dp"
            android:layout_marginLeft="150dp"
            android:layout_marginTop="60dp"
            app:circle_color="@color/colorAccent"
            app:dragable="true"
            />

自定义背景滑块布局：

    <news.jaywei.com.pureverticalseekbar.PureVerticalSeekBar
            android:id="@+id/vpb_inner_3"
            android:layout_width="13dp"
            android:layout_height="300dp"
            android:layout_marginLeft="100dp"
            android:layout_marginTop="60dp"
            app:dragable="true"
            app:image_background="@mipmap/thumb2"/>


用法：

    mPureVerticalSeekBar.setColor(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.TRANSPARENT);//可以设置渐变颜色
    				mPureVerticalSeekBar.setVertical_color(Color.RED);//可以设置滑竿的颜色，单一颜色
    				mPureVerticalSeekBar.setDragable(true);//设置是否可以拖动
    				mPureVerticalSeekBar_circle.setVertical_color(Color.YELLOW, Color.RED);//设置滑竿的颜色，上下两个颜色
    				mPureVerticalSeekBar.setCircle_color(Color.BLUE);//设置圆形滑块颜色

**注意：
默认不设置app:image_background属性；默认滑块是圆形，半径最大是控件宽的一半。**


如果有帮到你，能否赏个star。