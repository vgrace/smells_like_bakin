package se.cnet.smellslikabakin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vivi on 2016-08-18.
 */
public class ViewPagerFragment extends Fragment{

    public static final String KEY_RECIPE_INDEX = "recipe_index";// Key to be stored in bundle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);// Get index from bundle created in MainActivity
        getActivity().setTitle(Recipes.names[index]); // Set the recipe as title
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false); //false - we don't want android it add it to the container

        // Ingredients fragment + bundle
        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);
        ingredientsFragment.setArguments(bundle);

        // Directions fragment + bundle
        final DirectionsFragment directionsFragment = new DirectionsFragment();
        bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);
        directionsFragment.setArguments(bundle);


        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        //When we are dealing with fragments within fragments we need to use childFragments

        viewPager.setAdapter(new android.support.v13.app.FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? ingredientsFragment : directionsFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
              return position == 0 ? "Ingredients" : "Directions";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        // Setup Tabs
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));// Set the appname back (instead of the recipe title)
    }
}
