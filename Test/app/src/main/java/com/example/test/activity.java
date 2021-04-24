package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class activity {
    public String task_name;

    public boolean doing;

    public int hh;
    public int mm;
    public int ss;

    public int now_hh;
    public int now_mm;
    public int now_ss;

    public TimerTask tt;

    public activity(String task_nameX, boolean doingX, int hhX, int mmX, int ssX, int now_hhX, int now_mmX, int now_ssX) {
        task_name = task_nameX;
        doing = doingX;
        hh = hhX;
        mm = mmX;
        ss = ssX;
        now_hh = now_hhX;
        now_mm = now_mmX;
        now_ss = now_ssX;
    }

    public static List<activity> act = new ArrayList<activity>();
}