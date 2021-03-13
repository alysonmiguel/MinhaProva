package tads.eaj.ufrn.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao2Binding
import tads.eaj.ufrn.minhaprova.sqllite.Livro
import tads.eaj.ufrn.minhaprova.sqllite.LivroContrato
import tads.eaj.ufrn.minhaprova.sqllite.LivroDBOpener

class ActivityAcao2 : AppCompatActivity() {
    lateinit var binding: ActivityAcao2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)

        binding.buttonOk2.setOnClickListener {

            var nome = binding.editTextTitulo.text
            var autor = binding.editTextAutor.text
            var ano = binding.editTextAno.text
            var nota = binding.ratingBar.rating

            if(!(nome.isEmpty() || autor.isEmpty() || ano.isEmpty())){
                var l = Livro(0, nome.toString(), autor.toString(), ano.toString().toInt(), nota)

                var db = LivroDBOpener(this)

                db.insert(l)

                var intent = Intent()
                intent.putExtra("texto", "cadastrado")
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this, "Preencha tudo", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonCancelar2.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }


    }
}