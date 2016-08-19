package se.cnet.smellslikabakin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

//AppCompatActivity
public class MainActivity extends AppCompatActivity
        implements ListFragment.OnRecipeSelectedInterface, GridFragment.OnRecipeSelectedInterface{
    public static final String LIST_FRAGMENT = "list_fragment";// List with all recipes
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";// Shows one recipe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the user is using a tablet
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if(!isTablet){
            ListFragment savedFragment = (ListFragment) getFragmentManager().findFragmentByTag(LIST_FRAGMENT); //.findFragmentById(R.id.placeHolder);

            //if(savedInstanceState == null) works to
            if(savedFragment == null) {
                ListFragment fragment = new ListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);//Third parameter is the tag for the fragment and can be used to find the fragment by .findFragmentByTag()
                fragmentTransaction.commit();
            }
        }
        else{
            GridFragment savedFragment = (GridFragment) getFragmentManager().findFragmentByTag(LIST_FRAGMENT); //.findFragmentById(R.id.placeHolder);

            //if(savedInstanceState == null) works to
            if(savedFragment == null) {
                GridFragment fragment = new GridFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);//Third parameter is the tag for the fragment and can be used to find the fragment by .findFragmentByTag()
                fragmentTransaction.commit();
            }
        }

    }

    @Override
    public void onListRecipeSelected(int index) {
        //Toast.makeText(MainActivity.this, Recipes.names[index], Toast.LENGTH_LONG).show();
        ViewPagerFragment fragment = new ViewPagerFragment();
        // Pass recipe index to ViewPagerFragment
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);// Set bundle
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);// Makes the back-button work properly instead of quiting the app
        fragmentTransaction.commit();
    }

    @Override
    public void onGridRecipeSelected(int index) {
        DualPaneFragment fragment = new DualPaneFragment();
        // Pass recipe index to ViewPagerFragment
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);// Set bundle
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);// Makes the back-button work properly instead of quiting the app
        fragmentTransaction.commit();
    }
}
