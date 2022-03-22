package br.com.gabrielvieira.rafaelserres.projetofinal.model

import androidx.databinding.*

class Location {
    private var value = ObservableField<String>()

    fun getValue() = this.value

    fun setValue(value: String){
        this.value.set(value)
    }
}