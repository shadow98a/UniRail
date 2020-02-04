package com.unirail.Navigator;

import com.unirail.UniRail;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class open_API
{
    static protected final String default_key= UniRail.default_key;
    static protected final String realtime_station_key= UniRail.realtime_station_key;
        
    protected String URL;

    protected final String JSON(final String URL_on_String)
    {
        final StringBuffer buffer=new StringBuffer();

        Thread thread=new Thread
            (
                new Runnable()
                {
                    @Override
                    public final void run()
                    {
                        java.net.URL URL_on_URL=null;
                        try
                        {
                            URL_on_URL = new URL(URL_on_String);
                        }
                        catch(final MalformedURLException exception)
                        {
                            exception.printStackTrace();
                        }

                        InputStream stream=null;
                        while(true)
                        {
                            try
                            {
                                stream = URL_on_URL.openStream();
                                break;
                            }
                            catch (final IOException exception)
                            {
                                exception.printStackTrace();
                            }
                        }

                        BufferedReader reader=null;
                        try
                        {
                            reader=new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                        }
                        catch(final UnsupportedEncodingException exception)
                        {
                            exception.printStackTrace();
                        }

                        String line=null;
                        try
                        {
                            line = reader.readLine();
                        }
                        catch(final IOException exception)
                        {
                            exception.printStackTrace();
                        }
                        while (line!=null)
                        {
                            buffer.append(line);
                            try
                            {
                                line = reader.readLine();
                            }
                            catch(final IOException exception)
                            {
                                exception.printStackTrace();
                            }
                        }
                    }
                }
            );
        thread.start();
        try
        {
            thread.join();
        }
        catch(final InterruptedException exception)
        {
            exception.printStackTrace();
        }

        return buffer.toString();
    }

    protected abstract void parse(final JSONObject object);
}
