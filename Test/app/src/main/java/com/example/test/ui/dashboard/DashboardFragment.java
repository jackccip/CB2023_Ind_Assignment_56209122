package com.example.test.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.activity;

import java.util.Timer;
import java.util.TimerTask;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        .getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(String.valueOf(activity.act.size()));
            }
        });*/

        LinearLayout mainLL = root.findViewById(R.id.main_LL);

        Timer timer = new Timer(true);

        for(int i = 0; i < activity.act.size(); i++ ){

            final int j = i;

            Log.d("a", "xxd");
            LinearLayout linearLayout = new LinearLayout(this.getActivity());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            linearLayout.setLayoutParams(params);

            LinearLayout time = new LinearLayout(this.getActivity());
            time.setOrientation(LinearLayout.HORIZONTAL);
            time.setLayoutParams(params);

            LinearLayout name = new LinearLayout(this.getActivity());
            name.setOrientation(LinearLayout.HORIZONTAL);
            name.setLayoutParams(params);

            TextView textView1 = new TextView(this.getActivity());
            TextView hh = new TextView(this.getActivity());
            TextView colon = new TextView(this.getActivity());
            TextView mm = new TextView(this.getActivity());
            TextView colon2 = new TextView(this.getActivity());
            TextView ss = new TextView(this.getActivity());

            textView1.setText(activity.act.get(i).task_name);
            textView1.setLayoutParams(params);
            textView1.setTextSize(30);
            if(activity.act.get(j).doing){
                textView1.setTextColor(Color.parseColor("#008000"));
            }
            else
                textView1.setTextColor(Color.parseColor("#DC143C"));

            hh.setText(String.valueOf(activity.act.get(i).now_hh));
            hh.setLayoutParams(params);
            hh.setTextSize(30);
            hh.setGravity(Gravity.CENTER);

            colon.setText(":");
            colon.setLayoutParams(params);
            colon.setTextSize(30);
            colon.setGravity(Gravity.CENTER);

            mm.setText(String.valueOf(activity.act.get(i).now_mm));
            mm.setLayoutParams(params);
            mm.setTextSize(30);
            mm.setGravity(Gravity.CENTER);

            colon2.setText(":");
            colon2.setLayoutParams(params);
            colon2.setTextSize(30);
            colon2.setGravity(Gravity.CENTER);

            ss.setText(String.valueOf(activity.act.get(i).now_ss));
            ss.setLayoutParams(params);
            ss.setTextSize(30);
            ss.setGravity(Gravity.CENTER);

            name.addView(textView1);
            time.addView(hh);
            time.addView(colon);
            time.addView(mm);
            time.addView(colon2);
            time.addView(ss);
            linearLayout.addView(name);
            linearLayout.addView(time);

            if(activity.act.get(j).doing == true){
                activity.act.get(j).tt.cancel();
                activity.act.get(j).tt = new TimerTask() {
                    @Override
                    public void run() {
                        activity.act.get(j).now_ss--;
                        if(activity.act.get(j).now_ss<0) {
                            activity.act.get(j).now_ss = 59;
                            activity.act.get(j).now_mm--;
                            if (activity.act.get(j).now_mm < 0) {
                                activity.act.get(j).now_mm = 59;
                                activity.act.get(j).now_hh--;
                                if (activity.act.get(j).now_hh < 0) {
                                    activity.act.get(j).tt.cancel();
                                    activity.act.get(j).now_ss = 0;
                                    activity.act.get(j).now_mm = 0;
                                    activity.act.get(j).now_hh = 0;
                                }
                            }
                        }

                        hh.setText(String.valueOf(activity.act.get(j).now_hh));
                        colon.setText(":");
                        mm.setText(String.valueOf(activity.act.get(j).now_mm));
                        colon2.setText(":");
                        ss.setText(String.valueOf(activity.act.get(j).now_ss));
                    }
                };
                timer.schedule(activity.act.get(j).tt, 1000, 1000);
            }

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity.act.get(j).doing == false){
                        //long t = (activity.act.get(j).now_hh*60*60+activity.act.get(j).now_mm*60+activity.act.get(j).now_ss)*1000;
                        activity.act.get(j).tt = new TimerTask() {
                            @Override
                            public void run() {
                                activity.act.get(j).now_ss--;
                                if(activity.act.get(j).now_ss<0) {
                                    activity.act.get(j).now_ss = 59;
                                    activity.act.get(j).now_mm--;
                                    if (activity.act.get(j).now_mm < 0) {
                                        activity.act.get(j).now_mm = 59;
                                        activity.act.get(j).now_hh--;
                                        if (activity.act.get(j).now_hh < 0) {
                                            activity.act.get(j).tt.cancel();
                                            activity.act.get(j).now_ss = 0;
                                            activity.act.get(j).now_mm = 0;
                                            activity.act.get(j).now_hh = 0;
                                        }
                                    }
                                }

                                hh.setText(String.valueOf(activity.act.get(j).now_hh));
                                colon.setText(":");
                                mm.setText(String.valueOf(activity.act.get(j).now_mm));
                                colon2.setText(":");
                                ss.setText(String.valueOf(activity.act.get(j).now_ss));
                            }
                        };

                        timer.schedule(activity.act.get(j).tt, 1000, 1000);
                        textView1.setTextColor(Color.parseColor("#008000"));
                        activity.act.get(j).doing = true;
                    }
                    else{
                        activity.act.get(j).tt.cancel();
                        textView1.setTextColor(Color.parseColor("#DC143C"));
                        activity.act.get(j).doing = false;
                    }
                }
            });

            mainLL.addView(linearLayout);
        }

        return root;
    }
}