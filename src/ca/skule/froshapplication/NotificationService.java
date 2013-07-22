package ca.skule.froshapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

public class NotificationService extends Service {
	private NotificationManager mManager;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
	super.onStartCommand(intent, flags, startId);
	this.getApplicationContext();
	mManager = (NotificationManager) this.getApplicationContext()
			.getSystemService(
					Context.NOTIFICATION_SERVICE);
	Notification notification = new Notification.Builder(this.getApplicationContext())
    	.setContentTitle(intent.getStringExtra("eventName"))
    	.setContentText(intent.getStringExtra("eventName")+ " is now happening!")
    	.setSmallIcon(R.drawable.ic_launcher)
    	.build();
	
	notification.flags |= Notification.FLAG_AUTO_CANCEL;
	
	return START_STICKY;
}
}
