package se.cnet.smellslikabakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vivi on 2016-08-19.
 */
public class DualPaneFragment extends android.app.Fragment {
    private static final String INGREDIENTS_FRAGMENT = "ingredients_fragment";
    private static final String DIRECTIONS_FRAGMENT = "directions_fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);// Get index from bundle created in MainActivity
        getActivity().setTitle(Recipes.names[index]); // Set the recipe as title
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false); //false - we don't want android it add it to the container

        android.app.FragmentManager fragmentManager = getChildFragmentManager();
        //FragmentManager fragmentManager = getChildFragmentManager();
        
        // Ingredients
        IngredientsFragment savedIngredientsFragment = (IngredientsFragment) fragmentManager.findFragmentByTag(INGREDIENTS_FRAGMENT);

        if(savedIngredientsFragment == null){
            //Create new
            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            ingredientsFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.leftPlaceholder, ingredientsFragment, INGREDIENTS_FRAGMENT).commit();
        }

        //Directions
        DirectionsFragment savedDirectionsFragment = (DirectionsFragment) fragmentManager.findFragmentByTag(DIRECTIONS_FRAGMENT);

        if(savedDirectionsFragment == null){
            //Create new
            DirectionsFragment directionsFragment = new DirectionsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            directionsFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.rightPlaceholder, directionsFragment, DIRECTIONS_FRAGMENT).commit();
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));// Set the appname back (instead of the recipe title)
    }
}
