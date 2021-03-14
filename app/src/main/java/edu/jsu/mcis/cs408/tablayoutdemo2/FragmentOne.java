package edu.jsu.mcis.cs408.tablayoutdemo2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment implements TabFragment {

    private final String title = "Fragment One";
    private TextView outputField;
    private Calculator calculator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        calculator = new Calculator(this);

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //outputField = (TextView) view.findViewById(R.id.outputField1); COMMENT THIS OUT
        //view.findViewById(R.id.button).setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        //outputField.setText(R.string.output_text_1);

        String button = ((Button) v).getText().toString();       // get input button text
        calculator.process(button);                              // pass input to Calculator for processing
        TextView display = (TextView)getView().findViewById(R.id.display); // acquire reference to display TextView
        display.setText(calculator.getDisplay());                // show updated display buffer from Calculator
    }

    public String getTabTitle() { return title; }


}