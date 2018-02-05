package com.sparkle.countrypicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private static final int MY_CHILD_ACTIVITY = 102;
    private TextView editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        editText2 = findViewById(R.id.editText2);
        setAutoDetectedCountry();
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent countryIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivityForResult(countryIntent, MY_CHILD_ACTIVITY);
            }
        });
    }

    public void setAutoDetectedCountry() {
        try {
            boolean successfullyDetected = false;

            successfullyDetected = detectSIMCountry(false);

            if (!successfullyDetected) {
                successfullyDetected = detectNetworkCountry(false);
            }

            if (!successfullyDetected) {
                successfullyDetected = detectLocaleCountry(false);
            }

            if (!successfullyDetected) {
                resetToDefaultCountry();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resetToDefaultCountry();
        }

    }

    private boolean detectSIMCountry(boolean b) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String simCountryISO = telephonyManager.getSimCountryIso();
            if (simCountryISO == null || simCountryISO.isEmpty()) {
                if (b) {
                    resetToDefaultCountry();
                }
                return false;
            }
            setSelectedCountry(Country.getCountryForNameCodeFromLibraryMasterList(simCountryISO));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (b) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    public boolean detectNetworkCountry(boolean loadDefaultWhenFails) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String networkCountryISO = telephonyManager.getNetworkCountryIso();
            if (networkCountryISO == null || networkCountryISO.isEmpty()) {
                if (loadDefaultWhenFails) {
                    resetToDefaultCountry();
                }
                return false;
            }
            setSelectedCountry(Country.getCountryForNameCodeFromLibraryMasterList(networkCountryISO));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (loadDefaultWhenFails) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    public boolean detectLocaleCountry(boolean loadDefaultWhenFails) {
        try {
            String localeCountryISO = getResources().getConfiguration().locale.getCountry();
            if (localeCountryISO == null || localeCountryISO.isEmpty()) {
                if (loadDefaultWhenFails) {
                    resetToDefaultCountry();
                }
                return false;
            }
            setSelectedCountry(Country.getCountryForNameCodeFromLibraryMasterList(localeCountryISO));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (loadDefaultWhenFails) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    void setSelectedCountry(Country selectedCountry) {

        if (selectedCountry != null) {
            String displayText = "";


            if (displayText.length() > 0) {
                displayText += "  ";
            }
            displayText += "+" + selectedCountry.getPhoneCode();


            editText2.setText(displayText);
        }

    }

    private void resetToDefaultCountry() {
        editText2.setText("Default");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (MY_CHILD_ACTIVITY): {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("some_key");
                    editText2.setText(returnValue);
                }
                break;
            }
        }
    }
}
