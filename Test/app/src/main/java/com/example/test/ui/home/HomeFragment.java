package com.example.test.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.activity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        ProgressBar pb = root.findViewById(R.id.progressBar);
        TextView pbt = root.findViewById(R.id.progress_text);

        Button abort = root.findViewById(R.id.abort);

        long overall = 1;
        long now = 0;

        abort.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to abort all?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean all_stop = true;
                                for(int i = 0; i < activity.act.size(); i++ ){
                                    if(activity.act.get(i).doing){
                                        all_stop = false;
                                        break;
                                    }
                                }
                                if(all_stop) {
                                    activity.act.clear();
                                    pb.setMax(1);
                                    pb.setProgress(0);
                                    pbt.setText(String.valueOf(String.format("%.2f %%", 0.0)));
                                }
                                else{
                                    ShowMsgDialog("You MUST stop all tasks before abortion!");
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        for(int i = 0; i < activity.act.size(); i++ ) {
            overall += (activity.act.get(i).hh*60*60+activity.act.get(i).mm*60+activity.act.get(i).ss);
            now += (activity.act.get(i).hh*60*60+activity.act.get(i).mm*60+activity.act.get(i).ss)
                    - (activity.act.get(i).now_hh*60*60+activity.act.get(i).now_mm*60+activity.act.get(i).now_ss);
        }
        Log.d("asdasd", String.valueOf((int)(now)));
        Log.d("asdasdx", String.valueOf((int)(overall)));
        pb.setMax((int)overall);
        pb.setProgress((int)now);
        pbt.setText(String.valueOf(String.format("%.2f %%", (double)((double)now/(double)overall*100))));

        return root;
    }

    private void ShowMsgDialog(String Msg)
    {
        AlertDialog ad = new AlertDialog.Builder(this.getActivity())
                .create();
        ad.setCancelable(false);
        ad.setTitle("Error");
        ad.setMessage(Msg);
        ad.setButton(("OK"), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }
}
