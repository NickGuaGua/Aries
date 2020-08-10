package com.nickgua.aries

import android.app.Activity
import android.app.AlertDialog

fun Activity.alert(alertItem: AlertItem) {
    AlertDialog.Builder(this).apply {
        alertItem.title?.let { setTitle(it) }
        setMessage(alertItem.message)
        setPositiveButton(alertItem.positiveButton.text) { dialog, _ ->
            alertItem.positiveButton.function.invoke()
            dialog.dismiss()
        }
        alertItem.negativeButton?.let { negativeButton ->
            setNegativeButton(negativeButton.text) { dialog, _ ->
                negativeButton.function.invoke()
                dialog.dismiss()
            }
        }
        setCancelable(false)
    }.show()
}