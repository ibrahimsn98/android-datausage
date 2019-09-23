# Android Data Usage
An Android library to monitor cellular and wifi data usage easily

## Usage
```kotlin
<uses-permission android:name="android.permission.READ_PHONE_STATE" />

<uses-permission
    android:name="android.permission.PACKAGE_USAGE_STATS"
    tools:ignore="ProtectedPermissions" />

```

```kotlin
val networkStatsManager = getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

val manager = DataUsageManager(networkStatsManager, telephonyManager.subscriberId)

// Monitor single interval
manager.getUsage(Interval.today, NetworkType.MOBILE)

// Monitor multiple interval
manager.getMultiUsage(listOf(Interval.month, Interval.last30days), NetworkType.WIFI)

// Observe realtime usage
manager.getRealtimeUsage(NetworkType.WIFI).subscribe()
```

License
--------

    MIT License

	Copyright (c) 2018 ibrahim süren

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
	© 2018 Git