package com.example.thediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
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



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        var titlestring:String=""
        var contentString:String=""
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

            val page = Page(titlestring,contentString)
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
