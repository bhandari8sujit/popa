/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.indexer.hellotaxi.app.module;

import android.content.Context;
import com.indexer.hellotaxi.app.ForActivity;
import com.indexer.hellotaxi.app.base.BasePopaActivity;
import com.indexer.hellotaxi.app.controller.ActivityTitleController;
import com.indexer.hellotaxi.app.listener.MapMarkerListener;
import com.indexer.hellotaxi.app.ui.CallActivity_;
import com.indexer.hellotaxi.app.ui.LoginActivity_;
import com.indexer.hellotaxi.app.ui.MainActivity_;
import com.indexer.hellotaxi.app.ui.SettingActivity_;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
    injects = {
        MainActivity_.class, CallActivity_.class, LoginActivity_.class, SettingActivity_.class,
        MapMarkerListener.class
    },
    addsTo = AndroidModule.class,
    library = true)
public class ActivityModule {
  private final BasePopaActivity activity;

  public ActivityModule(BasePopaActivity activity) {
    this.activity = activity;
  }

  @Provides @Singleton @ForActivity Context provideActivityContext() {
    return activity;
  }

  @Provides @Singleton ActivityTitleController provideTitleController() {
    return new ActivityTitleController(activity);
  }
}
