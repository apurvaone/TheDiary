package com.example.thediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewPageActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_page)

        editTitle= findViewById(R.id.edit_title)
        editContent= findViewById(R.id.edit_content)

        val button= findViewById<Button>(R.id.button_save)
        button.setOnClickListener {

            val replyIntent= Intent()
            val extras: Bundle= Bundle()
            extras.putString("title",editTitle.toString())
            extras.putString("content",editContent.toString())

            if (TextUtils.isEmpty(editTitle.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
        }else {

                replyIntent.putExtra(EXTRA_REPLY, extras)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()   }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.pagelistsql.REPLY"
    }

}