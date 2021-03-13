package tads.eaj.ufrn.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao1Binding

class ActivityAcao1 : AppCompatActivity() {

    lateinit var binding:ActivityAcao1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)

        binding.buttonOk.setOnClickListener {
            var intent = Intent()
            intent.putExtra("texto", binding.editText1.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
        binding.buttonCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}