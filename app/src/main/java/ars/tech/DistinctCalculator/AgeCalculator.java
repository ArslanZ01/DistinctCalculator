package ars.tech.DistinctCalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AgeCalculator extends AppCompatActivity {
    MaterialDatePicker material_date_picker = MaterialDatePicker.Builder.datePicker().build();
    DateTime birth_date, to_date;
    Button calculate_b;
    EditText age_et, result_et, birth_date_et, to_date_et, current_et;
    CheckBox toggle_input_date_cb, toggle_input_to_date_cb;
    int years, months, days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);

        calculate_b = (Button) findViewById(R.id.calculate_b);

        age_et = (EditText) findViewById(R.id.age_et);
        result_et = (EditText) findViewById(R.id.result_et);
        birth_date_et = (EditText) findViewById(R.id.birth_date_et);
        to_date_et = (EditText) findViewById(R.id.to_date_et);

        toggle_input_date_cb = (CheckBox) findViewById(R.id.toggle_input_date_cb);
        toggle_input_to_date_cb = (CheckBox) findViewById(R.id.toggle_input_to_date_cb);

        birth_date_et.setText(new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date()));
        to_date_et.setText(new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date()));

        toggle_input_date_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                birth_date_et.setEnabled(toggle_input_date_cb.isChecked());
                to_date_et.setEnabled(toggle_input_date_cb.isChecked() && toggle_input_to_date_cb.isChecked());
                toggle_input_to_date_cb.setEnabled(toggle_input_date_cb.isChecked());
            }
        });

        toggle_input_to_date_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                to_date_et.setEnabled(toggle_input_to_date_cb.isChecked());
            }
        });

        birth_date_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_et = birth_date_et;
                material_date_picker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        to_date_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_et = to_date_et;
                material_date_picker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        calculate_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birth_date = DateTimeFormat.forPattern("MMM dd, yyyy").parseDateTime(birth_date_et.getText().toString());
                to_date = DateTimeFormat.forPattern("MMM dd, yyyy").parseDateTime(to_date_et.getText().toString());

                years = Years.yearsBetween(
                        new LocalDate(birth_date.getYear(), birth_date.getMonthOfYear(), birth_date.getDayOfMonth()),
                        new LocalDate(to_date.getYear(), to_date.getMonthOfYear(), to_date.getDayOfMonth())
                ).getYears();
                months = Months.monthsBetween(
                        new LocalDate(birth_date.getYear(), birth_date.getMonthOfYear(), birth_date.getDayOfMonth()),
                        new LocalDate(to_date.minusYears(years).getYear(), to_date.getMonthOfYear(), to_date.getDayOfMonth())
                ).getMonths();
                days = Days.daysBetween(
                        new LocalDate(birth_date.getYear(), birth_date.getMonthOfYear(), birth_date.getDayOfMonth()),
                        new LocalDate(to_date.minusYears(years).getYear(), to_date.minusMonths(months).getMonthOfYear(), to_date.getDayOfMonth())
                ).getDays();
                if (days >= 365)
                    days = days - 365;
                age_et.setText(years + " Year(s), " + months + " Month(s) and " + days + " Day(s)");

                months = Months.monthsBetween(
                        new LocalDate(to_date.getYear(), to_date.getMonthOfYear(), to_date.getDayOfMonth()),
                        new LocalDate(birth_date.plusYears(years+1).getYear(), birth_date.getMonthOfYear(), birth_date.getDayOfMonth())
                ).getMonths();
                days = Days.daysBetween(
                        new LocalDate(to_date.getYear(), to_date.getMonthOfYear(), to_date.getDayOfMonth()),
                        new LocalDate(birth_date.plusYears(years+1).getYear(), birth_date.minusMonths(months).getMonthOfYear(), birth_date.getDayOfMonth())
                ).getDays();
                if (days >= 365)
                    days = days - 365;
                result_et.setText((years+1) + " Year(s) in " + months + " Month(s) and " + days + " Day(s)");
            }
        });

        material_date_picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                current_et.setText(material_date_picker.getHeaderText());
            }
        });
    }
}