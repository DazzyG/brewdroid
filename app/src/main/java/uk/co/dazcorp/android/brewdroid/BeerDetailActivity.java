package uk.co.dazcorp.android.brewdroid;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uk.co.dazcorp.android.brewdroid.data.Beer;

public class BeerDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BEER_KEY = "beer_key";
    private static final String TAG = "BEER";

    private String mBeerKey;

    private DatabaseReference mBeerReference;
    private Beer mBeer;
    private ValueEventListener mBeerListener;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add fav beer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mBeerKey = getIntent().getStringExtra(EXTRA_BEER_KEY);
        if (mBeerKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_BEER_KEY");
        }

        mBeerReference = FirebaseDatabase.getInstance().getReference().child(mBeerKey);
    }

    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mBeer = dataSnapshot.getValue(Beer.class);
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

                Toast.makeText(BeerDetailActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mBeerReference.addValueEventListener(postListener);

        // Keep copy of post listener so we can remove it when app stops
        mBeerListener = postListener;
    }

    private void updateUI() {
        mCollapsingToolbarLayout.setTitle(mBeer.getName());
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mBeerReference != null) {
            mBeerReference.removeEventListener(mBeerListener);
        }
    }
}
