package com.tributedummy.metbb.dummy3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.Classes.Artist;
import com.tributedummy.metbb.dummy3.Classes.Concert;
import com.tributedummy.metbb.dummy3.Classes.Review;
import com.tributedummy.metbb.dummy3.Classes.User;
import com.tributedummy.metbb.dummy3.Classes.Venue;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DiscoverFragment discoverFragment;
    private ProfileFragment profileFragment;
    private ReviewFragment reviewFragment;
    private SignupFragment signupFragment;
    private LoginFragment loginFragment;
    private ConcertFragment concertFragment;
    private ArtistFragment artistFragment;

    private FrameLayout mainFrame;
    private BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";

    public static ArrayList<Concert> concerts = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    SwitchFragment(discoverFragment);
                    return true;
                case R.id.navigation_review:
                    SwitchFragment(reviewFragment);
                    return true;
                case R.id.navigation_profile:
                    SwitchFragment(profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        signupFragment = new SignupFragment();
        loginFragment = new LoginFragment();
        discoverFragment = new DiscoverFragment();
        profileFragment = new ProfileFragment();
        reviewFragment = new ReviewFragment();
        concertFragment = new ConcertFragment();
        artistFragment = new ArtistFragment();

        Log.d(TAG,"onCreate: started");

        generateData();

        SwitchFragment(signupFragment);
        toggleMenu(false);
    }


    public void SwitchFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
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

        for (int i = 0; i < 10; i++) {
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

    private void setupDiscoverFragment()
    {

    }

// Getters

    public Iterable<Concert> getConcerts() {
        return concerts;
    }

    public SignupFragment getSignupFragment() {
        return signupFragment;
    }

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public DiscoverFragment getDiscoverFragment() {
        return discoverFragment;
    }

    public ConcertFragment getConcertFragment(Concert concert) {
        concertFragment.setConcert(concert);
        return concertFragment;
    }

    public ArtistFragment getArtistFragment(Artist artist, Fragment previousFragment) {
        artistFragment.setArtist(artist);
        artistFragment.setPreviousFragment(previousFragment);
        return artistFragment;
    }
}
