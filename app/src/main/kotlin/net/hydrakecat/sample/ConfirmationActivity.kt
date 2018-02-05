package net.hydrakecat.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

const val ARG_ITEM_ID = "item_id"

class ConfirmationActivity : AppCompatActivity() {
  companion object {
    fun newIntent(ctx: Context, id: Int) = Intent(ctx, ConfirmationActivity::class.java).apply {
      putExtra(ARG_ITEM_ID, id)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}