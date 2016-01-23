package android.tasc.ch.mmolmgrechner;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MmolMgRechner extends Activity {


    private Einheit currentEditor = null;
    private Einheit lastEditor = null;
    private int shadow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mgText = (EditText) findViewById(R.id.tf_mg);
        final EditText mmolText = (EditText) findViewById(R.id.tf_mmol);
        final ImageButton switchBt = (ImageButton) findViewById(R.id.bt_switch);

        mgText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (shadow > 0) {
                    return;
                }
                if (currentEditor == Einheit.MMOL) {
                    return;
                }
                currentEditor = Einheit.MG;
                String mgValue = mgText.getText().toString();
                if (mgValue.length() > 0) {

                    String mmolValue = new BzCalculator().calcMmol(mgValue);
                    if (mmolValue != null) {
                        mmolText.setText(mmolValue);
                    } else {
                        mgText.setText("");
                    }

                } else {
                    mmolText.setText("");
                }
                currentEditor = null;
                lastEditor = Einheit.MG;
            }
        });

        mmolText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (shadow > 0) {
                    return;
                }
                if (currentEditor == Einheit.MG) {
                    return;
                }
                currentEditor = Einheit.MMOL;
                Log.d("MainActivity", "afterTextChanged");
                String mmolValue = mmolText.getText().toString();
                if (!mmolValue.isEmpty()) {

                    String mgValue = new BzCalculator().calcMg(mmolValue);
                    if (mgValue != null) {
                        mgText.setText(mgValue);
                    } else {
                        mmolText.setText("");
                    }
                } else {
                    mgText.setText("");
                }
                currentEditor = null;
                lastEditor = Einheit.MMOL;
            }
        });

        switchBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //addShadow();
                if(lastEditor == Einheit.MG){
                    mmolText.setText(mgText.getText());
                }
                else if(lastEditor == Einheit.MMOL){
                    mgText.setText(mmolText.getText());
                }
                //removeShadow();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void addShadow() {
        shadow++;
    }

    private void removeShadow() {
        shadow--;
    }

}
