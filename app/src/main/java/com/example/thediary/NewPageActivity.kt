package com.example.thediary

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class NewPageActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText
    private lateinit var dateText:TextView
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_page)

        editTitle= findViewById(R.id.edit_title)
        editContent= findViewById(R.id.edit_content)
        dateText= findViewById(R.id.dateText)

        dateText.text= SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            dateText.text = sdf.format(cal.time)

        }

        dateText.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitle.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editTitle.text.toString()
//                replyIntent.putExtra(EXTRA_REPLY, word)
                replyIntent.apply {     putExtra(EXTRA_REPLY,editTitle.text.toString())
                                        putExtra(EXTRA_REPLY2,editContent.text.toString())
                                   }
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }  }


    companion object {
        const val EXTRA_REPLY = "com.example.android.pagelistsql.REPLY"
        const val EXTRA_REPLY2 = "com.example.android.pagelistsql.REPLY2"
    }

}