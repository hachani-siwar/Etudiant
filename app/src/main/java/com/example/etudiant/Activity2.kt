package com.example.etudiant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ShareCompat

class Activity2 : AppCompatActivity() {
    lateinit var receiver:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        receiver = findViewById(R.id.txtReciever)
        val valeur = intent.getStringExtra("cle")
        receiver.text = valeur.toString()

        var btn:ImageButton=findViewById(R.id.btn2)
        btn.setOnClickListener(){
            val txt:String = receiver.text.toString()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, txt)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }


    }




   // fun shareText(view: View){
      //  val txt:String = receiver.getText().toString()
        //val mimeType = "text/plain"
        //val shareIntent = ShareCompat.IntentBuilder(this)
        //shareIntent.setType(mimeType)
          //  .setChooserTitle(R.string.share_text_with)
            //.setText(txt)
            //.startChooser()
    //}
}