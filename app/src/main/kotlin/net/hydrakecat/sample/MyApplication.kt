package net.hydrakecat.sample

import android.app.Application
import rx_activity_result2.RxActivityResult

class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    RxActivityResult.register(this);
  }
}