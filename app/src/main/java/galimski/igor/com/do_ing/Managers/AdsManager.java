package galimski.igor.com.do_ing.Managers;

import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import galimski.igor.com.do_ing.View.Activites.MainActivity;

public class AdsManager
{
    private static InterstitialAd mInterstitialAd;

    private static final String APP_ID = "ca-app-pub-0850996013613932~5275247023";
    private static final String BANNER_ID = "ca-app-pub-0850996013613932/7595956902";

    public static void Init()
    {
        MobileAds.initialize(MainActivity.GetInstance(), APP_ID);

        mInterstitialAd = new InterstitialAd(MainActivity.GetInstance());
        mInterstitialAd.setAdUnitId(BANNER_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed()
            {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdFailedToLoad(int var1){
                Log.d("TAG", "Add failed to load");
                Crashlytics.log("Add failed to load");
            }
        });
    }

    public static void Show()
    {
        if (mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        }
        else
        {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            Crashlytics.log("The interstitial wasn't loaded yet.");
        }
    }
}
