package com.htq.circleprogressbar.libary;


import com.htq.circleprogressbar.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

/**
 * 
 * @author htq
 * 爱丽颖的颖火虫
 * blog:http://blog.csdn.net/htq__
 *
 */
public class CircleProgressBar extends View {

	//圆环的背景颜色与宽度属性，颜色默认为黑色，宽度默认为2
	private  int circleColor ;
	private float circleWidth;
	
	//进度条的背景颜色与宽度属性，为了达到一个渐变色的效果，这里定义三个过渡色，进度条默认为绿色，宽度默认为1
	private int progressColor;
	private int progressColor2;
	private int progressColor3;
	private float progressWidth ;
	
	//进度条进度显示是否为从高到低，即从100到0，默认为从低到高，从高到底一般是在手机管家类的App中清理内存模块用于显示内存时用到
	private boolean isHighToLow;
	
	//是否设置顶部标题，中间内容，底部内容
	private boolean isSetToptitle;
	private boolean isSetMidContent;
	private boolean isSetBottomContent;
	
	
	
	private String topTitle;
	private float currentProgress;
	private String bottmoContent;
	private float maxProgress;
	
	//设置显示的title,midContent,bottomContent的颜色，为了美观和谐,title与bottom的颜色应该相同，midcontent的颜色可以不同，字体大小可以不同
	private int topTitleColor;
	private int midProgressColor;
	private int bottomcontentColor;
	private float toptitleTextSize;
	private float currentProgressTextsize;
	private float bottomcontentTextSize;
		
	private float center;
	private int radius;
	
	//需要绘制的总弧度
	private int sweepAngle;
    private float startAngle;
	//因为可能绘画的不是一个完整的圆，所以不能用canvas.drawCircle这个方法，而是采用的 canvas.drawArc方法，因此要
	//定义一个RectF对象，这个矩形不会被画出来，只是用来确定绘画的圆的位置而已
	private RectF hideRect;
	private Paint circleBgPaint,progressPaint,titlePaint,midPaint,bottomPaint;
	private SweepGradient sweepGradient;
	private Matrix rotateMatrix;
	private  int colors[];
//	private LinearGradient linearGradient;
	
	
	
	public CircleProgressBar(Context context) {
		super(context,null);
		
	}

	public CircleProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 initView(context, attrs);
		 
	}

	public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		 initView(context, attrs);
		
		// TODO Auto-generated constructor stub
	}

	private void initView(Context context,AttributeSet attrs)
	{
		
		
		TypedArray a =context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        circleColor = a.getColor(R.styleable.CircleProgressBar_circle_color, Color.BLACK);
        circleWidth = a.getDimension(R.styleable.CircleProgressBar_circle_width, 2);
      
        progressColor = a.getColor(R.styleable.CircleProgressBar_progress_color1, Color.GREEN);
        progressColor2 = a.getColor(R.styleable.CircleProgressBar_progress_color2,progressColor);
        progressColor3 = a.getColor(R.styleable.CircleProgressBar_progress_color3,progressColor);
        progressWidth = a.getDimension(R.styleable.CircleProgressBar_progress_width, 1);
	
        isSetToptitle = a.getBoolean(R.styleable.CircleProgressBar_is_set_top_title, false);
        isSetMidContent = a.getBoolean(R.styleable.CircleProgressBar_is_set_mid_content, false);
        isSetBottomContent = a.getBoolean(R.styleable.CircleProgressBar_is_set_bottom_content, false);
        
        topTitle=a.getString(R.styleable.CircleProgressBar_top_title);
        bottmoContent=a.getString(R.styleable.CircleProgressBar_bottom_content);
        currentProgress=a.getFloat(R.styleable.CircleProgressBar_current_progress, 0);
        maxProgress=a.getFloat(R.styleable.CircleProgressBar_max_progress, 360);
	
        topTitleColor=a.getColor(R.styleable.CircleProgressBar_top_title_color, Color.GREEN);
        midProgressColor=a.getColor(R.styleable.CircleProgressBar_mid_progress_color, Color.GREEN);
        bottomcontentColor=a.getColor(R.styleable.CircleProgressBar_bottom_content_color,topTitleColor);
        toptitleTextSize= a.getDimension(R.styleable.CircleProgressBar_top_title_text_size, 15);
        currentProgressTextsize=a.getDimension(R.styleable.CircleProgressBar_current_progress_text_size, 55);
        bottomcontentTextSize=a.getDimension(R.styleable.CircleProgressBar_bottom_content_text_size, 15);
        
        sweepAngle=a.getInteger(R.styleable.CircleProgressBar_sweep_angle, 360);
        startAngle=a.getFloat(R.styleable.CircleProgressBar_start_arc,135);
        
        circleBgPaint=new Paint();
		circleBgPaint.setColor(circleColor);
		circleBgPaint.setStyle(Paint.Style.STROKE); 
		circleBgPaint.setStrokeWidth(circleWidth);
		circleBgPaint.setAntiAlias(true);
        
		progressPaint=new Paint();
		progressPaint.setColor(progressColor);
		progressPaint.setStyle(Paint.Style.STROKE); 
		progressPaint.setStrokeWidth(progressWidth);
		progressPaint.setStrokeCap(Paint.Cap.ROUND);
		
		
		titlePaint=new Paint();
		titlePaint.setAntiAlias(true);
		titlePaint.setTextSize(toptitleTextSize);
		titlePaint.setColor(topTitleColor);
		titlePaint.setTextAlign(Paint.Align.CENTER);
		
		
		midPaint=new Paint();
		midPaint.setAntiAlias(true);
		midPaint.setTextSize(currentProgressTextsize);
		midPaint.setColor(midProgressColor);
		midPaint.setTextAlign(Paint.Align.CENTER);
		
		bottomPaint=new Paint();
		bottomPaint.setAntiAlias(true);
		bottomPaint.setTextSize(bottomcontentTextSize);
		bottomPaint.setColor(bottomcontentColor);	
		bottomPaint.setTextAlign(Paint.Align.CENTER);
		
		
		colors=new int[]{ progressColor, progressColor2, progressColor3};
		rotateMatrix = new Matrix();

		a.recycle();
        
       
	}
	
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		
		    canvas.drawArc(hideRect, startAngle,sweepAngle, false, circleBgPaint);
	
		    rotateMatrix.setRotate(140, center, center);
	        sweepGradient.setLocalMatrix(rotateMatrix);
	     //   progressPaint.setShader(linearGradient);
	     //使用sweepGradient更加美观
	        progressPaint.setShader(sweepGradient);
		    canvas.drawArc(hideRect, startAngle, currentProgress/maxProgress*360, false, progressPaint);
	
		 if (isSetToptitle) {
	            canvas.drawText(topTitle, center, center- currentProgressTextsize, titlePaint);
	        }
		 if (isSetMidContent) {
	            canvas.drawText(String.format("%.0f", currentProgress), center, center, midPaint);
	        }
	    
	     if (isSetBottomContent) {
	            canvas.drawText( bottmoContent, center, center+currentProgressTextsize,bottomPaint);
	        }
	     
	    
	}
	
	
	
	 public void setCurrentProgress(float currentProgress) {
	        if (currentProgress > maxProgress) {
	        	currentProgress = maxProgress;
	        }
	        if (currentProgress < 0) {
	        	currentProgress = 0;
	        }
	        if(currentProgress <= maxProgress){  
	            this.currentProgress = currentProgress;  
	            postInvalidate();  
	        }  
	    }

	 
	
	 //必须重写该方法，否则在xml文件中定义warp_content与match_parent效果相同
	@Override
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		 super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 int desiredWidth ;
		 int desiredHeight ;
		 
     //设置了文本，且属性为wrap_content，那么以输入的文本的长度为宽度
		 if (isSetToptitle) {
		 Rect rect = new Rect();  
		 titlePaint.getTextBounds(topTitle, 0, topTitle.length(), rect); 
//		 desiredWidth =(int) (rect.width()+4*progressWidth);
//		 desiredHeight = (int) (rect.width()+4*progressWidth);
		 desiredWidth =(int) (1.5*rect.width());
		 desiredHeight = (int) (1.5*rect.width());
		 }
		 else//没设置文本那么不可能设置wrap_content属性，事实上这些设置无效
		 {
			 desiredWidth =500;
			 desiredHeight = 500;
		 }
		    
		    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		    int width;
		    int height;

		    //Measure Width
		    if (widthMode == MeasureSpec.EXACTLY) {
		        //Must be this size
		        width = widthSize;
		    } else if (widthMode == MeasureSpec.AT_MOST) {
		        //Can't be bigger than...
		        width = Math.min(desiredWidth, widthSize);
		    } else {
		        //Be whatever you want
		        width = desiredWidth;
		    }

		    //Measure Height
		    if (heightMode == MeasureSpec.EXACTLY) {
		        //Must be this size
		        height = heightSize;
		    } else if (heightMode == MeasureSpec.AT_MOST) {
		        //Can't be bigger than...
		        height = Math.min(desiredHeight, heightSize);
		    } else {
		        //Be whatever you want
		        height = desiredHeight;
		    }

		    //MUST CALL THIS
		    setMeasuredDimension(width, height);
		    
		    center = getWidth()/2; //该方法必须在onDraw或者onMeasure中调用，否则不起作用d
	        
			//圆环的半径 ，此处必须是progressWidth与circleWidth中较大的一个
		    //radius = (int) (center - progressWidth/2); 
			if(progressWidth>circleWidth)
			  radius=(int)(center-progressWidth/2);
			else
			  {radius=(int)(center-circleWidth/2);}
	        
	       
	        sweepGradient = new SweepGradient(0, 0, colors, null);
	        hideRect=new RectF(center - radius, center - radius, center  
	                + radius, center + radius); 

		}
	
	/**
	 * @author htq（爱丽颖的颖火虫）
	 * bolg:http://blog.csdn.net/htq__
	 * 用来设置sweepAngle参数，该参数在绘制部分圆弧的时候用到，默认为360
	 * @param sweepAngle
	 */
	public void setSweepAngle(int sweepAngle)
	{
		this.sweepAngle=sweepAngle;
	}
	
	
	/**
	 * @author htq（爱丽颖的颖火虫）
	 * bolg:http://blog.csdn.net/htq__
	 * 设置进度条的最大值
	 * @param maxProgress 用来控制进度条的最大值，范围在0-360之间
	 */
	public void setMaxProgress(int maxProgress)
	{
		this.maxProgress=maxProgress;
	}

}
