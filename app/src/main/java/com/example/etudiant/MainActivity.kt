package com.example.etudiant

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // access the items of the list
        val languages = resources.getStringArray(R.array.classe)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.classe)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        val date: TextView  = findViewById(R.id.date)
        date.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            date.text = sdf.format(cal.time)

        }

        date.setOnClickListener {
            DatePickerDialog(this@MainActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
    fun valider(v: View?) {
        var nom : EditText =findViewById(R.id.nom)
        var date : EditText = findViewById(R.id.date)
        var email : EditText =findViewById(R.id.email)
        var classe : Spinner =findViewById(R.id.classe)
        var btn : ImageButton=findViewById(R.id.imageButton)

        if (nom.text.length === 0){
                Toast.makeText(applicationContext,"le champs nom et prenom est vide", Toast.LENGTH_SHORT).show()
            val Sb = Snackbar.make(nom,"le champs nom et prenom est vide", Snackbar.LENGTH_LONG)
            Sb.show()
            }
        if(  date.text.length === 0) {
                Toast.makeText(applicationContext,"le champs date de naissance est vide", Toast.LENGTH_SHORT).show()
            val Sb = Snackbar.make(nom,"le champs date de naissance est vide", Snackbar.LENGTH_LONG)
            Sb.show()
            }
        if(email.text.length === 0){
                Toast.makeText(applicationContext,"le champs email est vide", Toast.LENGTH_SHORT).show()
            val Sb = Snackbar.make(nom,"le champs email est vide", Snackbar.LENGTH_LONG)
            Sb.show()
            }




        if (nom.text.length !== 0 || date.text.length !== 0 || email.text.length !== 0
        ) {
            val ad1 = AlertDialog.Builder(this)
            ad1.setMessage("confirmer les donnees")
            ad1.setTitle("Alert")
            ad1.setIcon(android.R.drawable.btn_dialog)
            ad1.setPositiveButton("yes",
                DialogInterface.OnClickListener { dialogInterface, i -> btn.setOnClickListener{
                    var s1 = nom.text.toString()
                    var s2 = date.text.toString()
                    var s3 = email.text.toString()
                    var s4 = classe.selectedItem.toString()
                    var s = "nom et prenom :" +s1+ " date de naissance : "+s2+ " email : "+s3 + " classe : "+s4
                    var i = Intent(this,Activity2::class.java)
                    i.putExtra("cle",s)
                    startActivity(i)
                }  })
            val a: AlertDialog = ad1.create()
            a.show()

        }
    }
}