package edu.jsu.mcis.cs408.tablayoutdemo2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class FragmentTwo extends Fragment implements TabFragment {

    private final String title = "Tip Calculator";
    private TextInputEditText totalBill, tipPercentage, numberPeople, textOutput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalBill = (TextInputEditText) view.findViewById(R.id.totalBill);
        tipPercentage = (TextInputEditText) view.findViewById(R.id.tipPercentage);
        numberPeople = (TextInputEditText) view.findViewById(R.id.numberPeople);
        view.findViewById(R.id.buttonCalculate).setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        //Check inputs for any errors
        if (checkValid(totalBill) == true && checkValid(tipPercentage) == true && checkValid(numberPeople) == true){
            //Calculate the total bill split
            double billDouble = Double.parseDouble(totalBill.getText().toString());
            double tipDouble = Double.parseDouble(tipPercentage.getText().toString());
            double peopleDouble = Double.parseDouble(numberPeople.getText().toString());
            double billSplit = (double)((billDouble + (billDouble * tipDouble / 100)) / peopleDouble);

            //Convert double to a currency-valid value
            int dollars = (int)billSplit;
            double cents = (Math.ceil((billSplit - dollars) * 100)) / 100;
            billSplit = (double)((dollars + cents));

            //Convert double to currency format
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            String textOutput = currencyFormat.format(billSplit);

            //Update the output text to show the calculate split
            TextView output = (TextView) getView().findViewById(R.id.textOutput);
            output.setText("Calculated split: " + textOutput);
        }

    }

    public boolean checkValid(TextInputEditText t){
        //Check if the input is blank, not a number, or 0
        String s = t.getText().toString();

        if (s.trim().isEmpty() || s == null){
            Toast.makeText(getActivity(), "Values cannot be blank!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (s != null && !s.matches("[-+]?\\d*\\.?\\d+")){
            Toast.makeText(getActivity(), "Values must be numeric!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Double.parseDouble(s) == 0){
            Toast.makeText(getActivity(), "Values cannot be zero!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public String getTabTitle() { return title; }

}