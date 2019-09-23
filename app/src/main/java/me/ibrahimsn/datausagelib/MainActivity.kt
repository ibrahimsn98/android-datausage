package me.ibrahimsn.datausagelib

import android.app.usage.NetworkStatsManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import me.ibrahimsn.library.DataUsageManager
import me.ibrahimsn.library.Interval
import me.ibrahimsn.library.NetworkType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        //ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 34)

        val networkStatsManager = getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val manager = DataUsageManager(networkStatsManager, telephonyManager.subscriberId)

        // Monitor single interval
        manager.getUsage(Interval.today, NetworkType.MOBILE)

        // Monitor multiple interval
        manager.getMultiUsage(listOf(Interval.month, Interval.last30days), NetworkType.WIFI)

        // Observe realtime usage
        manager.getRealtimeUsage(NetworkType.WIFI).subscribe()
    }
}
