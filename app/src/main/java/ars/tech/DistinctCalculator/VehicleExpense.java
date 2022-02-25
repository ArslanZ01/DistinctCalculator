package ars.tech.DistinctCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

public class VehicleExpense extends AppCompatActivity {
    EditText result_et, kilometer_et, average_et, fuel_price_et, oil_kilometer_et, oil_price_et, wash_kilometer_et, wash_price_et, toll_kilometer_et, toll_price_et;
    CheckBox edit_average_cb, edit_oil_cb, edit_wash_cb, toggle_average_cb, toggle_oil_cb, toggle_wash_cb, toggle_toll_cb, edit_toll_cb, toggle_save_cb;
    Button calculate_b, save_b;
    Float result;
    SharedPreferences shared_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_expense);

        shared_preferences = getSharedPreferences("vehicle_expense", MODE_PRIVATE);

        result_et = findViewById(R.id.result_et);
        kilometer_et = findViewById(R.id.kilometer_et);
        average_et = findViewById(R.id.average_et);
        fuel_price_et = findViewById(R.id.fuel_price_et);
        oil_kilometer_et = findViewById(R.id.oil_kilometer_et);
        oil_price_et = findViewById(R.id.oil_price_et);
        wash_kilometer_et = findViewById(R.id.wash_kilometer_et);
        wash_price_et = findViewById(R.id.wash_price_et);
        toll_kilometer_et = findViewById(R.id.toll_kilometer_et);
        toll_price_et = findViewById(R.id.toll_price_et);

        edit_average_cb = findViewById(R.id.edit_average_cb);
        toggle_average_cb = findViewById(R.id.toggle_average_cb);
        edit_oil_cb = findViewById(R.id.edit_oil_cb);
        toggle_oil_cb = findViewById(R.id.toggle_oil_cb);
        edit_wash_cb = findViewById(R.id.edit_wash_cb);
        toggle_wash_cb = findViewById(R.id.toggle_wash_cb);
        edit_toll_cb = findViewById(R.id.edit_toll_cb);
        toggle_toll_cb = findViewById(R.id.toggle_toll_cb);

        toggle_save_cb = findViewById(R.id.toggle_save_cb);

        calculate_b = findViewById(R.id.calculate_b);
        save_b = findViewById(R.id.save_b);

        calculate_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0f;

                if (kilometer_et.getText().toString().equals(""))
                    kilometer_et.setText("100");

                if (toggle_average_cb.isChecked()) {
                    if (fuel_price_et.getText().toString().equals("") || average_et.getText().toString().equals(""))
                        toggle_average_cb.setChecked(false);
                    else
                        result = result + ((Float.parseFloat(fuel_price_et.getText().toString()) / Float.parseFloat(average_et.getText().toString())) * Float.parseFloat(kilometer_et.getText().toString()));
                }

                if (toggle_oil_cb.isChecked()) {
                    if (oil_price_et.getText().toString().equals("") || oil_kilometer_et.getText().toString().equals(""))
                        toggle_oil_cb.setChecked(false);
                    else
                        result = result + ((Float.parseFloat(oil_price_et.getText().toString()) / Float.parseFloat(oil_kilometer_et.getText().toString())) * Float.parseFloat(kilometer_et.getText().toString()));
                }

                if (toggle_wash_cb.isChecked()) {
                    if (wash_price_et.getText().toString().equals("") || wash_kilometer_et.getText().toString().equals(""))
                        toggle_wash_cb.setChecked(false);
                    else
                        result = result + ((Float.parseFloat(wash_price_et.getText().toString()) / Float.parseFloat(wash_kilometer_et.getText().toString())) * Float.parseFloat(kilometer_et.getText().toString()));
                }

                if (toggle_toll_cb.isChecked()) {
                    if (toll_price_et.getText().toString().equals("") || toll_kilometer_et.getText().toString().equals(""))
                        toggle_toll_cb.setChecked(false);
                    else
                        result = result + ((Float.parseFloat(toll_price_et.getText().toString()) / Float.parseFloat(toll_kilometer_et.getText().toString())) * Float.parseFloat(kilometer_et.getText().toString()));
                }

                result_et.setText(result.toString());
            }
        });

        edit_average_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (edit_average_cb.isChecked()) {
                    findViewById(R.id.average_et).setEnabled(true);
                    findViewById(R.id.fuel_price_et).setEnabled(true);
                }
                else {
                    findViewById(R.id.average_et).setEnabled(false);
                    findViewById(R.id.fuel_price_et).setEnabled(false);
                }
            }
        });
        toggle_average_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggle_average_cb.isChecked()) {
                    edit_average_cb.setEnabled(true);
                    if (edit_average_cb.isChecked()) {
                        findViewById(R.id.average_et).setEnabled(true);
                        findViewById(R.id.fuel_price_et).setEnabled(true);
                    }
                }
                else {
                    edit_average_cb.setEnabled(false);
                    findViewById(R.id.average_et).setEnabled(false);
                    findViewById(R.id.fuel_price_et).setEnabled(false);
                }
            }
        });

        edit_oil_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (edit_oil_cb.isChecked()) {
                    findViewById(R.id.oil_kilometer_et).setEnabled(true);
                    findViewById(R.id.oil_price_et).setEnabled(true);
                }
                else {
                    findViewById(R.id.oil_kilometer_et).setEnabled(false);
                    findViewById(R.id.oil_price_et).setEnabled(false);
                }
            }
        });
        toggle_oil_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggle_oil_cb.isChecked()) {
                    edit_oil_cb.setEnabled(true);
                    if (edit_oil_cb.isChecked()) {
                        findViewById(R.id.oil_kilometer_et).setEnabled(true);
                        findViewById(R.id.oil_price_et).setEnabled(true);
                    }
                }
                else {
                    edit_oil_cb.setEnabled(false);
                    findViewById(R.id.oil_kilometer_et).setEnabled(false);
                    findViewById(R.id.oil_price_et).setEnabled(false);
                }
            }
        });

        edit_wash_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (edit_wash_cb.isChecked()) {
                    findViewById(R.id.wash_kilometer_et).setEnabled(true);
                    findViewById(R.id.wash_price_et).setEnabled(true);
                }
                else {
                    findViewById(R.id.wash_kilometer_et).setEnabled(false);
                    findViewById(R.id.wash_price_et).setEnabled(false);
                }
            }
        });
        toggle_wash_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggle_wash_cb.isChecked()) {
                    edit_wash_cb.setEnabled(true);
                    if (edit_wash_cb.isChecked()) {
                        findViewById(R.id.wash_kilometer_et).setEnabled(true);
                        findViewById(R.id.wash_price_et).setEnabled(true);
                    }
                }
                else {
                    edit_wash_cb.setEnabled(false);
                    findViewById(R.id.wash_kilometer_et).setEnabled(false);
                    findViewById(R.id.wash_price_et).setEnabled(false);
                }
            }
        });

        edit_toll_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (edit_toll_cb.isChecked()) {
                    findViewById(R.id.toll_kilometer_et).setEnabled(true);
                    findViewById(R.id.toll_price_et).setEnabled(true);
                }
                else {
                    findViewById(R.id.toll_kilometer_et).setEnabled(false);
                    findViewById(R.id.toll_price_et).setEnabled(false);
                }
            }
        });
        toggle_toll_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggle_toll_cb.isChecked()) {
                    edit_toll_cb.setEnabled(true);
                    if (edit_toll_cb.isChecked()) {
                        findViewById(R.id.toll_kilometer_et).setEnabled(true);
                        findViewById(R.id.toll_price_et).setEnabled(true);
                    }
                }
                else {
                    edit_toll_cb.setEnabled(false);
                    findViewById(R.id.toll_kilometer_et).setEnabled(false);
                    findViewById(R.id.toll_price_et).setEnabled(false);
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
                    shared_preferences_editor.putString("result", result_et.getText().toString());
                    shared_preferences_editor.putString("kilometer", kilometer_et.getText().toString());
                    shared_preferences_editor.putString("average", average_et.getText().toString());
                    shared_preferences_editor.putString("fuel_price", fuel_price_et.getText().toString());
                    shared_preferences_editor.putString("oil_kilometer", oil_kilometer_et.getText().toString());
                    shared_preferences_editor.putString("oil_price", oil_price_et.getText().toString());
                    shared_preferences_editor.putString("wash_kilometer", wash_kilometer_et.getText().toString());
                    shared_preferences_editor.putString("wash_price", wash_price_et.getText().toString());
                    shared_preferences_editor.putString("toll_kilometer", toll_kilometer_et.getText().toString());
                    shared_preferences_editor.putString("toll_price", toll_price_et.getText().toString());

                    shared_preferences_editor.putBoolean("edit_average", edit_average_cb.isChecked());
                    shared_preferences_editor.putBoolean("toggle_average", toggle_average_cb.isChecked());
                    shared_preferences_editor.putBoolean("edit_oil", edit_oil_cb.isChecked());
                    shared_preferences_editor.putBoolean("toggle_oil", toggle_oil_cb.isChecked());
                    shared_preferences_editor.putBoolean("edit_wash", edit_wash_cb.isChecked());
                    shared_preferences_editor.putBoolean("toggle_wash", toggle_wash_cb.isChecked());
                    shared_preferences_editor.putBoolean("edit_toll", edit_toll_cb.isChecked());
                    shared_preferences_editor.putBoolean("toggle_toll", toggle_toll_cb.isChecked());
                }
                else {
                    result_et.setText("");
                    kilometer_et.setText("");
                    average_et.setText("");
                    fuel_price_et.setText("");
                    oil_kilometer_et.setText("");
                    oil_price_et.setText("");
                    wash_kilometer_et.setText("");
                    wash_price_et.setText("");
                    toll_kilometer_et.setText("");
                    toll_price_et.setText("");

                    shared_preferences_editor.remove("result");
                    shared_preferences_editor.remove("kilometer");
                    shared_preferences_editor.remove("average");
                    shared_preferences_editor.remove("fuel_price");
                    shared_preferences_editor.remove("oil_kilometer");
                    shared_preferences_editor.remove("oil_price");
                    shared_preferences_editor.remove("wash_kilometer");
                    shared_preferences_editor.remove("wash_price");
                    shared_preferences_editor.remove("toll_kilometer");
                    shared_preferences_editor.remove("toll_price");

                    shared_preferences_editor.remove("edit_average");
                    shared_preferences_editor.remove("toggle_average");
                    shared_preferences_editor.remove("edit_oil");
                    shared_preferences_editor.remove("toggle_oil");
                    shared_preferences_editor.remove("edit_wash");
                    shared_preferences_editor.remove("toggle_wash");
                    shared_preferences_editor.remove("edit_toll");
                    shared_preferences_editor.remove("toggle_toll");
                }

                shared_preferences_editor.apply();
            }
        });

        result_et.setText(shared_preferences.getString("result", result_et.getText().toString()));
        kilometer_et.setText(shared_preferences.getString("kilometer", kilometer_et.getText().toString()));
        average_et.setText(shared_preferences.getString("average", average_et.getText().toString()));
        fuel_price_et.setText(shared_preferences.getString("fuel_price", fuel_price_et.getText().toString()));
        oil_kilometer_et.setText(shared_preferences.getString("oil_kilometer", oil_kilometer_et.getText().toString()));
        oil_price_et.setText(shared_preferences.getString("oil_price", oil_price_et.getText().toString()));
        wash_kilometer_et.setText(shared_preferences.getString("wash_kilometer", wash_kilometer_et.getText().toString()));
        wash_price_et.setText(shared_preferences.getString("wash_price", wash_price_et.getText().toString()));
        toll_kilometer_et.setText(shared_preferences.getString("toll_kilometer", toll_kilometer_et.getText().toString()));
        toll_price_et.setText(shared_preferences.getString("toll_price", toll_price_et.getText().toString()));

        edit_average_cb.setChecked(shared_preferences.getBoolean("edit_average", edit_average_cb.isChecked()));
        toggle_average_cb.setChecked(shared_preferences.getBoolean("toggle_average", toggle_average_cb.isChecked()));
        edit_oil_cb.setChecked(shared_preferences.getBoolean("edit_oil", edit_oil_cb.isChecked()));
        toggle_oil_cb.setChecked(shared_preferences.getBoolean("toggle_oil", toggle_oil_cb.isChecked()));
        edit_wash_cb.setChecked(shared_preferences.getBoolean("edit_wash", edit_wash_cb.isChecked()));
        toggle_wash_cb.setChecked(shared_preferences.getBoolean("toggle_wash", toggle_wash_cb.isChecked()));
        edit_toll_cb.setChecked(shared_preferences.getBoolean("edit_toll", edit_toll_cb.isChecked()));
        toggle_toll_cb.setChecked(shared_preferences.getBoolean("toggle_toll", toggle_toll_cb.isChecked()));
    }
}