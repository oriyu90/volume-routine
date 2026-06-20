# Volume Routine (ボリュームルーチン)

Volume Routine is a lightweight, background-running Android application that automates your device's volume and Do Not Disturb (DND) settings based on specific triggers such as Time, Wi-Fi connection, or manual execution. 

## Features
- **Time Trigger**: Automatically change volume settings at a specific time and day(s) of the week.
- **Wi-Fi Trigger**: Automatically change volume settings when connected to a specific Wi-Fi network (SSID).
- **Manual Trigger**: Execute volume changes with a tap of a button.
- **Silent Mode (DND)**: Automatically enable Do Not Disturb mode.
- **Volume Control**: Precisely adjust the Media, Alarm, and Ringtone volumes independently.
- **Lightweight & Battery Efficient**: Designed to run efficiently in the background.

## How to Use
1. **Grant Permission**: When you launch the app for the first time, you will be prompted to grant "Do Not Disturb" (Notification Policy) access. This is required for the app to change your volume modes.
2. **Create a Routine**: 
   - Tap the `+` floating action button.
   - Enter a name for the routine.
   - Select a trigger constraint (`TIME`, `WIFI`, or `MANUAL`).
   - Configure the corresponding trigger condition (e.g., time and days, or the specific Wi-Fi SSID).
   - Scroll down to set the "Actions". You can enable Silent Mode, or set exact volume percentages for Media, Alarm, and Ringtone.
   - Save the routine. The routine will become active immediately if enabled.
3. **Manage Routines**: You can toggle routines on and off, manually run them using "Run Now", or delete them from the main screen.

## Installation
A pre-built APK is available in the root of the source code (`VolumeRoutine_v1.0.0.apk`).
You can transfer it to your Android device and install it directly.

## License
This application is distributed under the GPL-3.0 License. See the `LICENSE.md` file for details.
Developer: yuki orita
