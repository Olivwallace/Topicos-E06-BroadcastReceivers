package br.ufmg.coltec.topicos_e06_broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Bateria Fraca", "Alterando tema para economia de bateria!");
    }
}
