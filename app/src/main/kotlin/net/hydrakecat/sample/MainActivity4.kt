package net.hydrakecat.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import onactivityresult.ActivityResult
import onactivityresult.OnActivityResult
import java.io.Serializable

private const val REQ_CODE_LOGIN = 0
private const val REQ_CODE_PURCHASE = 1
private const val STATE_LAST_ACTION = "last_action"

class MainActivity4 : AppCompatActivity() {
  private var lastAction: Action? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    savedInstanceState?.let {
      lastAction = it.getSerializable(STATE_LAST_ACTION) as Action?
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    ActivityResult.onResult(requestCode, resultCode, data).into(this)
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)

    outState?.putSerializable(STATE_LAST_ACTION, lastAction)
  }

  fun onPurchaseClicked(view: View) {
    val itemId = 42
    if (isNotLoggedIn()) {
      lastAction = Action.Purchase(itemId)
      startActivityForResult(LoginActivity.newIntent(this), REQ_CODE_LOGIN)
      return
    }
    doPurchase(itemId)
  }

  fun onLikeClicked(view: View) {
    val itemId = 42
    if (isNotLoggedIn()) {
      lastAction = Action.Like(itemId)
      startActivityForResult(LoginActivity.newIntent(this), REQ_CODE_LOGIN)
      return
    }
    doLike(itemId)
  }

  private val purchaseItemId: Int = 0

  @OnActivityResult(requestCode = REQ_CODE_LOGIN)
  fun handleLoginResult(resultCode: Int) {
    if (resultCode == Activity.RESULT_OK) {
      val la = lastAction
      when (la) {
        is Action.Purchase -> doPurchase(la.itemId)
        is Action.Like -> doLike(la.itemId)
      }
    }
  }

  private fun doPurchase(itemId: Int) {
    Toast.makeText(this, "Purchasing $itemId", Toast.LENGTH_LONG).show()
    startActivityForResult(ConfirmationActivity.newIntent(this, itemId), REQ_CODE_PURCHASE)
  }

  private fun doLike(itemId: Int) {
    Toast.makeText(this, "Liking $itemId", Toast.LENGTH_LONG).show()
  }

  @OnActivityResult(requestCode = REQ_CODE_PURCHASE)
  fun handlePurchaseResult(resultCode: Int, data: Intent?) {
  }

  private fun isNotLoggedIn(): Boolean = true

  private sealed class Action : Serializable {
    class Purchase(val itemId: Int) : Action()
    class Like(val itemId: Int) : Action()
  }
}