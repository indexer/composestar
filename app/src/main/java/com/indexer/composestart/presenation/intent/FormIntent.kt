package com.indexer.composestart.presenation.intent

sealed class FormIntent {
    object LoadForm : FormIntent()
    data class FieldChanged(val key: String, val value: String) : FormIntent()
    object SubmitForm : FormIntent()
}
