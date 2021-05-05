package pt.atp.bucketlist

import android.os.Build.MANUFACTURER
import android.os.Build.MODEL

fun deviceName() = "$MODEL-$MANUFACTURER"