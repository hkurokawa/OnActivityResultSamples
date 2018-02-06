package net.hydrakecat.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import rx_activity_result2.RxActivityResult

private const val REQ_CODE_LOGIN = 0
private const val REQ_CODE_PURCHASE = 1

class MainActivity6 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun onPurchaseClicked(view: View) {
    val itemId = 42
    launch(UI) {
      if (isNotLoggedIn()) {
        val intent = LoginActivity.newIntent(this@MainActivity6)
        val (resultCode, _) = startActivitySuspend(intent)
        if (resultCode != Activity.RESULT_OK) {
          return@launch
        }
      }
      
      doPurchase(itemId)
    }
  }

  private data class Result(val resultCode: Int, val data: Intent?)

  private suspend fun startActivitySuspend(intent: Intent): Result {
    return Result(Activity.RESULT_OK, null)
  }

  fun onLikeClicked(view: View) {
    val itemId = 42
    if (isNotLoggedIn()) {
      startActivityForResult(LoginActivity.newIntent(this), REQ_CODE_LOGIN)
      return
    }
    doLike(itemId)
  }

  private fun doPurchase(itemId: Int) {
    Toast.makeText(this, "Purchasing $itemId", Toast.LENGTH_LONG).show()
    startActivityForResult(ConfirmationActivity.newIntent(this, itemId), REQ_CODE_PURCHASE)
  }

  private fun doLike(itemId: Int) {
    Toast.makeText(this, "Liking $itemId", Toast.LENGTH_LONG).show()
  }

  private fun isNotLoggedIn(): Boolean = true
}