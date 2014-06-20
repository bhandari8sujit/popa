package com.indexer.hellotaxi.app.Module;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import com.indexer.hellotaxi.app.Application.popa;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by yemonkyaw on 5/10/14.
 */
@Module(library = true)
public class AndroidModule {
  private popa application;

  public AndroidModule(popa application) {
    this.application = application;
  }

  @Provides @Singleton Application provideApplicationContext() {
    return application;
  }

  @Provides @Singleton LocationManager provideLocationManager() {
    return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
  }

  @Provides @Singleton Criteria provoideCriteria() {
    Criteria criteria = new Criteria();
    //Use FINE or COARSE (or NO_REQUIREMENT) here
    criteria.setAccuracy(Criteria.ACCURACY_FINE);
    criteria.setPowerRequirement(Criteria.POWER_LOW);
    criteria.setAltitudeRequired(true);
    criteria.setSpeedRequired(true);
    criteria.setCostAllowed(true);
    criteria.setBearingRequired(true);
    return criteria;
  }
}
