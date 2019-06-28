package com.example.mathlearn.app_view.sound

import android.content.Context
import android.media.RingtoneManager


fun playDefaultNotiSound(context: Context){

    try {
        val uri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val r=RingtoneManager.getRingtone(context,uri)

        r.play()
    }catch (e:RuntimeException){
        return
    }



}