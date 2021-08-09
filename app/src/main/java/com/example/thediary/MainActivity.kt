package com.example.thediary

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val newPageActivityRequestCode = 1
    private val pageViewModel: PageViewModel by viewModels {
        PageViewModelFactory((application as PagesApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PageListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        pageViewModel.allPages.observe(this, Observer { pages ->
            // Update the cached copy of the words in the adapter.
            pages?.let { adapter.submitList(it) }
        })


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPageActivity::class.java)
            startActivityForResult(intent, newPageActivityRequestCode)
        }


        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        //make fully Android Transparent Status bar
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }





    }


    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win: Window = activity.window
        val winParams: WindowManager.LayoutParams = win.getAttributes()
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.setAttributes(winParams)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        var titlestring:String=""
        var contentString:String=""
        var dateString:String=""
        if (requestCode == newPageActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewPageActivity.EXTRA_REPLY)?.let { reply ->
//                val word = Page(reply,"Test")
//                pageViewModel.insert(word)
                  titlestring= reply
            }
            intentData?.getStringExtra(NewPageActivity.EXTRA_REPLY2)?.let { reply ->
//                val word = Page(reply,"Test")
//                pageViewModel.insert(word)
                 contentString= reply
            }
            intentData?.getStringExtra(NewPageActivity.EXTRA_REPLY3)?.let { reply ->
//                val word = Page(reply,"Test")
//                pageViewModel.insert(word)
                dateString= reply
            }

            val page = Page(titlestring, contentString,dateString)
            pageViewModel.insert(page)

        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }





}



//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == newPageActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            val extras: Bundle? = data?.extras
//            val title_string= extras?.getString("ExtraTitle")
//            val content_string= extras?.getString("ExtraContent")
//
////            val page= title_string?.let {
////                if (content_string != null) {
////                    Page(it,content_string)
////                }
////            }
//
//           Toast.makeText(applicationContext,title_string+ " "+ content_string,Toast.LENGTH_LONG).show()
//
//
//            Toast.makeText(applicationContext,"Saved",Toast.LENGTH_LONG).show()
//            }
//        else {
//            Toast.makeText(
//                applicationContext,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG).show()
//        }
//        }
//    }
