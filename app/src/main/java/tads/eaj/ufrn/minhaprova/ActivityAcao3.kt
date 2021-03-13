package tads.eaj.ufrn.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao2Binding
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao3Binding
import tads.eaj.ufrn.minhaprova.sqllite.LivroDBOpener

class ActivityAcao3 : AppCompatActivity() {

    lateinit var binding: ActivityAcao3Binding
    var i = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3)

        var db = LivroDBOpener(this)

        var livro = db.findById(i)
        var livros = db.findAll()

        binding.textViewTitulo.text  = "Titulo: ${livro.nome}"
        binding.textViewAutor.text  = "Autor: ${livro.autor}"
        binding.textViewAno.text  =   "Ano: ${livro.ano}"
        binding.textViewNota.text  =  "Nota: ${livro.nota}"

        binding.buttonAnterior.setOnClickListener {

            if(i > 1){ binding.buttonProximo.isEnabled = true  }

            var livro = db.findById(--i)
            binding.textViewTitulo.text  = "Titulo: ${livro.nome}"
            binding.textViewAutor.text  = "Autor: ${livro.autor}"
            binding.textViewAno.text  =   "Ano: ${livro.ano}"
            binding.textViewNota.text  =  "Nota: ${livro.nota}"

            if (i == 1){binding.buttonAnterior.isEnabled = false }
        }
        binding.buttonProximo.setOnClickListener {

            var livro = db.findById(++i)
            binding.textViewTitulo.text = "Titulo: ${livro.nome}"
            binding.textViewAutor.text = "Autor: ${livro.autor}"
            binding.textViewAno.text = "Ano: ${livro.ano}"
            binding.textViewNota.text = "Nota: ${livro.nota}"

            if (i == livros.size){ binding.buttonProximo.isEnabled = false }

            if (i > 1){ binding.buttonAnterior.isEnabled = true }
        }

    }
}