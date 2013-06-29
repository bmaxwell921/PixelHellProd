package com.theoc.pixhell;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MenuView extends View
{
	private Paint brush = null;
	private Bitmap image1 = null;
	private Bitmap image2 = null;

	public MenuView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		this.brush = new Paint();
		this.brush.setColor(Color.BLACK);
	}
	
	public void setContent(AssetManager assets) 
	{
		try
		{
			this.image1 = (Bitmap) BitmapFactory.decodeStream(assets.open("img/Player.png"));
			this.image2 = (Bitmap) BitmapFactory.decodeStream(assets.open("img/Explosion.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		canvas.drawRect(0, 0, w, h, this.brush);
		
		if (this.image1 != null) {
			canvas.drawBitmap(image2, w/4, h/4, this.brush);
			//canvas.drawBitmap(image2, w/2, h/2, this.brush);
			canvas.drawBitmap(image2, w/4, 3 * h/4, this.brush);
			canvas.drawBitmap(image2, 3 * w/4, h/4, this.brush);
			canvas.drawBitmap(image2, 3 * w/4, 3 * h/4, this.brush);
			canvas.drawBitmap(image2, w/3, h/3, this.brush);
			canvas.drawBitmap(image2, w/3, 2 * h/3, this.brush);
			canvas.drawBitmap(image2, 2 * w/3, h/3, this.brush);
			canvas.drawBitmap(image2, 2 * w/3, 2 * h/3, this.brush);
			canvas.drawBitmap(image1, w/2, h/2, this.brush);
		}
	}

}
