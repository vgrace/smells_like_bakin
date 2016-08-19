package se.cnet.smellslikabakin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * Created by Vivi on 2016-08-19.
 * This class is made abstract since it does not need to be instantiated
 */
public abstract class CheckboxesFragment extends Fragment{
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";
    private CheckBox[] mCheckBoxes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX); //The selected recipe
        
        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false); // False not to connect with container
        // Ref to linear layout
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkboxesLayout);
        // Get contents separated by `
        String[] contents = getContext(index);

        mCheckBoxes = new CheckBox[contents.length];
        // Restored checked states
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if(savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null){
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }
        setUpCheckBoxes(contents, linearLayout, checkedBoxes);

        return view;
    }

    public abstract String[] getContext(int index); // Abstract requires to implement this method

    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] checkedBoxes) {
        int i = 0;
        for(String content : contents){
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(content);
            container.addView(mCheckBoxes[i]);
            if(checkedBoxes[i]){
                mCheckBoxes[i].toggle();// Restoring checked
            }
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Save checkboxes state
        boolean[] stateOfCheckboxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for(CheckBox checkBox : mCheckBoxes){
            stateOfCheckboxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckboxes);
        super.onSaveInstanceState(outState);
    }
}
