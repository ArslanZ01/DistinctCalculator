package ars.tech.DistinctCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.DecimalFormat;

public class RechargeTax extends AppCompatActivity {
    EditText price_et, upper_tax_et, lower_tax_et, upper_price_et, lower_price_et, result_upper_et, result_lower_et;
    RadioButton upper_tax_type_rb, lower_tax_type_rb;
    RadioGroup tax_type_rg;
    CheckBox toggle_save_cb;
    Button calculate_b, done_tax_b, save_b;
    Float tax_percent, result_price;
    SharedPreferences shared_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_tax);

        shared_preferences = getSharedPreferences("recharge_tax", MODE_PRIVATE);

        upper_tax_type_rb = (RadioButton) findViewById(R.id.upper_tax_type_rb);
        lower_tax_type_rb = (RadioButton) findViewById(R.id.lower_tax_type_rb);
        tax_type_rg = (RadioGroup) findViewById(R.id.tax_type_rg);

        price_et = (EditText) findViewById(R.id.price_et);
        upper_tax_et = (EditText) findViewById(R.id.upper_tax_et);
        lower_tax_et = (EditText) findViewById(R.id.lower_tax_et);
        upper_price_et = (EditText) findViewById(R.id.upper_price_et);
        lower_price_et = (EditText) findViewById(R.id.lower_price_et);
        result_upper_et = (EditText) findViewById(R.id.result_upper_et);
        result_lower_et = (EditText) findViewById(R.id.result_lower_et);

        calculate_b = (Button) findViewById(R.id.calculate_b);
        done_tax_b = (Button) findViewById(R.id.tax_done_b);
        save_b = (Button) findViewById(R.id.save_b);

        toggle_save_cb = (CheckBox) findViewById(R.id.toggle_save_cb);

        calculate_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done_tax_b.performClick();
                if (!upper_tax_et.getText().toString().equals("") && !lower_tax_et.getText().toString().equals("")) {
                    if (price_et.getText().toString().equals(""))
                        price_et.setText("100");

                    upper_price_et.setText(new DecimalFormat("###.###").format((Float.parseFloat(upper_tax_et.getText().toString()) * Float.parseFloat(price_et.getText().toString())) / 100));
                    lower_price_et.setText(new DecimalFormat("###.###").format((Float.parseFloat(lower_tax_et.getText().toString()) * Float.parseFloat(price_et.getText().toString())) / 100));
                    result_upper_et.setText(new DecimalFormat("###.###").format(Float.parseFloat(price_et.getText().toString()) + Float.parseFloat(upper_price_et.getText().toString())));
                    result_lower_et.setText(new DecimalFormat("###.###").format(Float.parseFloat(price_et.getText().toString()) - Float.parseFloat(lower_price_et.getText().toString())));
                }
            }
        });

        done_tax_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upper_tax_type_rb.isChecked()) {
                    if (!upper_tax_et.getText().toString().equals("")) {
                        tax_percent = (Float.parseFloat(upper_tax_et.getText().toString()) / (100 + Float.parseFloat(upper_tax_et.getText().toString()))) * 100;
                        lower_tax_et.setText(tax_percent.toString());
                    }
                    else if (!lower_tax_et.getText().toString().equals("")) {
                        lower_tax_type_rb.setChecked(true);
                        done_tax_b.performClick();
                    }
                }
                if (lower_tax_type_rb.isChecked()) {
                    if (!lower_tax_et.getText().toString().equals("")) {
                        tax_percent = (Float.parseFloat(lower_tax_et.getText().toString()) / (100 - Float.parseFloat(lower_tax_et.getText().toString()) )) * 100;
                        upper_tax_et.setText(tax_percent.toString());
                    }
                    else if (!upper_tax_et.getText().toString().equals("")) {
                        upper_tax_type_rb.setChecked(true);
                        done_tax_b.performClick();
                    }
                }
            }
        });

        tax_type_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (upper_tax_type_rb.isChecked()) {
                    upper_tax_et.setEnabled(true);
                    lower_tax_et.setEnabled(false);
                }
                if (lower_tax_type_rb.isChecked()) {
                    lower_tax_et.setEnabled(true);
                    upper_tax_et.setEnabled(false);
                }
            }
        });

        toggle_save_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggle_save_cb.isChecked())
                    save_b.setText("Clear");
                else
                    save_b.setText("Save");
            }
        });

        save_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor shared_preferences_editor = shared_preferences.edit();

                if (save_b.getText().equals("Save")) {
                    shared_preferences_editor.putString("price", price_et.getText().toString());
                    shared_preferences_editor.putString("upper_tax", upper_tax_et.getText().toString());
                    shared_preferences_editor.putString("lower_tax", lower_tax_et.getText().toString());
                    shared_preferences_editor.putString("upper_price", upper_price_et.getText().toString());
                    shared_preferences_editor.putString("lower_price", lower_price_et.getText().toString());
                    shared_preferences_editor.putString("result_upper", result_upper_et.getText().toString());
                    shared_preferences_editor.putString("result_lower", result_lower_et.getText().toString());

                    shared_preferences_editor.putBoolean("upper_tax_type", upper_tax_type_rb.isChecked());
                    shared_preferences_editor.putBoolean("lower_tax_type", lower_tax_type_rb.isChecked());
                }
                else {
                    price_et.setText("");
                    upper_tax_et.setText("");
                    lower_tax_et.setText("");
                    upper_price_et.setText("");
                    lower_price_et.setText("");
                    result_upper_et.setText("");
                    result_lower_et.setText("");

                    shared_preferences_editor.remove("price");
                    shared_preferences_editor.remove("upper_tax");
                    shared_preferences_editor.remove("lower_tax");
                    shared_preferences_editor.remove("upper_price");
                    shared_preferences_editor.remove("lower_price");
                    shared_preferences_editor.remove("result_upper");
                    shared_preferences_editor.remove("result_lower");

                    shared_preferences_editor.remove("upper_tax_type");
                    shared_preferences_editor.remove("lower_tax_type");
                }

                shared_preferences_editor.apply();
            }
        });

        price_et.setText(shared_preferences.getString("price", price_et.getText().toString()));
        upper_tax_et.setText(shared_preferences.getString("upper_tax", upper_tax_et.getText().toString()));
        lower_tax_et.setText(shared_preferences.getString("lower_tax", lower_tax_et.getText().toString()));
        upper_price_et.setText(shared_preferences.getString("upper_price", upper_price_et.getText().toString()));
        lower_price_et.setText(shared_preferences.getString("lower_price", lower_price_et.getText().toString()));
        result_upper_et.setText(shared_preferences.getString("result_upper", result_upper_et.getText().toString()));
        result_lower_et.setText(shared_preferences.getString("result_lower", result_lower_et.getText().toString()));

        upper_tax_type_rb.setChecked(shared_preferences.getBoolean("upper_tax_type", upper_tax_type_rb.isChecked()));
        lower_tax_type_rb.setChecked(shared_preferences.getBoolean("lower_tax_type", lower_tax_type_rb.isChecked()));
    }
}