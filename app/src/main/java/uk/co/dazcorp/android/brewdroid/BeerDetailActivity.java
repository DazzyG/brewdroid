package uk.co.dazcorp.android.brewdroid;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.dazcorp.android.brewdroid.data.Beer;

public class BeerDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BEER_KEY = "beer_key";
    private static final String TAG = "BEER";

    private String mBeerKey;

    private DatabaseReference mBeerReference;
    private Beer mBeer;
    private ValueEventListener mBeerListener;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.beer_description) TextView mDescriptionTextView;
    @BindView(R.id.beer_first_brewed) TextView mFirstBrewedTextView;
    @BindView(R.id.beer_contributed_by) TextView mContributedByTextView;
    @BindView(R.id.beer_brewers_tips) TextView mBrewersTipsTextView;
    @BindView(R.id.beer_food_pairing) TextView mFoodPairingTextView;


    // Basics views

    @BindView(R.id.volume_value) TextView mVolumeValue;
    @BindView(R.id.volume_unit) TextView mVolumeUnit;
    @BindView(R.id.boil_volume_value) TextView mBoilVolumeValue;
    @BindView(R.id.boil_volume_unit) TextView mBoilVolumeUnit;
    @BindView(R.id.abv_value) TextView mABVValue;
    @BindView(R.id.target_fg_value) TextView mTargetFGValue;
    @BindView(R.id.target_og_value) TextView mTargetOGValue;
    @BindView(R.id.ebc_value) TextView mEBCValue;
    @BindView(R.id.srm_value) TextView mSRMValue;
    @BindView(R.id.ph_value) TextView mPHValue;
    @BindView(R.id.attenuation_level_value) TextView mAttenuationLevelValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = ButterKnife.findById(this, R.id.fab);
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

        mDescriptionTextView.setText(mBeer.getDescription());
        mFirstBrewedTextView.setText(mBeer.getFirst_brewed());
        mContributedByTextView.setText(mBeer.getContributed_by());
        mBrewersTipsTextView.setText(mBeer.getBrewers_tips());

        // Food paring
        StringBuilder stringBuilder = new StringBuilder();
        for (String food : mBeer.getFood_pairing()) {
            stringBuilder.append("- ");
            stringBuilder.append(food);
            stringBuilder.append("\n");
        }
        mFoodPairingTextView.setText(stringBuilder.toString());

        // Basics
        mVolumeValue.setText(String.valueOf(mBeer.getVolume().getValue()));
        mVolumeUnit.setText(mBeer.getVolume().getUnit());

        mBoilVolumeValue.setText(String.valueOf(mBeer.getBoil_volume().getValue()));
        mBoilVolumeUnit.setText(mBeer.getBoil_volume().getUnit());

        mABVValue.setText(String.format("%s%%", String.valueOf(mBeer.getAbv())));
        mTargetFGValue.setText(String.valueOf(mBeer.getTarget_fg()));
        mTargetOGValue.setText(String.valueOf(mBeer.getTarget_og()));

        mEBCValue.setText(String.valueOf(mBeer.getEbc()));
        mSRMValue.setText(String.valueOf(mBeer.getSrm()));
        mPHValue.setText(String.valueOf(mBeer.getPh()));
        mAttenuationLevelValue.setText(String.format("%s%%", String.valueOf(mBeer.getAttenuation_level())));





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
