

package com.orpheusdroid.screenrecorder;

import org.solovyev.android.checkout.Billing;


public class ScreenCamApp extends ScreenCamBaseApp {
    private static ScreenCamApp sInstance;

    private final Billing mBilling = new Billing(this, new Billing.DefaultConfiguration() {
        @Override
        public String getPublicKey() {
            return BuildConfig.APP_PUB_KEY;
        }
    });

    public ScreenCamApp() {
        sInstance = this;
    }

    public static ScreenCamApp get() {
        return sInstance;
    }

    public Billing getBilling() {
        return mBilling;
    }

    @Override
    public void setupAnalytics() {
        super.setupAnalytics();
    }
}
