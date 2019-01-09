

package com.orpheusdroid.screenrecorder;

import android.app.Application;
import android.preference.PreferenceManager;
import android.util.Log;

import ly.count.android.sdk.Countly;
import ly.count.android.sdk.DeviceId;

import static com.orpheusdroid.screenrecorder.Const.ANALYTICS_API_KEY;
import static com.orpheusdroid.screenrecorder.Const.ANALYTICS_URL;


public class ScreenCamBaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setupAnalytics() {
        Countly.sharedInstance()
                .setRequiresConsent(true)
                .setLoggingEnabled(true)
                .setHttpPostForced(true)
                .enableParameterTamperingProtection(getPackageName())
                .setViewTracking(true)
                .setIfStarRatingShownAutomatically(true)
                .setAutomaticStarRatingSessionLimit(3)
                .setIfStarRatingDialogIsCancellable(true)
                .enableCrashReporting();

        String[] groupFeatures = new String[]{Countly.CountlyFeatureNames.sessions
                , Countly.CountlyFeatureNames.users, Countly.CountlyFeatureNames.events
                , Countly.CountlyFeatureNames.starRating};
        Countly.sharedInstance().createFeatureGroup(Const.COUNTLY_USAGE_STATS_GROUP_NAME, groupFeatures);

        boolean isUsageStatsEnabled = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getBoolean(getString(R.string.preference_anonymous_statistics_key), false);
        Countly.sharedInstance().setConsentFeatureGroup(Const.COUNTLY_USAGE_STATS_GROUP_NAME, isUsageStatsEnabled);

        boolean isCrashesEnabled = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getBoolean(getString(R.string.preference_crash_reporting_key), false);
        Countly.sharedInstance().setConsent(new String[]{Countly.CountlyFeatureNames.crashes}, isCrashesEnabled);

        Countly.sharedInstance().init(this, ANALYTICS_URL, ANALYTICS_API_KEY, null, DeviceId.Type.OPEN_UDID);
        Log.d(Const.TAG, "Countly setup");
    }
}
