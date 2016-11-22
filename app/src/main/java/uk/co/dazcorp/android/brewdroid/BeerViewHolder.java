package uk.co.dazcorp.android.brewdroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.dazcorp.android.brewdroid.data.Beer;

public class BeerViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;

    public BeerViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.beer_title);
        authorView = (TextView) itemView.findViewById(R.id.beer_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.post_num_stars);
        bodyView = (TextView) itemView.findViewById(R.id.beer_body);
    }

    public void bindToPost(Beer beer) {
        titleView.setText(beer.getName());
        authorView.setText(beer.getContributed_by());
        numStarsView.setText(String.valueOf(beer.getAbv()));
        bodyView.setText(beer.getDescription());
    }
}
