package com.tributedummy.metbb.dummy3;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.tributedummy.metbb.dummy3.classes.Review;
import com.tributedummy.metbb.dummy3.classes.User;
import com.tributedummy.metbb.dummy3.classes.Venue;
import com.tributedummy.metbb.dummy3.databinding.ActivityMainBinding;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;

    private DiscoverFragment discoverFragment = new DiscoverFragment();
    private SeeAllFragment seeAllFragment = new SeeAllFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private ReviewFragment reviewFragment = new ReviewFragment();
    private SignupFragment signupFragment = new SignupFragment();
    private LoginFragment loginFragment = new LoginFragment();
    private ConcertFragment concertFragment = new ConcertFragment();
    private SolopageFragment solopageFragment = new SolopageFragment();
    private ReviewConcertFragment reviewConcertFragment = new ReviewConcertFragment();
    private UpcomingConcertFragment upcomingConcertFragment = new UpcomingConcertFragment();

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

        if(mainActivity == null)
            mainActivity = this;

        // init data binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Initialize the action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setTitle("Tribute");
        actionBar.hide();

        // init the fragment manager
        fragmentManager = getSupportFragmentManager();

        // init the bottom nav bar
        bottomNavigationView = mainBinding.navigation;
        mainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toggleMenu(false);

        Log.d(TAG,"onCreate: started");

        generateData();
        switchFragment(signupFragment,false);

    }
    // Used for switching fragments
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

        ArrayList<Artist> allArtists = new ArrayList<>();
        allArtists.add(artistWaronDrugs);
        allArtists.add(artistKanyeWest);
        allArtists.add(artistLonelyIsland);

        Venue venueParken = new Venue("Parken", R.mipmap.judith_opener);
        Venue venueRoyalArena = new Venue("Royal Arena", R.mipmap.judith_opener);
        Venue venueVega = new Venue("Vega", R.mipmap.judith_opener);

        ArrayList<Venue> allVenues = new ArrayList<>();
        allVenues.add(venueParken);
        allVenues.add(venueRoyalArena);
        allVenues.add(venueVega);
        User user = new User("Mikkel");
        for (int i = 0; i < 50; i++) {
            int rndArtist = new Random().nextInt(3);
            int rndVenue = new Random().nextInt(3);
            Concert concert = new Concert(allArtists.get(rndArtist), allVenues.get(rndVenue));
            Review review1 = new Review(user,concert,"ehhh",5,4);
            Review review2 = new Review(user,concert,"ehhh",1,1);
            Review review3 = new Review(user,concert,"ehhh\nehhhh",2,4);
            Review review4 = new Review(user,concert,"ehhh",5,3);

            concert.addReview(review1);
            concert.addReview(review2);
            concert.addReview(review3);
            concert.addReview(review4);

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

    public UpcomingConcertFragment getUpcomingConcertFragment(Concert concert) {
        upcomingConcertFragment.setConcert(concert);
        return upcomingConcertFragment;
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
