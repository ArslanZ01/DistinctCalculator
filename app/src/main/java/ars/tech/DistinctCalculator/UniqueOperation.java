package ars.tech.DistinctCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.DecimalFormat;

public class UniqueOperation extends AppCompatActivity {
    EditText number_et, other_number_et, other_percentage_et, addition_et, subtraction_et, multiplication_et, division_et, square_et, cube_et, square_root_et, cube_root_et, power_et, inverse_et, reciprocal_et, remainder_et;
    RadioButton other_number_type_rb, other_percentage_type_rb;
    RadioGroup other_type_rg;
    Button calculate_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_operation);

        number_et = (EditText) findViewById(R.id.number_et);
        other_number_et = (EditText) findViewById(R.id.other_number_et);
        other_percentage_et = (EditText) findViewById(R.id.other_percentage_et);
        addition_et = (EditText) findViewById(R.id.addition_et);
        subtraction_et = (EditText) findViewById(R.id.subtraction_et);
        multiplication_et = (EditText) findViewById(R.id.multiplication_et);
        division_et = (EditText) findViewById(R.id.division_et);
        square_et = (EditText) findViewById(R.id.square_et);
        cube_et = (EditText) findViewById(R.id.cube_et);
        square_root_et = (EditText) findViewById(R.id.square_root_et);
        cube_root_et = (EditText) findViewById(R.id.cube_root_et);
        power_et = (EditText) findViewById(R.id.power_et);
        inverse_et = (EditText) findViewById(R.id.inverse_et);
        reciprocal_et = (EditText) findViewById(R.id.reciprocal_et);
        remainder_et = (EditText) findViewById(R.id.remainder_et);

        calculate_b = (Button) findViewById(R.id.calculate_b);

        other_type_rg = (RadioGroup) findViewById(R.id.other_type_rg);
        other_number_type_rb = (RadioButton) findViewById(R.id.other_number_type_rb);
        other_percentage_type_rb = (RadioButton) findViewById(R.id.other_percentage_type_rb);

        calculate_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number_et.getText().toString().equals(""))
                    number_et.setText("100");

                if(other_number_et.getText().toString().equals("") && other_percentage_et.getText().toString().equals(""))
                    other_number_et.setText("0");

                if (other_number_type_rb.isChecked()) {
                    if (!other_number_et.getText().toString().equals(""))
                        other_percentage_et.setText(new DecimalFormat("").format((Float.parseFloat(other_number_et.getText().toString()) * 100) / Float.parseFloat(number_et.getText().toString())));
                    else if (!other_percentage_et.getText().toString().equals(""))
                        other_percentage_type_rb.setChecked(true);
                }
                if (other_percentage_type_rb.isChecked()) {
                    if (!other_percentage_et.getText().toString().equals(""))
                        other_number_et.setText((new DecimalFormat("").format((Float.parseFloat(number_et.getText().toString()) * Float.parseFloat(other_percentage_et.getText().toString())) / 100)));
                    else if (!other_number_et.getText().toString().equals(""))
                        other_number_type_rb.setChecked(true);
                }

                addition_et.setText(new DecimalFormat("").format(Float.parseFloat(number_et.getText().toString()) + Float.parseFloat(other_number_et.getText().toString())));
                subtraction_et.setText(new DecimalFormat("").format(Float.parseFloat(number_et.getText().toString()) - Float.parseFloat(other_number_et.getText().toString())));
                multiplication_et.setText(new DecimalFormat("").format(Float.parseFloat(number_et.getText().toString()) * Float.parseFloat(other_number_et.getText().toString())));
                division_et.setText(new DecimalFormat("").format(Float.parseFloat(number_et.getText().toString()) / Float.parseFloat(other_number_et.getText().toString())));
                square_et.setText(new DecimalFormat("").format(Math.pow(Float.parseFloat(number_et.getText().toString()), 2)));
                cube_et.setText(new DecimalFormat("").format(Math.pow(Float.parseFloat(number_et.getText().toString()), 3)));
                square_root_et.setText(new DecimalFormat("").format(Math.sqrt(Float.parseFloat(number_et.getText().toString()))));
                cube_root_et.setText(new DecimalFormat("").format(Math.cbrt(Float.parseFloat(number_et.getText().toString()))));
                power_et.setText(new DecimalFormat("").format(Math.pow(Float.parseFloat(number_et.getText().toString()), Float.parseFloat(other_number_et.getText().toString()))));
                inverse_et.setText(new DecimalFormat("").format(Math.pow(Float.parseFloat(number_et.getText().toString()), -Float.parseFloat(other_number_et.getText().toString()))));
                reciprocal_et.setText(new DecimalFormat("").format(1 / Float.parseFloat(number_et.getText().toString())));
                remainder_et.setText(new DecimalFormat("").format(Float.parseFloat(number_et.getText().toString()) % Float.parseFloat(other_number_et.getText().toString())));

            }
        });

        other_type_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (other_number_type_rb.isChecked()) {
                    other_number_et.setEnabled(true);
                    other_percentage_et.setEnabled(false);
                }
                if (other_percentage_type_rb.isChecked()) {
                    other_percentage_et.setEnabled(true);
                    other_number_et.setEnabled(false);
                }
            }
        });
    }
}