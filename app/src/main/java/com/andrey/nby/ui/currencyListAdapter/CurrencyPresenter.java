package com.andrey.nby.ui.currencyListAdapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.andrey.nby.R;
import com.andrey.nby.data.repositories.Currency;
import com.andrey.nby.di.component.ApplicationComponent;

import io.realm.Realm;

public class CurrencyPresenter implements CurrencyListView {

    private static final String TAG = "CurrencyPresenter";

    public CurrencyPresenter(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onFavoriteClick(final Currency currency, final View view, final CurrencyAdapter.CurrencyViewHolder holder) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    currency.setFavorite(!currency.isFavorite());
                    realm.insertOrUpdate(currency);

                    if (currency.isFavorite()) {
                        holder.favorite.setImageResource(R.drawable.star_yellow_36dp);
                    } else {
                        holder.favorite.setImageResource(R.drawable.star_white_36dp);
                    }

                    Log.i(TAG, "isFavorite: " + currency.isFavorite());
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
