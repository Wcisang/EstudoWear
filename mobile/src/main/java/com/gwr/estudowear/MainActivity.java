package com.gwr.estudowear;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationCompat.BigTextStyle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView textView;
    String[] lista ;
    ListView list;
    private int NOTIFICATION_ID = 1;

    private static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
    private static final String GROUP_KEY_EMAILS = "group_key_emails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lista = new String[]{"Basic Notification","Notification with map Action","Notification to open Activity with Extend",
        "Notification with Big Text Style","Notification with background in wear","Notification With Text Return",
        "Notification with More Pages","More than one Notification","Notification with Inbox Style"};
        textView = (TextView) findViewById(R.id.textview);
        list = (ListView) findViewById(R.id.listview);
        list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista));
        list.setOnItemClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEventFromWearableService(Steps steps){
        Toast.makeText(this,"Steps: "+steps.getSteps(),Toast.LENGTH_SHORT).show();
        textView.setText("Steps: "+steps.getSteps());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:
                sendNotification();
                break;
            case 1:
                sendNotificationWithMapAction();
                break;
            case 2:
                sendNotificationOpenActivityWithExtend();
                break;
            case 3:
                sendNotificationWithBigStyle();
                break;
            case 4:
                sendNotificationwithBackground();
                break;
            case 5:
                sendNotificationWithReturn();
                break;
            case 6:
                sendNotificationWithMorePages();
                break;
            case 7:
                sendNotificationWithMoreThanOneNotification();
                break;
            case 8:
                sendNotificationWithInboxStyle();
                break;


        }
    }
    public void sendNotification() {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("BasicNotifications Sample");
        builder.setContentText("Time to learn about notifications!");
        builder.setSubText("Tap to view documentation about notifications.");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void sendNotificationWithMapAction(){

        Intent it = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("geo:37.7749,-122.4194");
        it.setData(uri);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,it,0);

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent2);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Open Map notification");
        builder.setContentText("MAP");
        builder.setSubText("Open the map");
        builder.addAction(R.drawable.cast_ic_notification_0,"Abrir mapa",pendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void sendNotificationOpenActivityWithExtend(){

        Intent it = new Intent(MainActivity.this,ActionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,it,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.cast_ic_notification_rewind,"Abrir Activity",pendingIntent).build();

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Abrir Activity")
                .setContentText("Clicar no Action")
                .extend(new WearableExtender().addAction(action)).build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void sendNotificationWithBigStyle(){

        Intent it = new Intent(MainActivity.this,ActionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,it,PendingIntent.FLAG_UPDATE_CURRENT);

        BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae lectus euismod, elementum orci pulvinar," +
                " porta erat. In hac habitasse platea dictumst. Curabitur et nibh eu massa rutrum commodo quis faucibus metus. Praesent non nibh " +
                "vel ipsum molestie commodo. Suspendisse potenti. Duis in nisi in nisi mattis rhoncus. Curabitur mattis est quis sem convallis aliquet.");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Open Map notification");
        builder.setContentText("MAP");
        builder.setSubText("Open the map");
        builder.addAction(R.drawable.cast_ic_notification_0,"Abrir mapa",pendingIntent);
        builder.setStyle(bigTextStyle);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void sendNotificationwithBackground(){

        WearableExtender wearableExtender = new WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),
                        R.drawable.cast_ic_notification_2));

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Email from Will")
                .setContentText("Está é a mensagem do will, responda o mais breve")
                .extend(wearableExtender).build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void sendNotificationWithReturn(){
        String[] choices = getResources().getStringArray(R.array.response);
        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                .setChoices(choices)
                .setLabel("Fale algo")
                .build();

        Intent it = new Intent(MainActivity.this,ActionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,it,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,"Responda",pendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.cast_ic_notification_2)
                .setContentTitle("Notificação com Resposta")
                .setContentText("Responda a essa notificação")
                .extend(new WearableExtender().addAction(action))
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void sendNotificationWithMorePages(){

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.cast_ic_notification_1)
                .setContentTitle("Page 1")
                .setContentText("Short Message")
                .setContentIntent(pendingIntent2);

        BigTextStyle secondPage = new NotificationCompat.BigTextStyle();
        secondPage.bigText("a lot of text here...")
                .setBigContentTitle("Page 2");

        Notification secondNotification = new NotificationCompat.Builder(this)
                .setStyle(secondPage).build();

        Notification notification = notificationBuilder
                .extend(new NotificationCompat.WearableExtender()
                    .addPage(secondNotification)).build();


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    public void sendNotificationWithMoreThanOneNotification(){

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.cast_ic_notification_1)
                        .setContentTitle("New Email from Will")
                        .setContentText("Short Message")
                        .setGroup(GROUP_KEY_EMAILS)
                        .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, notification);

        Notification notification2 =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.cast_ic_notification_1)
                        .setContentTitle("New Email from Raq")
                        .setContentText("Short Message")
                        .setGroup(GROUP_KEY_EMAILS)
                        .build();

        notificationManager.notify(2, notification2);

    }

    public void sendNotificationWithInboxStyle(){

        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("2 new messages")
                .setSmallIcon(R.drawable.cast_ic_notification_2)
                .setStyle(new NotificationCompat.InboxStyle()
                        .setBigContentTitle("2 new Messages")
                        .addLine("Teste 1")
                        .addLine("Teste 2")
                        .setSummaryText("will@email.com"))
                .setGroup(GROUP_KEY_EMAILS)
                .setGroupSummary(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(3, notification);
    }


}
