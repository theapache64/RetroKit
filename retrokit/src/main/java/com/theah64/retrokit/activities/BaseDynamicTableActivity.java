package com.theah64.retrokit.activities;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.evrencoskun.tableview.TableView;
import com.theah64.retrokit.R;
import com.theah64.retrokit.utils.tableview.BaseTableAdapter;
import com.theah64.retrokit.utils.tableview.MyTableViewListener;
import com.theah64.retrokit.utils.tableview.model.CellModel;
import com.theah64.retrokit.utils.tableview.model.ColumnHeaderModel;
import com.theah64.retrokit.utils.tableview.model.RowHeaderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 26/1/18.
 */

public abstract class BaseDynamicTableActivity<DATA, MODEL, APIINTERFACE> extends BaseDynamicActivity<DATA, APIINTERFACE> {


    protected static final String KEY_GHOST_KEYS = "ghost_keys";

    private BaseTableAdapter adapter;

    @Override
    public boolean isRefreshable() {
        return false;
    }


    @Override
    protected void setup() {

        final String[] ghostColumns = getIntent().getStringArrayExtra(KEY_GHOST_KEYS);
        adapter = new BaseTableAdapter(this);
        adapter.setGhostColumns(ghostColumns);
        getTableView().setAdapter(adapter);

        // Create listener
        getTableView().setTableViewListener(new MyTableViewListener(getTableView()));

        super.setup();
    }

    @Override
    protected void onSuccess(DATA response, boolean isClearList) {

        final List<List<CellModel>> rows = new ArrayList<>();
        for (final MODEL d : getRowModels(response)) {
            rows.add(getRow(getRow(d)));
        }


        final List<RowHeaderModel> rowHeader = new ArrayList<>();
        for (int i = 0; i < rows.size(); ) {
            rowHeader.add(new RowHeaderModel(String.valueOf(++i)));
        }

        if (rows.isEmpty()) {
            Toast.makeText(this, R.string.No_data_found, Toast.LENGTH_SHORT).show();
        }

        adapter.setAllItems(getHeaderModels(getHeaders()), rowHeader, rows);
    }

    protected abstract List<MODEL> getRowModels(DATA response);

    protected abstract Object[] getRow(MODEL model);

    protected abstract int[] getHeaders();

    private List<CellModel> getRow(Object[] rowValues) {

        final List<CellModel> row = new ArrayList<>();

        for (Object rowValue : rowValues) {
            row.add(new CellModel(rowValue));
        }

        return row;
    }

    public List<ColumnHeaderModel> getHeaderModels(@StringRes int... stringRes) {
        final List<ColumnHeaderModel> columnHeaderModels = new ArrayList<>();
        for (int string : stringRes) {
            columnHeaderModels.add(new ColumnHeaderModel(getString(string)));
        }
        return columnHeaderModels;
    }

    protected abstract TableView getTableView();
}
