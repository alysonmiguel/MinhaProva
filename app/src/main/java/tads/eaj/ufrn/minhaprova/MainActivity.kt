package tads.eaj.ufrn.minhaprova

import android.app.Activity
import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import tads.eaj.ufrn.minhaprova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    val CODE_1 = 1
    val CODE_3 = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var settings = getSharedPreferences("prefs", MODE_PRIVATE)
        var flag = settings.getBoolean("flag", true)

        if(flag){
            Toast.makeText(this, getString(R.string.bemVindo), Toast.LENGTH_SHORT).show()
            var editor = settings.edit()
            editor.putBoolean("flag", false)
            editor.apply()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.text1.text = viewModel.text1
        binding.text2.text = viewModel.text2

        binding.button1.setOnClickListener {
            var intent = Intent(this, ActivityAcao1::class.java)
            startActivityForResult(intent, CODE_1)
        }

        binding.button2.setOnClickListener {
            var dialog = CafeDialog()
            dialog.show(supportFragmentManager, "Café")
        }

        binding.button3.setOnClickListener {
            var intent = Intent(this, ActivityAcao2::class.java)
            startActivityForResult(intent, CODE_3)
        }

        binding.button4.setOnClickListener {
            var intent = Intent(this, ActivityAcao3::class.java)
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODE_1->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        val param = data?.extras
                        val texto = param?.getString("texto")
                        viewModel.text1 = texto.toString()
                        binding.text1.text = texto
                    }
                    Activity.RESULT_CANCELED->{
                        Snackbar.make(binding.mainlayout, "\t\t\t\t Cancelado \t\t\t\t  ¯\\_( ͡❛ ͜ʖ ͡❛)_/¯ ", Snackbar.LENGTH_LONG).show()
                    }

                }
            }
            CODE_3->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        val param = data?.extras
                        val texto = param?.getString("texto")
                        viewModel.text2 = texto.toString()
                        binding.text2.text = texto
                    }
                }
            }
        }
    }
}