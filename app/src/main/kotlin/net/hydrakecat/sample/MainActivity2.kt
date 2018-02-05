package net.hydrakecat.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity2 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun onPurchaseClicked(view: View) {
    val itemId = 42
    if (isNotLoggedIn()) {
      startActivityForResult(Request.Login)
    } else {
      startActivityForResult(Request.Purchase(itemId))
    }
  }

  fun onLikeClicked(view: View) {
    val itemId = 42
    if (isNotLoggedIn()) {
      startActivityForResult(Request.Login)
      return
    }
  }

  private fun isNotLoggedIn(): Boolean = true

  private fun startActivityForResult(req: Request) = startActivityForResult(
      req.newIntent(this),
      req.code.ordinal)

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    RequestCode.values()
        .find { it.ordinal == requestCode }
        ?.handleResult(resultCode, data)
        ?: throw IllegalArgumentException("Invalid req code: $requestCode")
  }

  private fun handleLoginResult(resultCode: Int) {
  }

  private fun handlePurchaseResult(resultCode: Int, data: Intent?) {
  }

  private enum class RequestCode {
    LOGIN, PURCHASE
  }

  private fun RequestCode.handleResult(resultCode: Int, data: Intent?) {
    when (this) {
      RequestCode.LOGIN -> handleLoginResult(resultCode)
      RequestCode.PURCHASE -> handlePurchaseResult(resultCode, data)
    }
  }

  private sealed class Request(
      val code: RequestCode,
      val newIntent: (Context) -> Intent) {

    object Login : Request(RequestCode.LOGIN, LoginActivity.Companion::newIntent)
    class Purchase(id: Int) : Request(RequestCode.PURCHASE,
        { ConfirmationActivity.newIntent(it, id) })
  }
}