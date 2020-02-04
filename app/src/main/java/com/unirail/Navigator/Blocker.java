package com.unirail.Navigator;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

import com.unirail.UniRail;

public class Blocker implements SensorEventListener
{
    static private final float accelation_criteria=1.7f;

    private SensorManager manager;
    private Sensor accelerometer;

    private boolean is_being_accelated=false;

    private Vibrator vibrator;
    private Notifier Notifier;

    public Blocker(final Context context)
    {
        manager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        Notifier=new Notifier(context);
    }

    public final void block()
    {
        if(UniRail.does_block)
        {
            manager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public final void stop()
    {
        manager.unregisterListener(this);
    }

    @Override
    public final void onSensorChanged(final SensorEvent event)
    {
        final float X_accelation=event.values[0]/ SensorManager.GRAVITY_EARTH;
        final float Y_accelation=event.values[1]/ SensorManager.GRAVITY_EARTH;
        final float Z_accelation=event.values[2]/ SensorManager.GRAVITY_EARTH;

        if(Math.sqrt(X_accelation*X_accelation+Y_accelation*Y_accelation+Z_accelation*Z_accelation)>accelation_criteria)
        {
            if(is_being_accelated==false)
            {
                vibrator.vibrate(250);
                Notifier.notify_importantly("이번 역은 내리실 역이 아닙니다. 내리지 마시기 바랍니다.");
            }

            is_being_accelated=true;
        }
        else
        {
            is_being_accelated=false;
        }
    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy)
    {
    }
}