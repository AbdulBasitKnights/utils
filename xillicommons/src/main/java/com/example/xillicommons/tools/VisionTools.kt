package com.example.xillicommons.tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.xillicommons.R
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
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
    fun convertToEnglishDate(date: String): String {

        return try {

            val initDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val formatter = SimpleDateFormat("dd MMM YYYY", Locale.ENGLISH)
                return formatter.format(initDate)
            }
            else{
                val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
                return formatter.format(initDate)
            }


        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            ""
        }


    }

    fun timePicker(context: FragmentActivity?,theme:Int, callback: (String?) -> Unit) {
        try {
            val hour:Int?=0
            val minute:Int?=0
            val timePicker = hour?.let { it1 ->
                try {
                    minute?.let { it2 ->
                        MaterialTimePicker.Builder()
                            .setTimeFormat(TimeFormat.CLOCK_12H)
                            .setTheme(theme)
                            .setHour(it1)  // Set current hour
                            .setMinute(it2)  // Set current minute
                            .build()
                    }
                }
                catch (e:Exception){
                    minute?.let { it2 ->
                        MaterialTimePicker.Builder()
                            .setTimeFormat(TimeFormat.CLOCK_12H)
                            .setHour(it1)  // Set current hour
                            .setMinute(it2)  // Set current minute
                            .build()
                    }
                }
            }
            timePicker?.addOnPositiveButtonClickListener {
                val selectedHour = timePicker.hour
                val selectedMinute = timePicker.minute
                val amPm = if (selectedHour < 12) "AM" else "PM"  // Correct AM/PM logic
                val displayHour = if (selectedHour == 0) 12 else selectedHour % 12

                val formattedTime = String.format("%02d:%02d %s", displayHour, selectedMinute, amPm)
                val time=formattedTime// Use 'text' property for efficiency
                callback(time)
            }
            timePicker?.show(context?.supportFragmentManager!!, "timePicker")
        }
        catch (e:Exception){
            //
        }

    }

    fun convertToEnglishTime(dateTime:String):String{
        return try {
            // Use ThreeTenABP for API levels below 26
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
                val date: Date
                try {
                    date = inputFormat.parse(dateTime)
                } catch (e: Exception) {
                    e.printStackTrace()
                    return dateTime
                }
                val outputFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                val formattedTime = outputFormat.format(date)
                return formattedTime
            } else {
                // Use java.time for API levels 26 and above
                val parsedDateTime = LocalDateTime.parse(dateTime)
                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val formattedTime = parsedDateTime.format(formatter)
                return formattedTime
            }
        } catch (e: Exception) {
            // Handle parsing errors gracefully
            // You can log the error here for debugging, if needed
            dateTime
        }
    }

}