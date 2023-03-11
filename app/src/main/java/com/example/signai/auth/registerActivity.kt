package com.example.signai.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.signai.R
import com.example.signai.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class registerActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var auth : FirebaseAuth
    lateinit var store: FirebaseFirestore
    lateinit var dref: DocumentReference
    private lateinit var binding: ActivityRegisterBinding
    private var userID: String = ""


    private lateinit var username : EditText
    private lateinit var email : EditText
    private lateinit var alamat : EditText
    private lateinit var nohp : EditText
    private lateinit var password : EditText
    private lateinit var createButton : Button
    private lateinit var masukButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        store = FirebaseFirestore.getInstance()

        //view bindings
        username = findViewById(R.id.usernamePlain)
        email = findViewById(R.id.emailPlain)
        alamat = findViewById(R.id.alamatPlain)
        nohp = findViewById(R.id.nohpPlain)
        password = findViewById(R.id.passwordPlain)

        binding.createButton.setOnClickListener(this)
        binding.masukButton.setOnClickListener(this)


        usernameLengthChecker()
        emailLengthChecker()
        passwordLengthChecker()
        alamatLengthChecker()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.createButton -> {
                registerUser()
            }
            R.id.masukButton -> {
                startActivity(Intent(this,loginActivity::class.java))
            }
        }
    }

    private fun registerUser() {
        val usernameIn = binding.usernamePlain.text.toString()
        val emailIn = binding.emailPlain.text.toString()
        val alamatIn = binding.alamatPlain.text.toString()
        val nohpIn = binding.nohpPlain.text.toString()
        val passwordIn = binding.passwordPlain.text.toString()

        auth.createUserWithEmailAndPassword(emailIn, passwordIn).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //TEST
                Toast.makeText(this, "error!", Toast.LENGTH_LONG).show()

                dref = store.collection("users").document(userID)
                userID = auth.currentUser!!.uid
                val userHashMap = HashMap<String, Any>()
                userHashMap.put("email", alamatIn)
                userHashMap.put("username", usernameIn)
                userHashMap.put("alamat", alamatIn)
                userHashMap.put("nohp", nohpIn)

                dref.set(userHashMap).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //TEST
                        Toast.makeText(this, "Berhasil Regis", Toast.LENGTH_LONG).show()
                        Log.d("RegisterFragment", "SignUp:success")
                        val intent = Intent(this, loginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.w("RegisterFragment", "SignUp:failure", task.exception)
                        Toast.makeText(applicationContext, "SignUp failed.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }



    //FUNGSI CHECK PANJANG USERNAME
    private fun usernameLengthChecker(){
        username.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()){
                username.background = resources.getDrawable(R.drawable.custom_edittext_notnull)
            }
            else if(text.isEmpty()) {
                username.background = resources.getDrawable(R.drawable.custom_edittext)
            }
        }
    }

    //FUNGSI CHECK EMAIL
    private fun emailLengthChecker(){
        email.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                email.setError("valid")
            }
            else if (text.isEmpty()){
                email.setError("please fill email")
            }
        }
    }

    private fun alamatLengthChecker(){
        val uppercase: Pattern = Pattern.compile("[A-Z]")
        val lowercase: Pattern = Pattern.compile("[a-z]")
        val digit: Pattern = Pattern.compile("[0-9]")

        password.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 8 && !lowercase.matcher(text).find() && !uppercase.matcher(text).find()
                && !digit.matcher(text).find()){
                password.setError("Sip dah bener")
            }
    }
    }
//    private fun nohpLengthChecker(){
//        email.doOnTextChanged { text, start, before, count ->
//            if (text!!.isNotEmpty()){
//                if (text!!.length > 16) {
//                    username.setError("Make it short!")
//                }
//            }
//            else if(text.isEmpty()) {
//                username.setError("Please Enter your name!")
//            }
//        }
//    }

    private fun passwordLengthChecker(){
        val uppercase: Pattern = Pattern.compile("[A-Z]")
        val lowercase: Pattern = Pattern.compile("[a-z]")
        val digit: Pattern = Pattern.compile("[0-9]")

        password.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 8 && !lowercase.matcher(text).find() && !uppercase.matcher(text).find()
                && !digit.matcher(text).find()){
                password.setError("Sip dah bener")
            }
            else{

            }



        }
    }


}