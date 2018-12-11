package com.quanghoa.apps.bottomappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(bottom_app_bar)

        val addVisibilityChanged: FloatingActionButton.OnVisibilityChangedListener = object : FloatingActionButton.OnVisibilityChangedListener() {

            override fun onShown(fab: FloatingActionButton?) {
                super.onShown(fab)
            }

            override fun onHidden(fab: FloatingActionButton?) {
                super.onHidden(fab)
                bottom_app_bar.toggleFabAlignment()

                val menu = when (currentFabAlignmentMode) {
                    BottomAppBar.FAB_ALIGNMENT_MODE_CENTER -> R.menu.bottomappbar_menu_secondary
                    else -> R.menu.bottomappbar_menu_primary
                }
                bottom_app_bar.replaceMenu(menu)

                val drawableIcon = when (currentFabAlignmentMode) {
                    BottomAppBar.FAB_ALIGNMENT_MODE_CENTER -> getDrawable(R.drawable.baseline_reply_white_24)
                    else -> getDrawable(R.drawable.baseline_add_white_24)
                }
                fab?.setImageDrawable(drawableIcon)
                fab?.show()
            }
        }

        toggle_fab_alignment_button.setOnClickListener {
            fab.hide(addVisibilityChanged)
            invalidateOptionsMenu()

            bottom_app_bar.navigationIcon = if (bottom_app_bar.navigationIcon != null) null
            else getDrawable(R.drawable.baseline_menu_white_24)

            when (screen_label.text) {
                getString(R.string.primary_screen_text) -> screen_label.text = getString(R.string.secondary_screen_text)
                getString(R.string.secondary_screen_text) -> screen_label.text = getString(R.string.primary_screen_text)
            }
        }

        fab.setOnClickListener {
            displayMaterialSnackbar()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu_primary, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }

    private fun displayMaterialSnackbar() {
        val marginSide = 0
        val marginBottom = 550
        val snackbar = Snackbar.make(coordinatorLayoutChild, "FAB clicked", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {}

        //Changing message text color
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorSnackButton))

        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as CoordinatorLayout.LayoutParams

        params.setMargins(
                params.leftMargin + marginSide,
                params.topMargin,
                params.rightMargin + marginSide,
                params.bottomMargin + marginBottom
        )
        snackbarView.layoutParams = params
        snackbar.show()
    }

    private fun BottomAppBar.toggleFabAlignment() {
        currentFabAlignmentMode = fabAlignmentMode
        fabAlignmentMode = currentFabAlignmentMode.xor(1)
    }
}
