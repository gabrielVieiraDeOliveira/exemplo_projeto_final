package br.com.gabrielvieira.rafaelserres.projetofinal.model

interface IObserver {
    fun updateUI(data: MutableMap<String, Any>)
}