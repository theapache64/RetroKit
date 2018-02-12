package com.theah64.retrokit.activities;

import android.app.SearchManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.theah64.retrokit.R;
import com.theah64.retrokit.utils.ProgressManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shifar on 15/9/16.
 */
public abstract class BaseSearchableActivity<MODEL, RESPONSE_DATA, API_INTERFACE> extends BaseRecyclerViewActivity<MODEL, RESPONSE_DATA, API_INTERFACE> implements SearchView.OnQueryTextListener {

    private static final String X = BaseSearchableActivity.class.getSimpleName();
    final List<MODEL> filteredList = new ArrayList<>();
    private final List<MODEL> fullTempDataList = new ArrayList<>();
    private SearchView searchView;
    private MenuItem miSearch;

    public boolean isSuggestionEnabled() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        miSearch = menu.findItem(R.id.miSearch);

        miSearch.setIcon(new IconDrawable(this, FontAwesomeIcons.fa_search_minus).colorRes(android.R.color.white).sizeDp(32));

        searchView = (SearchView) MenuItemCompat.getActionView(miSearch);
        searchView.setQueryHint(getQueryHint());
        searchView.setOnQueryTextListener(this);


        if (isSuggestionEnabled()) {

            searchView.setSuggestionsAdapter(new SimpleCursorAdapter(
                    this, R.layout.simple_list_item_1_light, null,
                    new String[]{SearchManager.SUGGEST_COLUMN_TEXT_1},
                    new int[]{R.id.tvSug}));

            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {

                @Override
                public boolean onSuggestionSelect(int position) {

                    Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
                    String term = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
                    searchView.setQuery(term, true);
                    cursor.close();

                    return true;
                }

                @Override
                public boolean onSuggestionClick(int position) {

                    return onSuggestionSelect(position);
                }
            });
        }
        return true;
    }


    private static final String[] sAutocompleteColNames = new String[]{
            BaseColumns._ID,                         // necessary for adapter
            SearchManager.SUGGEST_COLUMN_TEXT_1      // the full search term
    };


    @Override
    public void setFullDataList(List<MODEL> fullData) {
        super.setFullDataList(fullData);

        fullTempDataList.clear();
        if (fullData.isEmpty()) {
            miSearch.setVisible(false);
        } else {

            miSearch.setVisible(true);
            fullTempDataList.addAll(fullData);
        }

        //Setting search element
        setSuggestions(fullData);

    }

    public List<MODEL> getFullTempDataList() {
        return fullTempDataList;
    }

    protected CharSequence getQueryHint() {
        return getString(R.string.Search);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        if (!query.isEmpty()) {
            final List<MODEL> filteredData = filter(query);
            if (!filteredData.isEmpty()) {

                setSuggestions(filteredData);

                getAdapter().setData(filteredData);
                getAdapter().notifyDataSetChanged();
                progressMan.showMainView();
            } else {
                progressMan.showError(ProgressManager.ERROR_TYPE_EMPTY, getErrorOnEmptyData());
                if (isSuggestionEnabled()) {
                    searchView.getSuggestionsAdapter().changeCursor(null);
                }
            }
        } else {

            filteredList.clear();

            if (!fullTempDataList.isEmpty()) {
                //Show full data
                getAdapter().setData(fullTempDataList);
                getAdapter().notifyDataSetChanged();
                progressMan.showMainView();
            }

            setSuggestions(fullTempDataList);
        }


        return true;
    }

    protected String getErrorOnEmptyData() {
        return getString(R.string.No_data_found);
    }


    private List<MODEL> filter(String newText) {

        newText = newText.trim().toLowerCase();
        filteredList.clear();

        for (final MODEL item : getFullDataList()) {
            if (getSearchableProperty(item).toLowerCase().contains(newText)) {
                filteredList.add(item);
            }
        }

        return filteredList;
    }

    public List<MODEL> getFilteredList() {
        return filteredList;
    }

    protected abstract String getSearchableProperty(MODEL t);

    public MODEL getClickedItem(int position) {
        return !getFilteredList().isEmpty() ? getFilteredList().get(position) : getFullDataList().get(position);
    }


    public void setSuggestions(List<MODEL> suggestions) {
        if (isSuggestionEnabled()) {
            MatrixCursor matrixCursor = new MatrixCursor(sAutocompleteColNames);
            for (int i = 0; i < suggestions.size(); i++) {
                matrixCursor.addRow(new Object[]{i, getSearchableProperty(suggestions.get(i))});
            }
            searchView.getSuggestionsAdapter().changeCursor(matrixCursor);
        }
    }
}
