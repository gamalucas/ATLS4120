package com.example.notes

data class NoteOption(var company: String="", var url: String="") {
    fun getUserOption(position:Int){
        setCompanyName(position)
        setCompanyUrl(position)
    }

    private fun setCompanyName(position:Int){
        when(position){
            0 -> company = "Google"
            1 -> company = "Microsoft"
            else -> "Not identified"
        }
    }
    private fun setCompanyUrl(position:Int){
        when(position){
            0 -> url = "https://drive.google.com/drive"
            1 -> url = "https://www.onenote.com/hrd"
            else -> "www.google.com"
        }
    }
}