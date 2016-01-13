package com.designedbyz.trafficstatstest;

import android.net.TrafficStats;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView text;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.refresh_button);
        text = (TextView)findViewById(R.id.traffic_stats);
        button.setOnClickListener(buttonClickListener);
        wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CharSequence currentText = text.getText();
            long mobileData = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
            long wifiData = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes() - mobileData;
            boolean wifiEnabled = wifiManager.isWifiEnabled();
            text.setText(currentText + "\nMobile data: " + mobileData + " wifi data: " + wifiData + " wifi enabled " + wifiEnabled);
        }
    };

}
