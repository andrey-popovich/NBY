package com.andrey.nby.ui.mainScreen;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andrey.nby.App;
import com.andrey.nby.R;
import com.andrey.nby.data.DataManager;
import com.andrey.nby.data.repositories.Currency;
import com.andrey.nby.ui.currencyListAdapter.CurrencyAdapter;

import javax.inject.Inject;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainScreenView {

//    @Inject
//    MainScreenPresenter mPresenter;

    @Inject
    DataManager mDataManager;

    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);

        getCurrencyList();
        initAdapter();
    }

    private void initAdapter() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new CurrencyAdapter(realm.where(Currency.class).findAll(), this));
    }

    public void getCurrencyList() {
        mDataManager.updateDatabase(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }
}
