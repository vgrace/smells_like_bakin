package se.cnet.smellslikabakin;

/**
 * Created by Vivi on 2016-08-19.
 */
public class IngredientsFragment extends CheckboxesFragment {
    @Override
    public String[] getContext(int index) {
        return Recipes.ingredients[index].split("`");
    }
}
