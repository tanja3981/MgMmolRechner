package android.tasc.ch.mmolmgrechner;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;


public class MmolMgRechner extends Activity {


    private Einheit currentEditor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mgText = (EditText) findViewById(R.id.tf_mg);
        final EditText mmolText = (EditText) findViewById(R.id.tf_mmol);

        mgText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
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
                if (currentEditor == Einheit.MG)
                    return;
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
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
