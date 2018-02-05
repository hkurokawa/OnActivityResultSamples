package net.hydrakecat.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

private const val REQ_CODE_LOGIN = 0
private const val REQ_CODE_PURCHASE = 1

class MainActivity : AppCompatActivity() {
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

    when(requestCode) {
      REQ_CODE_LOGIN -> handleLoginResult(resultCode)
      REQ_CODE_PURCHASE -> handlePurchaseResult(resultCode, data)
    }
  }

  private fun handleLoginResult(resultCode: Int) {}

  private fun handlePurchaseResult(resultCode: Int, data: Intent?) {}

  private fun isNotLoggedIn(): Boolean = true
}