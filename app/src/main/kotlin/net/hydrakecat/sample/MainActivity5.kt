package net.hydrakecat.sample

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import rx_activity_result2.RxActivityResult

private const val REQ_CODE_LOGIN = 0
private const val REQ_CODE_PURCHASE = 1

class MainActivity5 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun onPurchaseClicked(view: View) {
    val itemId = 42
    Observable.just(isNotLoggedIn())
        .flatMap {
          if (it) {
            RxActivityResult.on(this)
                .startIntent(LoginActivity.newIntent(this))
                .map { it.resultCode() == Activity.RESULT_OK }
          } else {
            Observable.just(true)
          }
        }
        .filter { it }
        .subscribe { doPurchase(itemId) }
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