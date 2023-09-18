package com.example.lockunlockdetection

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_ENABLE_ADMIN = 1

    private var devicePolicyManager: DevicePolicyManager? = null
    private var componentName: ComponentName? = null

    var enableAdmin: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
        enableAdmin = findViewById<View>(R.id.enableAdminButton) as Button

        // Initialize DevicePolicyManager and ComponentName
        devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)
        val enableAdminButton = findViewById<Button>(R.id.enableAdminButton)
        enableAdminButton.setOnClickListener {
            // Check if the app is already a device administrator
            if (!devicePolicyManager!!.isAdminActive(componentName!!)) {
                // If not, request device administrator privileges
                val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Your explanation here")
                startActivityForResult(intent, REQUEST_CODE_ENABLE_ADMIN)
            } else {
                // App is already a device administrator
                // You can handle this case as needed
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ENABLE_ADMIN) {
            if (resultCode == RESULT_OK) {
                // Device admin privileges granted
                // You can handle this case as needed
            } else {
                // Device admin privileges not granted
                // You can handle this case as needed
            }
        }
    }
}