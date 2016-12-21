package uk.co.dazcorp.android.brewdroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

public class BeerListActivity extends BaseActivity {

    private BeerListFragment mBeerfragment;
    private Fragment mCurrentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);

        mBeerfragment = new BeerListFragment();
        final FragmentManager fm = getSupportFragmentManager();

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.all_beers:
                        if (mCurrentFragment == null || mCurrentFragment != mBeerfragment) {
                            mCurrentFragment = mBeerfragment;
                            fm.beginTransaction().add(R.id.fragment_container, mBeerfragment).commit();
                        }
                        return true;
                    case R.id.favourites:
                        break;
                }
                return false;
            }
        });

        findViewById(navigationView.getMenu().getItem(0).getItemId()).performClick();
    }
}