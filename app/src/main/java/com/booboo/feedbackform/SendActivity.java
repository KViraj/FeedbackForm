package com.booboo.feedbackform;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {

    TextView tvSend;
    FloatingActionButton btnSend, btnWhatsApp, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent i = getIntent();
        final String msg = i.getStringExtra("msg");

        tvSend = findViewById(R.id.tvSend);
        btnSend = findViewById(R.id.btnSend);
        btnWhatsApp = findViewById(R.id.btnWhatsApp);
        btnBack = findViewById(R.id.btnBack);

        tvSend.setText(msg);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(i);
            }
        });

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.setPackage("com.whatsapp");
                i.putExtra(Intent.EXTRA_TEXT, msg);
                try {
                    startActivity(i);
                }
                catch (Exception e) {
                    Snackbar.make(v,"WhatsApp not Installed", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
