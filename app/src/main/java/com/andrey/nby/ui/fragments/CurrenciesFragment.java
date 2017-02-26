package com.andrey.nby.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrey.nby.App;
import com.andrey.nby.R;
import com.andrey.nby.data.prefs.PreferencesHelperImp;
import com.andrey.nby.data.repositories.Currency;
import com.andrey.nby.ui.currencyListAdapter.CurrencyAdapter;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class CurrenciesFragment extends Fragment {

    @Inject
    PreferencesHelperImp mPreferencesHelper;

    private String title;
    private int page;
    private Realm realm;
    private RecyclerView mRecyclerView;

    public static CurrenciesFragment newInstance(int page, String title) {
        CurrenciesFragment fragment = new CurrenciesFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        page = getArguments().getInt("page", 0);
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        Context context = App.getContext();
        realm = Realm.getDefaultInstance();
        RealmResults<Currency> currencies = null;
        String sort = mPreferencesHelper.getSortString();

        switch (page) {
            case 0:
                currencies = realm.where(Currency.class).findAllSorted(sort, Sort.DESCENDING);
                break;
            case 1:
                currencies = realm.where(Currency.class).equalTo("isFavorite", true)
                        .findAllSorted(sort, Sort.DESCENDING);
                break;
            default:
                break;
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewAll);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(new CurrencyAdapter(currencies, context));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}
