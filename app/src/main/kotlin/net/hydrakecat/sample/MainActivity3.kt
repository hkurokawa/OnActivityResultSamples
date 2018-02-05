package net.hydrakecat.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import onactivityresult.ActivityResult
import onactivityresult.OnActivityResult

private const val REQ_CODE_LOGIN = 0
private const val REQ_CODE_PURCHASE = 1

class MainActivity3 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun onPurchaseClicked(view: View) {
    val itemId = 42
    if (isNotLoggedIn()) {
      startActivityForResult(LoginActivity.newIntent(this), REQ_CODE_LOGIN)
    } else {
      startActivityForResult(ConfirmationActivity.newIntent(this, itemId), REQ_CODE_PURCHASE)
    }
  }

  fun onLikeClicked(view: View) {
    val itemId = 42
    if (isNotLoggedIn()) {
      startActivityForResult(LoginActivity.newIntent(this), REQ_CODE_LOGIN)
      return
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    ActivityResult.onResult(requestCode, resultCode, data).into(this)
  }

  @OnActivityResult(requestCode = REQ_CODE_LOGIN)
  fun handleLoginResult(resultCode: Int) {
    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show()
  }

  @OnActivityResult(requestCode = REQ_CODE_PURCHASE)
  fun handlePurchaseResult(resultCode: Int, data: Intent?) {}

  private fun isNotLoggedIn(): Boolean = true
}