package com.tributedummy.metbb.dummy3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tributedummy.metbb.dummy3.classes.Artist;
import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.ConcertElement;
import com.tributedummy.metbb.dummy3.classes.ConcertStatus;
import com.tributedummy.metbb.dummy3.classes.Venue;
import com.tributedummy.metbb.dummy3.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DiscoverFragment discoverFragment = new DiscoverFragment();
    private SeeAllFragment seeAllFragment = new SeeAllFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private ReviewFragment reviewFragment = new ReviewFragment();
    private SignupFragment signupFragment = new SignupFragment();
    private LoginFragment loginFragment = new LoginFragment();
    private ConcertFragment concertFragment = new ConcertFragment();
    private SolopageFragment solopageFragment = new SolopageFragment();
    private ReviewConcertFragment reviewConcertFragment = new ReviewConcertFragment();

    ActivityMainBinding mainBinding;

    private BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";
    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    public static ArrayList<Concert> concerts = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    switchFragment(discoverFragment,false);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    return true;
                case R.id.navigation_review:
                    switchFragment(reviewFragment,false);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    return true;
                case R.id.navigation_profile:
                    switchFragment(profileFragment,false);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        actionBar = getSupportActionBar();
        fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Log.d(TAG,"onCreate: started");
        generateData();

        switchFragment(signupFragment,false);
        toggleMenu(false);
    }
    // Used for swithing fragments
    public void switchFragment(Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.main_frame, fragment);
        if(addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }
    public void toggleMenu(boolean toggle) {
        if (toggle) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            bottomNavigationView.setVisibility(View.INVISIBLE);
        }
    }

    private void generateData() {

        Artist artistWaronDrugs = new Artist("The War On Drugs", R.mipmap.warondrugs);
        Artist artistKanyeWest = new Artist("Kanye West", R.mipmap.warondrugs);
        Artist artistLonelyIsland = new Artist("Lonely Island", R.mipmap.warondrugs);

        artistWaronDrugs.addReview("pretty good", (double)5);
        artistKanyeWest.addReview("could be betta",(double)2);
        artistLonelyIsland.addReview("cool I guess",(double)3);

        ArrayList<Artist> allArtists = new ArrayList<>();
        allArtists.add(artistWaronDrugs);
        allArtists.add(artistKanyeWest);
        allArtists.add(artistLonelyIsland);

        Venue venueParken = new Venue("Parken", R.mipmap.judith_opener);
        Venue venueRoyalArena = new Venue("Royal Arena", R.mipmap.judith_opener);
        Venue venueVega = new Venue("Vega", R.mipmap.judith_opener);

        venueParken.addReview("bleh",(double)1);
        venueVega.addReview("NICE",(double)5);
        venueRoyalArena.addReview("bad internet",(double)3);

        ArrayList<Venue> allVenues = new ArrayList<>();
        allVenues.add(venueParken);
        allVenues.add(venueRoyalArena);
        allVenues.add(venueVega);

        for (int i = 0; i < 50; i++) {
            int rndArtist = new Random().nextInt(3);
            int rndVenue = new Random().nextInt(3);
            Concert concert = new Concert(allArtists.get(rndArtist), allVenues.get(rndVenue));
            concert.addReview("<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3\n<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3",(double)2);
            concert.addReview("<3",(double)2);
            concert.addReview("<3",(double)2);
            concert.addReview("<3",(double)2);
            concert.addReview("<3",(double)2);

            concerts.add(concert);
        }
    }
    private void setupDiscoverFragment() {

    }

    public static ArrayList<Concert> getIndexedConcerts(ConcertStatus concertStatus) {
        ArrayList<Concert> indexed = new ArrayList<>();

        for (Concert c: concerts) {
            if(c.getStatus() == concertStatus)
            {
                indexed.add(c);
            }
        }
        return indexed;
    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }else if(!bottomNavigationView.getMenu().getItem(0).isChecked()){
                switchFragment(getDiscoverFragment(), false);
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }else
            super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        fragmentManager.popBackStack();
        return super.onSupportNavigateUp();
    }

    // Fragments Getters and settersF
    public Iterable<Concert> getConcerts() {
        return concerts;
    }
    public SignupFragment getSignupFragment() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        return signupFragment;
    }
    public LoginFragment getLoginFragment() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        return loginFragment;
    }
    public DiscoverFragment getDiscoverFragment() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        return discoverFragment;
    }
    public ConcertFragment getConcertFragment(Concert concert) {
        actionBar.setDisplayHomeAsUpEnabled(false);
        concertFragment.setConcert(concert);
        return concertFragment;
    }
    public ReviewConcertFragment getReviewConcertFragment(Concert concert) {
        actionBar.setDisplayHomeAsUpEnabled(false);
        reviewConcertFragment.setConcert(concert);
        return reviewConcertFragment;
    }
    public SolopageFragment getSolopageFragment(ConcertElement concertElement) {
        setActionBarVisibility(false);
        solopageFragment.setConcertElement(concertElement);
        return solopageFragment;
    }
    public SeeAllFragment getSeeAllFragment(String title,ArrayList<Concert> concerts) {
        setActionBarVisibility(true);
        seeAllFragment.setFilterTitle(title);
        seeAllFragment.setConcerts(concerts);
        return seeAllFragment;
    }
    //Helpers

    //gettes and setters other
    public void setActionBarVisibility(boolean visibility) {

        if (visibility) {
            actionBar.show();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        else {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.hide();
        }
    }
}
