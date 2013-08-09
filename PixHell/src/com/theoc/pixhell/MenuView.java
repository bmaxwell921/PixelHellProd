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
	private Paint brush      = null;
	private Bitmap ship      = null;
	private Bitmap expl = null;
	private Bitmap title     = null;

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
			this.ship = (Bitmap) BitmapFactory.decodeStream(assets.open("img/Player.png"));
			this.expl = (Bitmap) BitmapFactory.decodeStream(assets.open("img/Explosion.png"));
			this.title = (Bitmap) BitmapFactory.decodeStream(assets.open("img/Title.png"));
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
		
		if (this.ship != null) {
			canvas.drawBitmap(expl, (w/4) - (expl.getWidth()/2), (h/4) - (expl.getHeight()/2), this.brush);
			canvas.drawBitmap(expl, (w/4) - (expl.getWidth()/2), (3*h/4) - (expl.getHeight()/2), this.brush);
			canvas.drawBitmap(expl, (3*w/4) - (expl.getWidth()/2), (h/4) - (expl.getHeight()/2), this.brush);
			canvas.drawBitmap(expl, (3*w/4) - (expl.getWidth()/2), (3*h/4) - (expl.getHeight()/2), this.brush);
			
			canvas.drawBitmap(expl, (w/3) - (expl.getWidth()/2), (h/3) - (expl.getHeight()/2), this.brush);
			canvas.drawBitmap(expl, (w/3) - (expl.getWidth()/2), (2*h/3) - (expl.getHeight()/2), this.brush);
			canvas.drawBitmap(expl, (2*w/3) - (expl.getWidth()/2), (h/3) - (expl.getHeight()/2), this.brush);
			canvas.drawBitmap(expl, (2*w/3) - (expl.getWidth()/2), (2*h/3) - (expl.getHeight()/2), this.brush);
			
			canvas.drawBitmap(ship, (w/2) - (ship.getWidth()/2), (h/2) - (ship.getHeight()/2), this.brush);
			canvas.drawBitmap(title, (w/2) - (title.getWidth()/2), (h/3) - (title.getHeight()/2), this.brush);
		}
	}

}
