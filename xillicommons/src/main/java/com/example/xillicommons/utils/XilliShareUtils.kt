package com.example.xillicommons.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import java.io.File

object XilliShareUtils {
    @SuppressLint("QueryPermissionsNeeded")
    fun shareToApp(shareAppUrl:String, imagePath:String?, context: Context){
        try {
            val sharedProviderAuthority=context.packageName+".myfileprovider"
            val playStoreURL = "https://play.google.com/store/apps/details?id="
            if (imagePath!= null) {
                val sharedFile = File(imagePath)
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT, playStoreURL + context.packageName)
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://$imagePath"))
                val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) FileProvider.getUriForFile(
                        context,
                        sharedProviderAuthority,
                        sharedFile
                    ) else Uri.parse(
                        "file://$imagePath"
                    )
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                    shareIntent.type = "image/*"
                    shareIntent.setPackage("" + shareAppUrl)
                    shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    context.startActivity(Intent.createChooser(shareIntent, "Choose Your choice"))
                } else {
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                    shareIntent.type = "image/*"
                    shareIntent.setPackage("" + shareAppUrl)
                    if (shareIntent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(
                            Intent.createChooser(
                                shareIntent,
                                "Choose Your choice"
                            )
                        )
                    } else Toast.makeText(
                        context.applicationContext, "Not Installed", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        catch (e:Exception){
            //
        }
    }
    @SuppressLint("QueryPermissionsNeeded")
    fun shareCompat(imagePath:String, context: Activity){
        try {
            val sharedProviderAuthority=context.packageName+".myfileprovider"
            val playStoreURL = "https://play.google.com/store/apps/details?id="
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val sharedFile= File(imagePath)
                // Get the shared file's Uri
                val uri = FileProvider.getUriForFile(
                    context,
                    sharedProviderAuthority,
                    sharedFile
                )
                // Create a intent
                val intentBuilder = ShareCompat
                    .IntentBuilder(context)
                    .setType("image/*")
                    .setText(playStoreURL + context.packageName)
                    .addStream(uri)
                // Start the intent
                val chooserIntent = intentBuilder.createChooserIntent()
                chooserIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                if (chooserIntent.resolveActivity(context.packageManager) != null) context.startActivity(
                    chooserIntent
                )
            } else {
                //////
                val moreIntent = Intent()
                moreIntent.action = Intent.ACTION_SEND
                moreIntent.putExtra(Intent.EXTRA_TEXT, playStoreURL + context.packageName)
                moreIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://$imagePath"))
                moreIntent.type = "image/*"
                if (moreIntent.resolveActivity(context.packageManager) != null) context.startActivity(
                    Intent.createChooser(
                        moreIntent,
                        "Choose Your choice"
                    )
                )
            }
        }
        catch (e:Exception){
//
        }
    }
}