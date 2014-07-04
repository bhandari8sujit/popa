package com.indexer.hellotaxi.app.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.indexer.hellotaxi.app.R;
import com.indexer.hellotaxi.app.application.popa;
import com.indexer.hellotaxi.app.module.ActivityModule;
import dagger.ObjectGraph;
import java.util.Arrays;
import java.util.List;

public abstract class BasePopaActivity extends ActionBarActivity {
  private ObjectGraph activityGraph;
  private GoogleMap mMap;

  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(getLayoutId());
    setUpMapIfNeeded();
    popa application = (popa) getApplication();
    activityGraph = application.getObjectGraph().plus(getModules().toArray());
    activityGraph.inject(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    setUpMapIfNeeded();
  }

  @Override
  protected void onDestroy() {
    // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
    // soon as possible.
    activityGraph = null;
    super.onDestroy();
  }

  private void setUpMapIfNeeded() {
    if (mMap != null) {
      return;
    }
    mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    if (mMap != null) {
      start();
    }
  }

  /**
   * Run the demo-specific code.
   */
  protected abstract void start();

  protected GoogleMap getMap() {
    setUpMapIfNeeded();
    return mMap;
  }

  protected List<Object> getModules() {
    return Arrays.<Object>asList(new ActivityModule(this));
  }

  /**
   * Inject the supplied {@code object} using the activity-specific graph.
   */
  public void inject(Object object) {
    activityGraph.inject(object);
  }

  protected void enableBtn(EditText editText, final Button mBtn) {
    editText.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override public void afterTextChanged(Editable s) {
        if (s.toString().trim().length() == 0) {
          mBtn.setEnabled(false);
        } else {
          mBtn.setEnabled(true);
        }
      }
    });
  }
}