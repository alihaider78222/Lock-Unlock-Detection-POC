
# Android Lock/Unlock Detection POC

This Android Kotlin application is designed to detect lock and unlock events on a mobile device. Whenever the user enters the wrong PIN, a callback is triggered, and a log message is printed along with a toast message displayed on the screen. This app also allows the user to enable it as a device administrator.

## Introduction

Modern Android devices come with a range of security features, including device administrator capabilities. This app utilizes the Device Administrator API to monitor lock and unlock events on the device. When an incorrect PIN is entered, it logs the event and displays a toast message.

<br>

## 1. Permissions

The poc app requires the following permissions to function properly:

```android.permission.BIND_DEVICE_ADMIN```: This permission allows the app to become a device administrator, enabling it to monitor device events and perform actions like locking the device.
```android.permission.RECEIVE_BOOT_COMPLETED```: This permission allows the app to receive a notification when the device boots up, ensuring that the monitoring functionality remains active.

```
<uses-permission android:name="android.permission.BIND_DEVICE_ADMIN" tools:ignore="ProtectedPermissions"/>
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
```

```
<receiver android:name=".SimChangeReceivers"
    android:name=".MyDeviceAdminReceiver"
    android:label="DeviceAdminReceiver"
    android:permission="android.permission.BIND_DEVICE_ADMIN"
    android:exported="true">
    <meta-data
        android:name="android.app.device_admin"
        android:resource="@xml/device_admin_policies" />
    <intent-filter>
        <action android:name="android.app.action.DEVICE_ADMIN_ENABLED" />
    </intent-filter>
</receiver>
```

## 2. MyDeviceAdminReceiver.kt

This Kotlin class extends ```DeviceAdminReceiver``` and handles various device administration events. It logs these events and displays toast messages.

```
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

```

## 3. res/xml/device_admin_policies.xml

This XML file specifies the policies that can be enforced by the device administrator. In this case, various password-related policies are defined.

## 4. MainActivity.kt

The ```MainActivity.kt``` class contains a button that allows the user to enable the app as a device administrator..

<br>

## How to Use

1. Enable Device Administrator:

* Launch the app.
* Click the "Enable Device Administrator" button.
* Follow the prompts to enable the app as a device administrator. This step is necessary for the app to monitor device events.

2. Lock/Unlock Detection:

* Once the app is enabled as a device administrator, it will monitor lock and unlock events.
*If the user enters an incorrect PIN, the app will log the event and display a toast message on the screen.

<br>

## Contributing

Contributions are welcome! If you have any improvements or bug fixes, please submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](https://choosealicense.com/licenses/mit/) file for details.

## Acknowledgments

Special thanks to the Android developer community for their support and contributions.

<br>

## Contact

If you have any questions or need further assistance, feel free to contact the project owner:

Ali Haider

<alihaider78222@gmail.com>

<https://github.com/alihaider78222>