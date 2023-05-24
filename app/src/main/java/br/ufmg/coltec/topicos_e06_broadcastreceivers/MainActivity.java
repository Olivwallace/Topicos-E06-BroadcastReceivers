package br.ufmg.coltec.topicos_e06_broadcastreceivers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static int CURRENT_THEME = R.style.Theme_TopicosE06BroadcastReceivers;
    private static int LOW_THEME = R.style.Theme_TopicosE06BroadcastReceiversLowBattery;

    private static int DEFAULT_THEME = CURRENT_THEME;

    // TODO: Criar os receivers para tratar a mudança do tema
    BroadcastReceiver batteryReceiver = new BatteryReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            switchActivityTheme(LOW_THEME);
            Log.e("Bateria Fraca", "Alterando tema para economia de bateria!");
        }
    };

    BroadcastReceiver batteryOkay = new BatteryReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            switchActivityTheme(DEFAULT_THEME);
            Log.d("Bateria Ok", "Bateria Carregada!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: registrar os receivers
        registrarReceivers();
    }

    protected void onResume(){
        super.onResume();
        registrarReceivers();
    }

    protected void onStop() {
        super.onStop();
        removerReceivers();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // TODO: desregistrar os receivers
        removerReceivers();
    }

    private void removerReceivers () {
        unregisterReceiver(batteryReceiver);
        unregisterReceiver(batteryOkay);
    }

    private void registrarReceivers () {
        IntentFilter receiverFilterLOW = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        IntentFilter receiverFilterOK = new IntentFilter(Intent.ACTION_BATTERY_OKAY);
        registerReceiver(batteryReceiver, receiverFilterLOW);
        registerReceiver(batteryOkay, receiverFilterOK);
    }



    /**
     * Realiza a troca do tema da Activity.
     *
     * Basicamente, esse método atualiza a variável estática responsável por definir o tema da
     * activity com o novo valor enviado por parâmetro, e recria toda a activity do zero para
     * que o novo tema seja aplicado.
     *
     * @param themeId Id do tema que será trocado.
     *                O parâmetro se refere ao ID que o tema possui na classe R.java.
     *                Para esse app específico, podemos informar dois possíveis IDs.
     *                - R.style.Theme_TopicosE06BroadcastReceivers (Tema padrão)
     *                - R.style.Theme_TopicosE06BroadcastReceiversLowBattery (bateria baixa)
     */
    private void switchActivityTheme(int themeId) {
        CURRENT_THEME = themeId;
        MainActivity.this.finish();
        MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class));
    }
}