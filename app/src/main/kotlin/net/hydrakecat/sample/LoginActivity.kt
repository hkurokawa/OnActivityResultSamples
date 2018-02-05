package net.hydrakecat.sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
  companion object {
    fun newIntent(ctx: Context) = Intent(ctx, LoginActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    setResult(Activity.RESULT_OK)
  }
}
