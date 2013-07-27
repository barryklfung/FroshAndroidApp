package ca.skule.froshapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ca.skule.froshapplication.NotificationService;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
    // When our Alarm time is triggered , this method will be excuted (onReceive)
    // We're invoking a service in this method which shows Notification to the User
     Intent myIntent = new Intent(context, NotificationService.class);
     context.startService(myIntent);
   }

} 
