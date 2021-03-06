package uk.co.dazcorp.android.brewdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.dazcorp.android.brewdroid.data.Beer;
import uk.co.dazcorp.android.brewdroid.data.Hop;
import uk.co.dazcorp.android.brewdroid.data.Malt;

public class BeerDetailActivity extends BaseActivity {

    public static final String EXTRA_BEER_KEY = "beer_key";
    private static final String TAG = "BEER";
    // Choose an arbitrary request code value
    private static final int RC_SIGN_IN = 123;

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

    // Ingredients
    @BindView(R.id.hops_layout) LinearLayout mHopsLayout;
    @BindView(R.id.malt_container) LinearLayout mMaltLayout;
    @BindView(R.id.yeast_value) TextView mYeastValue;

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
                FirebaseAuth auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() != null) {
                    // already signed in
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);
                }
                Snackbar.make(view, "Add fav beer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mBeerKey = getIntent().getStringExtra(EXTRA_BEER_KEY);
        if (mBeerKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_BEER_KEY");
        }

        mBeerReference = FirebaseDatabase.getInstance().getReference().child("beers").child(mBeerKey);
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

        addHops(mBeer.getIngredients().getHops());
        addMalt(mBeer.getIngredients().getMalt());
        mYeastValue.setText(mBeer.getIngredients().getYeast());
    }

    private void addHops(List<Hop> ingredients) {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Hop hop : ingredients) {
            View hopLayout = inflater.inflate(R.layout.item_hops, mHopsLayout, false);
            ((TextView)ButterKnife.findById(hopLayout, R.id.hop_name)).setText(hop.getName());
            ((TextView)ButterKnife.findById(hopLayout, R.id.hop_weight)).setText(String.valueOf(hop.getAmount().getValue()));
            ((TextView)ButterKnife.findById(hopLayout, R.id.hop_add_time)).setText(hop.getAdd());
            ((TextView)ButterKnife.findById(hopLayout, R.id.hop_attribute)).setText(hop.getAttribute());
            mHopsLayout.addView(hopLayout);
        }
    }

    private void addMalt(List<Malt> ingredients) {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Malt malt : ingredients) {
            View hopLayout = inflater.inflate(R.layout.item_malt, mMaltLayout, false);
            ((TextView)ButterKnife.findById(hopLayout, R.id.malt_name)).setText(malt.getName());
            ((TextView)ButterKnife.findById(hopLayout, R.id.malt_weight)).setText(String.format("%s %s", String.valueOf(malt.getAmount().getValue()), malt.getAmount().getUnit()));
            mMaltLayout.addView(hopLayout);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mBeerReference != null) {
            mBeerReference.removeEventListener(mBeerListener);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                startActivity(new Intent(this, BeerListActivity.class));
                finish();
                return;
            }

            // Sign in canceled
            if (resultCode == RESULT_CANCELED) {
                showSnackbar(R.string.sign_in_cancelled);
                return;
            }

            // No network
            if (resultCode == ResultCodes.RESULT_NO_NETWORK) {
                showSnackbar(R.string.no_internet_connection);
                return;
            }

            // User is not signed in. Maybe just wait for the user to press
            // "sign in" again, or show a message.
        }
    }

    private void showSnackbar(int resource) {
        Snackbar.make(mCollapsingToolbarLayout, resource, Snackbar.LENGTH_LONG).show();
    }
}
