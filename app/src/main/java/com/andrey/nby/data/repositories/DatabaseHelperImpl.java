package com.andrey.nby.data.repositories;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.andrey.nby.R;
import com.andrey.nby.di.component.ApplicationComponent;
import com.andrey.nby.service.NBUClient;
import com.andrey.nby.service.NBUService;
import com.andrey.nby.ui.mainScreen.MainActivity;
import com.andrey.nby.ui.mainScreen.MainScreenPresenter;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseHelperImpl implements DatabaseHelper {

    private static final String TAG = "DatabaseHelperImpl";

    public DatabaseHelperImpl(ApplicationComponent component) {
        component.inject(this);
    }

    Animation loadingAnimation;

    @Override
    public void deleteCurrency(Currency currency, Context context) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.deleteAll();
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        toastMessage(R.string.on_delete, context);
    }

    @Override
    public void updateCurrency(final Context context, final FloatingActionButton fab) {
        loadingAnimation = AnimationUtils.loadAnimation(context, R.anim.loading);
        fab.startAnimation(loadingAnimation);
        NBUService service = NBUClient.getNBUService();
        Call<List<Currency>> call = service.getCurrencyList("");
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, final Response<List<Currency>> response) {
                Log.i(TAG, "onResponse");
                if (response.isSuccessful()) {
                    final List<Currency> currencyList = response.body();
                    if (currencyList.isEmpty()) {
                        toastMessage(R.string.on_failure, context);
                        return;
                    }
                    Realm realm = null;
                    try {
                        Log.i(TAG, "ITEMS SIZE: " + String.valueOf(currencyList.size()));
                        realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                for (int i = 0; i < currencyList.size(); i++) {
                                    Currency currency = currencyList.get(i);

                                    // set currency isFavorite
                                    Currency result = realm.where(Currency.class).equalTo("r030", currency.getR030()).findFirst();
                                    if (result.isFavorite()) {
                                        currency.setFavorite(true);
                                    }

                                    realm.copyToRealmOrUpdate(currency);
                                }
                            }
                        });
                    } finally {
                        if (realm != null) {
                            realm.close();
                        }
                    }
                    fab.clearAnimation();
                    fab.setVisibility(View.GONE);
                }
                toastMessage(R.string.on_response, context);
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getMessage());
                fab.clearAnimation();
                fab.setVisibility(View.GONE);
                toastMessage(R.string.on_failure, context);
            }
        });
    }

    public void toastMessage(int text, Context context) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
