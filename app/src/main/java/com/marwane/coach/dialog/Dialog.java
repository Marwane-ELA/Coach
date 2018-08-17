package com.marwane.coach.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.marwane.coach.R;

/**
 * Bronnen:
 * https://youtu.be/ARezg1D9Zd0
 * https://developer.android.com/guide/topics/ui/dialogs
 */
public class Dialog extends AppCompatDialogFragment {


    private TextInputLayout editTextFood;
    private TextInputLayout editTextCal;
    private DialogListener listener;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Add Food")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String food = editTextFood.getEditText().getText().toString().trim();
                        Integer cal = 0;
                        if(!editTextCal.getEditText().getText().toString().trim().equals("")) {
                            cal = Integer.parseInt(editTextCal.getEditText().getText().toString().trim());
                        }

                        listener.applyAdd(food,cal);
                    }
                });

        editTextFood = view.findViewById(R.id.edit_task);
        editTextCal = view.findViewById(R.id.edit_calorie);

        return builder.create();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener{
        void applyAdd(String food,Integer calorie);
    }
}
