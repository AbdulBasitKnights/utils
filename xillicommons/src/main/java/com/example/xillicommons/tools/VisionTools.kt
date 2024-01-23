package com.example.xillicommons.tools

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object VisionTools {
    fun privacy(context: Context?,url:String?){
        try{
            val privacyIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
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
    fun convertToEnglishDateTime(dateTimeString: String): String? {
        return try {
            // Use ThreeTenABP for API levels below 26
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US)
                val dateTime = formatter.parse(dateTimeString)

                val englishFormatter = SimpleDateFormat("MMMM d, yyyy 'at' h:mm a", Locale.US)
                englishFormatter.format(dateTime)
            } else {
                // Use java.time for API levels 26 and above
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
                val dateTime = LocalDateTime.parse(dateTimeString, formatter)
                val englishFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a")
                dateTime.format(englishFormatter)
            }
        } catch (e: Exception) {
            // Handle parsing errors gracefully
            // You can log the error here for debugging, if needed
            dateTimeString
        }
    }

}