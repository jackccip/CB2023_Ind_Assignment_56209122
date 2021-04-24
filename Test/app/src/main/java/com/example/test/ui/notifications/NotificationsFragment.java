package com.example.test.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.activity;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        Button creat = root.findViewById(R.id.button2);
        EditText task_name = root.findViewById(R.id.task_name);
        EditText hh = root.findViewById(R.id.hh);
        EditText mm = root.findViewById(R.id.mm);

        creat.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task_name.getText().toString().matches("")){
                    ShowMsgDialog("Task name should not be empty!");
                }
                else if(hh.getText().toString().matches("") || mm.getText().toString().matches("")){
                    ShowMsgDialog("Time should not be empty!");
                }
                else if(Integer.parseInt(hh.getText().toString())>23||Integer.parseInt(hh.getText().toString())<0){
                    ShowMsgDialog("Hour should be 0-23!");
                }
                else if(Integer.parseInt(mm.getText().toString())>59||Integer.parseInt(hh.getText().toString())<0){
                    ShowMsgDialog("Minute should be 0-59!");
                }
                else{
                    activity new_act = new activity(
                            task_name.getText().toString(), false,
                            Integer.parseInt(hh.getText().toString()),
                            Integer.parseInt(mm.getText().toString()), 0,
                            Integer.parseInt(hh.getText().toString()),
                            Integer.parseInt(mm.getText().toString()), 0);
                    activity.act.add(new_act);
                }
            }
        });
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