package com.squidshoe.mfl.core;

import com.squidshoe.mfl.core.model.Franchise;
import com.squidshoe.mfl.core.model.League;
import com.squidshoe.mfl.core.model.LeagueStandings;
import com.squidshoe.mfl.core.model.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import rx.Observer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by kkovach on 8/24/15.
 */
public class LeagueManagerTest {

    private final static Logger LOGGER = Logger.getLogger(LeagueManagerTest.class.getName());

    LeagueManager mLeagueManager;
    private List<League> mLeagueSearchResults;
    private League mLeague;
    private LeagueStandings mLeagueStandings;

    private Integer mDefaultYear;
    private String mDefaultLeagueId;
    private List<Player> mPlayerList;
    private List<Player> mRoster;
    private Franchise mFranchise;

    @Before
    public void setUp() throws Exception {

        mDefaultYear = 2012;
        mDefaultLeagueId = "35165";

//        mDefaultYear = 2015;
//        mDefaultLeagueId = "63554";

        mLeagueManager = new LeagueManager(mDefaultYear, mDefaultLeagueId, RestAdapter.LogLevel.FULL);
    }

    @Test
    public void noLogging() {

        mLeagueManager = new LeagueManager(mDefaultYear, mDefaultLeagueId, null);

        assertNotNull(mLeagueManager);
    }

    @Test
    public void search() {

        final CountDownLatch searchLatch = new CountDownLatch(1);

        mLeagueManager.search("Ultimate").subscribe(new Observer<List<League>>() {

            @Override
            public void onCompleted() {

                searchLatch.countDown();
            }

            @Override
            public void onError(Throwable e) {

                System.out.println("Error: " + e.getMessage());

                fail(e.getMessage());
                searchLatch.countDown();
            }

            @Override
            public void onNext(List<League> leagues) {

                mLeagueSearchResults = leagues;
                searchLatch.countDown();
            }
        });

        try {

            searchLatch.await(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            e.printStackTrace();
            fail("Search timed out!");
        }

        assertNotNull(mLeagueSearchResults);
        assertThat(mLeagueSearchResults.size(), is(226));
    }

    @Test
    public void get() {

        final CountDownLatch leagueLatch = new CountDownLatch(1);

        mLeagueManager.get().subscribe(new Observer<League>() {
            @Override
            public void onCompleted() {

                leagueLatch.countDown();
            }

            @Override
            public void onError(Throwable e) {

                if (e instanceof RetrofitError) {
                    if (((RetrofitError) e).getResponse() != null) {
                        System.out.println("Error: " + ((RetrofitError) e).getResponse().getReason());
                    } else {
                        System.out.println("Error: " + ((RetrofitError) e).getKind());
                        System.out.println("Error: " + ((RetrofitError) e).getCause().getMessage());
                    }
                } else {
                    System.out.println("Error: " + e.getMessage());
                }
                fail(e.getMessage());
                leagueLatch.countDown();
            }

            @Override
            public void onNext(League league) {

                System.out.println("Got next!");
                mLeague = league;
                leagueLatch.countDown();
            }
        });

        try {

            leagueLatch.await(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            e.printStackTrace();
            fail("Search timed out!");
        }

        assertNotNull(mLeague);
        assertThat(mLeague.name, is("Absolute Gridiron League"));
        assertThat(mLeague.baseURL, is("http://football16.myfantasyleague.com"));
    }

    @Test
    public void standings() {

        final CountDownLatch standingsLatch = new CountDownLatch(1);

        mLeagueManager.standings().subscribe(new Observer<LeagueStandings>() {
            @Override
            public void onCompleted() {

                standingsLatch.countDown();
            }

            @Override
            public void onError(Throwable e) {

                standingsLatch.countDown();
            }

            @Override
            public void onNext(LeagueStandings leagueStandings) {

                mLeagueStandings = leagueStandings;
                standingsLatch.countDown();
            }
        });

        try {

            standingsLatch.await(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            e.printStackTrace();
            fail("Search timed out!");
        }

        assertNotNull(mLeagueStandings);
        assertThat(mLeagueStandings.franchises.get(0).h2hw, is(22));
        assertThat(mLeagueStandings.franchises.get(0).pf, is(5038.55));
        assertThat(mLeagueStandings.franchises.get(0).pwr, is(58.36));
        assertThat(mLeagueStandings.franchises.get(0).minpa, is(187.25));
        assertThat(mLeagueStandings.franchises.get(0).pa, is(7478.95));
        assertThat(mLeagueStandings.franchises.get(0).dp, is(2490.10));
        assertThat(mLeagueStandings.franchises.get(0).maxpa, is(358.65));
        assertThat(mLeagueStandings.franchises.get(0).pp, is(5959.50));

    }

    @Test
    public void players() {

        final CountDownLatch playersLatch = new CountDownLatch(1);

        LOGGER.log(Level.INFO, "Getting players!!");

        mLeagueManager.players().subscribe(new Observer<List<Player>>() {

            @Override
            public void onCompleted() {

                LOGGER.log(Level.INFO, "Complete!");
                playersLatch.countDown();
            }

            @Override
            public void onError(Throwable e) {

                LOGGER.log(Level.INFO, "Error: " + e.getMessage());
                playersLatch.countDown();
            }

            @Override
            public void onNext(List<Player> players) {

                LOGGER.log(Level.INFO, "Got next player list.");
                mPlayerList = players;
                playersLatch.countDown();
            }
        });

        try {

            playersLatch.await(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            e.printStackTrace();
            fail("Search timed out!");
        }

        assertNotNull(mPlayerList);
    }

    @Test
    public void roster() {

        final CountDownLatch rosterLatch = new CountDownLatch(1);

        LOGGER.log(Level.INFO, "Getting roster!!");

        mLeagueManager.roster("0001").subscribe(new Observer<List<Player>>() {

            @Override
            public void onCompleted() {

                LOGGER.log(Level.INFO, "Complete!");
                rosterLatch.countDown();
            }

            @Override
            public void onError(Throwable e) {

                LOGGER.log(Level.INFO, "Error: " + e.getMessage());
                rosterLatch.countDown();
            }

            @Override
            public void onNext(List<Player> roster) {

                LOGGER.log(Level.INFO, "Got next player list.");
                mRoster = roster;
                rosterLatch.countDown();
            }
        });

        try {

            rosterLatch.await(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            e.printStackTrace();
            fail("Search timed out!");
        }

        assertNotNull(mRoster);
    }
}
