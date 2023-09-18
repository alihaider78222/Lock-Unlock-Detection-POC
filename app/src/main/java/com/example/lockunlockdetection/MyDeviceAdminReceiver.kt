package com.example.lockunlockdetection

import android.R
import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.os.UserHandle
import android.util.Log
import android.widget.Toast


class MyDeviceAdminReceiver : DeviceAdminReceiver() {

    private val TAG = "MyDeviceAdminReceivers"

    private fun showToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    }

    override fun onEnabled(context: Context, intent: Intent) {
        Log.i(TAG, "onEnabled")
        showToast(context, "onEnabled")
    }

    override fun onDisableRequested(context: Context, intent: Intent): CharSequence =
        "onDisableRequested"

    override fun onDisabled(context: Context, intent: Intent) {
        Log.i(TAG, "onDisabled")
        showToast(context, "onDisabled")
    }

    override fun onPasswordChanged(context: Context, intent: Intent, userHandle: UserHandle) =
        showToast(context, "onPasswordChanged")

    override fun onPasswordFailed(context: Context, intent: Intent, userHandle: UserHandle) {
        Log.i(TAG, "onPasswordFailed")
        showToast(context, "onPasswordFailed")
    }

    override fun onPasswordSucceeded(context: Context, intent: Intent, userHandle: UserHandle) {
        Log.i(TAG, "onPasswordSucceeded")
        showToast(context, "onPasswordSucceeded")
    }


}
