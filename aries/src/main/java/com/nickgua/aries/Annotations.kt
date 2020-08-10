package com.nickgua.aries

import android.widget.Toast
import androidx.annotation.IntDef

@IntDef(value = [Toast.LENGTH_SHORT, Toast.LENGTH_LONG])
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class Duration