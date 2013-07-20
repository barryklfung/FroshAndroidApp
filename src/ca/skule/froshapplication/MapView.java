package ca.skule.froshapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;

/**
 * This view displays a few rectangles that can be panned and zoomed. 
 * It is a subclass of PanZoomView, which provides most of the code to
 * support zooming and panning.

 */

public class MapView extends PanZoomView {
	
	private Location mLocation = null;

/**
 */
public MapView (Context context) {
    super (context);
}

public MapView (Context context, AttributeSet attrs) {
    super (context, attrs);
}

public MapView (Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
}

public void setMarker(Location marker)
{
	mLocation=marker;
}

public void changeCameraPosition(int x, int y)
{
	mPosX=-x*mScaleFactor+getWidth()*0.5f/mScaleFactor;
	mPosY=-y*mScaleFactor+getHeight()*0.5f/mScaleFactor;
	invalidate();
}

public void placeMarker(int x, int y, Canvas canvas, String marker){
	Paint paint = new Paint();
	
	paint.setColor(Color.RED);
    paint.setStrokeWidth(3);
    canvas.drawCircle (x, y, 15, paint);
    paint.setColor(Color.WHITE);
    paint.setTextAlign(Align.CENTER);
    canvas.drawText(marker, x, y+4, paint);
}
/**
 * Do whatever drawing is appropriate for this view.
 * The canvas object is already set up to be drawn on. That means that all translations and scaling
 * operations have already been done.
 * 
 * @param canvas Canvas
 * @return void
 */

public void drawOnCanvas (Canvas canvas) {
	super.drawOnCanvas(canvas);
	if (mLocation!=null)
	{
		placeMarker(mLocation.getCoordX(),mLocation.getCoordY(),canvas, mLocation.getShortName());
	}
}

/**
 * Return true if panning is supported.
 * 
 * @return boolean
 */

public boolean supportsPan () {
    return true;
}

/**
 * Return true if scaling is done around the focus point of the pinch.
 * 
 * @return boolean
 */

public boolean supportsScaleAtFocusPoint () {
    return true;
}

/**
 * Return true if pinch zooming is supported.
 * 
 * @return boolean
 */

public boolean supportsZoom () {
    return true;
}

} // end class
