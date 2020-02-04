package com.unirail;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.unirail.R;

public class SettingActivity extends Activity {

    boolean is_theme_white = false;

    private ConstraintLayout layout;
    //    private Button black_theme;
//    private Button white_theme;
//    private Button start_activity;
    private Switch does_notify_importantly;
    private Switch does_indicate;
    private Switch does_block;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        layout = findViewById(R.id.layout);
//        black_theme = findViewById(R.id.black_theme);
//        white_theme = findViewById(R.id.white_theme);
//        start_activity=findViewById(R.id.start_activity);
        does_notify_importantly=findViewById(R.id.does_notify_importantly);
        does_indicate=findViewById(R.id.does_indicate);
        does_block=findViewById(R.id.does_block);
//        black_theme.setOnClickListener
//                (
//                        new View.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                is_theme_white = false;
//                                layout.setBackgroundColor(Color.BLACK);
//                                black_theme.setBackgroundColor(Color.BLACK);
//                                white_theme.setBackgroundColor(Color.BLACK);
//
//                                black_theme.setTextColor(Color.WHITE);
//                                white_theme.setTextColor(Color.WHITE);
//                                start_activity.setTextColor(Color.WHITE);
//                            }
//                        }
//                        );
//        white_theme.setOnClickListener
//                (
//                        new View.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                is_theme_white = true;
//                                layout.setBackgroundColor(Color.WHITE);
//                                black_theme.setBackgroundColor(Color.WHITE);
//                                white_theme.setBackgroundColor(Color.WHITE);
//
//                                black_theme.setTextColor(Color.BLACK);
//                                white_theme.setTextColor(Color.BLACK);
//                                start_activity.setTextColor(Color.BLACK);
//                            }
//                        }
//                );
//        start_activity.setOnClickListener
//                (
//                        new View.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                Intent intent= new Intent(SettingActivity.this, MainActivity.class);
//                                intent.putExtra("is_theme_white",is_theme_white);
//                                startActivity(intent);
//                            }
//                        }
//                );
        does_notify_importantly.setChecked(UniRail.does_notify_importantly);
        does_indicate.setChecked(UniRail.does_indicate);
        does_block.setChecked(UniRail.does_block);

        does_notify_importantly.setOnCheckedChangeListener
                (
                        new CompoundButton.OnCheckedChangeListener()
                        {
                            @Override
                            public void onCheckedChanged(CompoundButton button, boolean is_checked)
                            {
                                UniRail.does_notify_importantly=is_checked;
                            }
                        }
                );
        does_indicate.setOnCheckedChangeListener
                (
                        new CompoundButton.OnCheckedChangeListener()
                        {
                            @Override
                            public void onCheckedChanged(CompoundButton button, boolean is_checked)
                            {
                                UniRail.does_indicate=is_checked;
                            }
                        }
                );
        does_block.setOnCheckedChangeListener
                (
                        new CompoundButton.OnCheckedChangeListener()
                        {
                            @Override
                            public void onCheckedChanged(CompoundButton button, boolean is_checked)
                            {
                                UniRail.does_block=is_checked;
                            }
                        }
                );

    }

}
