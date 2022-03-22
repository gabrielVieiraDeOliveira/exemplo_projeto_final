package br.com.gabrielvieira.rafaelserres.projetofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import br.com.gabrielvieira.rafaelserres.R
import br.com.gabrielvieira.rafaelserres.databinding.ActivityMainBinding
import br.com.gabrielvieira.rafaelserres.projetofinal.model.IObserver
import br.com.gabrielvieira.rafaelserres.projetofinal.model.Location
import br.com.gabrielvieira.rafaelserres.projetofinal.repository.RateAPI

class MainActivity : AppCompatActivity(),IObserver {
    private lateinit var binding: ActivityMainBinding
    private val cepValue = Location()
    private val logradouroValue = Location()
    private val bairroValue = Location()
    private val localidadeValue = Location()
    private val ufValue = Location()
    private lateinit var alert:AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        alert = AlertDialog.Builder(this).create()
        alert.setTitle("Aguarde...")
        alert.setMessage("Estamos Buscando seu CEP")

        binding.lifecycleOwner = this
        binding.cepValue = cepValue
        binding.logradouroValue = logradouroValue
        binding.bairroValue = bairroValue
        binding.localidadeValue = localidadeValue
        binding.ufValue = ufValue

        binding.btnConvert.setOnClickListener {
            alert.show()
            val cep = binding.edtCEP.text.toString()
            val rateAPI = RateAPI()
            rateAPI.getLocation(applicationContext,this,cep)
        }
    }

    override fun updateUI(data: MutableMap<String, Any>) {
        if (data.isNotEmpty()){
            binding.textCep.text = data["cep"]!! as String
            binding.textLogradouro.text = data["logradouro"]!! as String
            binding.textBairro.text = data["bairro"]!! as String
            binding.textLocalidade.text = data["localidade"]!! as String
            binding.textUf.text = data["uf"]!! as String
            alert.dismiss()
        }
    }
}