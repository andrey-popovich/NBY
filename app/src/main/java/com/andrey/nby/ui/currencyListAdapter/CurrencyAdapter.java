package com.andrey.nby.ui.currencyListAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrey.nby.App;
import com.andrey.nby.R;
import com.andrey.nby.data.repositories.Currency;

import javax.inject.Inject;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class CurrencyAdapter extends RealmRecyclerViewAdapter<Currency, CurrencyAdapter.CurrencyViewHolder> {

    @Inject
    CurrencyPresenter mCurrencyPresenter;

    public CurrencyAdapter(RealmResults<Currency> currencies, Context context) {
        super(context, currencies, true);
        App.getComponent().inject(this);
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new CurrencyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CurrencyViewHolder holder, int position) {
        final Currency currency = getItem(position);
        if (currency != null) {
            holder.txt.setText(currency.getTxt());
            holder.cc.setText(currency.getCc());
            holder.rate.setText(Double.toString(currency.getRate()));
            holder.date.setText(currency.getExchangeDate());
            if (currency.isFavorite()) {
                holder.favorite.setImageResource(R.drawable.star_yellow_36dp);
            } else {
                holder.favorite.setImageResource(R.drawable.star_white_36dp);
            }
            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrencyPresenter.onFavoriteClick(currency, view, holder);
                }
            });
        }
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt;
        public TextView cc;
        public TextView rate;
        public TextView date;
        public ImageView favorite;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            txt = (TextView) itemView.findViewById(R.id.currency_txt);
            cc = (TextView) itemView.findViewById(R.id.currency_cc);
            rate = (TextView) itemView.findViewById(R.id.currency_rate);
            date = (TextView) itemView.findViewById(R.id.currency_date);
            favorite = (ImageView) itemView.findViewById(R.id.favorite);
        }
    }
}
