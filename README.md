# CircleProgressBar
一个界面漂亮功能强大的圆形进度条，支持多种属性，如圆环的大小颜色，进度条的大小颜色，进度条的颜色支持渐变色处理，支持圆环中间显示三层文本，支持文本的大小，颜色设置，几乎完美的解决wrap_content，具备较强的自适应能力

![image](https://github.com/HuTianQi/CircleProgressBar/blob/master/CircleProgressBar.gif)
该控件支持以下功能：

1能够支持设置进度条各种属性，如圆环的大小，颜色，进度条的大小，颜色，进度条的颜色支持设置三种颜色来达到渐变色的效果。<br>
2圆形进度条的内部支持设置三层文本，即上层的标题，如上图的“您的等级超越全国”，中间层的进度值，如上图的“700”，下层的附带内容，如上图的“万的用户”<br>
3支持设置三层文本的大小与颜色，如上图标题与底部文本为黑色，中间文本为红色<br>
4支持进度条从任意位置开始显示，为何要支持该功能，是因为在不同的场合，进度条开始显示的位置一般是不同的，如在某些手机助手类下载App的应用中显示下载进度的时候都是从圆环的顶部开始，以顺时针为方向逐渐递增显示，本例的第三个小圆环即是模仿的该场合，但是因为截的动态图上传出错，只能上传几张图片，所以看的不是很清楚，而在某些计步器类的app中进度的绘制一般是从左下角开始显示，然后以顺时针为方向达到对称的位置，本例的最后一个大圆环即是模仿的该场合。<br>
5支持设置部分圆弧，而不是整个圆，如本例的最后一个大圆环的进度条显示效果，因为在某些场合是不需要绘制整个圆的，如在模拟汽车速度表盘的场合。<br>
6具备极强的自适应能力，即wrap_content参数要能够比较完美的适应用户输入的文本的长度。<br>

#如何使用
Add the following to your attrs.xml file (in res/values):<br>
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="CircleProgressBar">
        <!-- 设置圆环进度条的圆环背景属性，包括颜色与宽度 -->
        <attr name="circle_color" format="color"/>
        <attr name="circle_width" format="dimension"/>
        
        <!-- 设置圆环进度条的进度条属性，包括颜色与宽度，颜色可以设置3种从而实现一个渐变的效果 -->
        <attr name="progress_color1" format="color"/>
        <attr name="progress_color2" format="color"/>
        <attr name="progress_color3" format="color"/>
        <attr name="progress_width" format="dimension"/>
         
       
        <!-- 设置圆环进度条百分比是从低到高显示还是从高到低显示 ，默认从低到高，即从0到100 -->
        <attr name="set_high_to_low" format="boolean"/>
         
        <!-- 是否设置圆环进度条中间的内容属性，需要与下面的一组属性配合使用-->
        <attr name="is_set_top_title" format="boolean"/>
        <attr name="is_set_mid_content" format="boolean"/>
        <attr name="is_set_bottom_content" format="boolean"/>
        
        <!-- 设置圆环进度条中间的内容属性-->
        <attr name="top_title" format="string"/>
        <attr name="bottom_content" format="string"/>
        <attr name="current_progress" format="float"/>
        <attr name="max_progress" format="float"/>
        
        <attr name="top_title_color" format="color"/>
        <attr name="mid_progress_color" format="color"/>
        <attr name="bottom_content_color" format="color"/>
        <attr name="top_title_text_size" format="dimension"/>
        <attr name="bottom_content_text_size" format="dimension"/>
        <attr name="current_progress_text_size" format="dimension"/>
    
        
         <!-- 设置显示圆环的弧度，360度则显示整个圆环-->
        <attr name="sweep_angle" format="integer"/>
        <attr name="start_arc" format="float"/>
        <attr name="end_arc" format="float"/>
       

    </declare-styleable>
    
</resources>

2Add the following code to the root view of your layout:
<br>xmlns:app="http://schemas.android.com/apk/res/com.htq.circleprogressbar"

3Add the widget code in the appropriate place in your xml file. Here's a sample implementation:
<com.htq.circleprogressbar.libary.CircleProgressBar 
    android:id="@+id/circle_progress_bar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" 
    android:layout_marginLeft="20dp"
    app:circle_color="#000"
    app:circle_width="2dp"
    app:progress_color1="#f00"
    app:progress_width="2dp"
    app:start_arc="90"
    app:is_set_top_title="true"
    app:top_title="我的github,欢迎follow,star,fork"
    app:top_title_color="#000"
    app:top_title_text_size="12sp"
    app:is_set_bottom_content="true"
    app:bottom_content="https://github.com/HuTianQi"
    app:bottom_content_color="#f00"
    app:bottom_content_text_size="12sp"/>

