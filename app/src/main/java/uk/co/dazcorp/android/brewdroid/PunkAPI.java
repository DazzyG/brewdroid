package uk.co.dazcorp.android.brewdroid;

import retrofit2.Call;
import retrofit2.http.GET;
import uk.co.dazcorp.android.brewdroid.data.Beer;

/**
 *
 */

public interface PunkAPI {

    @GET("beers/random")
    Call<Beer> randomBeer();
}
