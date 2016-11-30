package uk.co.dazcorp.android.brewdroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import uk.co.dazcorp.android.brewdroid.data.Beer;

public class BeerViewHolder extends RecyclerView.ViewHolder {

    TextView titleView;
    TextView authorView;
    TextView abv;
    TextView bodyView;

    public BeerViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.beer_title);
        authorView = (TextView) itemView.findViewById(R.id.beer_author);
        abv = (TextView) itemView.findViewById(R.id.beer_abv_value);
        bodyView = (TextView) itemView.findViewById(R.id.beer_body);
    }

    public void bindToPost(Beer beer) {
        titleView.setText(beer.getName());
        authorView.setText(beer.getContributed_by());
        abv.setText(String.format("%s%%", String.valueOf(beer.getAbv())));
        bodyView.setText(beer.getTagline());
    }
}
