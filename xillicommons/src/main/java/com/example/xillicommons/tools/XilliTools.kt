package com.example.xillicommons.tools

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

object XilliTools {
    fun privacy(context: Context?){
        try{
            val privacyIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.xilliapps.com/privacy-policy.html")
            )
            context?.startActivity(privacyIntent)
        }
        catch (e:Exception){
            //
        }
    }
    fun rateus(context: Context?){
        try {
            val packageName= context?.packageName
            val rateIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
            context?.startActivity(rateIntent)
        } catch (e: Exception) {
            Log.e("checkexcept",""+e)
            //
        }
    }
    fun shareapp(context: Context?){
        try {
            val appPackageName = context?.packageName
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out the App at: https://play.google.com/store/apps/details?id=$appPackageName"
            )
            sendIntent.type = "text/plain"
            context?.startActivity(sendIntent)
        }
        catch (e:Exception){
            Log.e("checkexcept",""+e)
            //
        }
    }
    fun showToast(message:String?,context: Context?) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show()
        }
        catch (e:Exception){
            Log.e("checkexcept",""+e)
            //
        }

    }
}