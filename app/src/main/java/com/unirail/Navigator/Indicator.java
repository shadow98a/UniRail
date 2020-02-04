package com.unirail.Navigator;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.unirail.R;
import com.unirail.UniRail;

import static java.lang.StrictMath.abs;

public class Indicator implements SensorEventListener
{
    private SensorManager manager;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private final SoundPool pool;
    private final int[] soundIDs=new int[7];

    private final float[] gravity=new float[3];
    private final float[] geomagnetic=new float[3];

    private Thread thread;

    public Indicator(final Context context)
    {
        manager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer=manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        AudioAttributes.Builder attributes_builder=new AudioAttributes.Builder();
        attributes_builder.setUsage(AudioAttributes.USAGE_MEDIA);
        attributes_builder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);

        SoundPool.Builder pool_builder=new SoundPool.Builder();
        pool_builder.setAudioAttributes(attributes_builder.build());
        pool_builder.setMaxStreams(2);

        pool=pool_builder.build();
        soundIDs[0]=pool.load(context,R.raw.indicator_0,1);
        soundIDs[1]=pool.load(context,R.raw.indicator_1,1);
        soundIDs[2]=pool.load(context,R.raw.indicator_2,1);
        soundIDs[3]=pool.load(context,R.raw.indicator_3,1);
        soundIDs[4]=pool.load(context,R.raw.indicator_4,1);
        soundIDs[5]=pool.load(context, R.raw.indicator_5,1);
        soundIDs[6]=pool.load(context,R.raw.indicator_6,1);
    }

    public final void indicate(final float station_azimuth)
    {
        if(UniRail.does_indicate)
        {
            manager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_GAME);
            manager.registerListener(this,magnetometer, SensorManager.SENSOR_DELAY_GAME);

            thread=new Thread
            (
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            while(true)
                            {
                                final float sensor_azimuth=get_sensor_azimuth();

                                pool.play
                                (
                                    soundIDs[((int)get_angle(sensor_azimuth,station_azimuth)+15)/30],
                                    get_angle(get_sum_of_azimuths(sensor_azimuth,270),station_azimuth)<=90?1:0,
                                    get_angle(get_sum_of_azimuths(sensor_azimuth,90),station_azimuth)<=90?1:0,
                                    1,
                                    0,
                                    1
                                );

                                Thread.sleep((long)(250* Math.pow(2.0,get_angle(sensor_azimuth,station_azimuth)/90)));
                            }
                        }
                        catch(final InterruptedException exception)
                        {
                            return;
                        }
                    }
                }
            );
            thread.start();
        }
    }

    public final void stop()
    {
        if(thread!=null)
        {
            thread.interrupt();
        }
        manager.unregisterListener(this);
    }

    @Override
    public final void onSensorChanged(final SensorEvent event)
    {
        synchronized (this)
        {
            switch (event.sensor.getType())
            {
                case Sensor.TYPE_ACCELEROMETER:
                    gravity[0]=event.values[0];
                    gravity[1]=event.values[1];
                    gravity[2]=event.values[2];
                    break;

                case Sensor.TYPE_MAGNETIC_FIELD:
                    geomagnetic[0]=event.values[0];
                    geomagnetic[1]=event.values[1];
                    geomagnetic[2]=event.values[2];
            }
        }
    }

    public final float get_sensor_azimuth()
    {
        final float[] R=new float[9];
        SensorManager.getRotationMatrix(R,null,gravity,geomagnetic);
        final float orientation[]=new float[3];
        SensorManager.getOrientation(R,orientation);

        return (float) Math.toDegrees(orientation[0]);
    }

    private final float get_angle(final float azimuth_1,final float azimuth_2)
    {
        final float temporary_value=abs(azimuth_1-azimuth_2);
        if(temporary_value<=180)
        {
            return temporary_value;
        }
        else
        {
            return 360-temporary_value;
        }
    }

    private final float get_sum_of_azimuths(final float azimuth_1,final float azimuth_2)
    {
        return (azimuth_1+azimuth_2)%360;
    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy)
    {
    }
}