package me.ibrahimsn.library

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import io.reactivex.Observable

class DataUsageManager(private val statsManager: NetworkStatsManager,
                       private val subscriberId: String) {

    fun getRealtimeUsage(networkType: NetworkType): Observable<Usage> {
        return Observable.create {
            when (networkType) {
                NetworkType.MOBILE -> {
                    it.onNext(Usage(TrafficStats.getMobileRxBytes(), TrafficStats.getMobileTxBytes()))
                }
                NetworkType.WIFI -> {
                    it.onNext(Usage(TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes(),
                                    TrafficStats.getTotalTxBytes() - TrafficStats.getMobileTxBytes()))
                }
            }
        }
    }

    fun getUsage(interval: TimeInterval, networkType: NetworkType): Usage {
        val stats = statsManager.queryDetails(when (networkType) {
            NetworkType.MOBILE -> NetworkCapabilities.TRANSPORT_CELLULAR
            NetworkType.WIFI -> NetworkCapabilities.TRANSPORT_WIFI
        }, subscriberId, interval.start, interval.end)

        val bucket = NetworkStats.Bucket()
        val usage = Usage()

        while (stats.hasNextBucket()) {
            stats.getNextBucket(bucket)

            usage.downloads += bucket.rxBytes
            usage.uploads += bucket.txBytes
        }

        stats.close()
        return usage
    }

    fun getMultiUsage(intervals: List<TimeInterval>, networkType: NetworkType): List<Usage> {
        var start = intervals[0].start
        var end = intervals[0].end

        val usages = mutableMapOf<TimeInterval, Usage>()

        for (interval in intervals) {
            if (interval.start < start)
                start = interval.start

            if (interval.end > end)
                end = interval.end

            usages[interval] = Usage()
        }

        val stats = statsManager.queryDetails(when (networkType) {
            NetworkType.MOBILE -> NetworkCapabilities.TRANSPORT_CELLULAR
            NetworkType.WIFI -> NetworkCapabilities.TRANSPORT_WIFI
        }, subscriberId, start, end)

        val bucket = NetworkStats.Bucket()

        while (stats.hasNextBucket()) {
            stats.getNextBucket(bucket)

            for (interval in intervals) {
                if (checkBucketInterval(bucket, interval.start, interval.end)) {
                    usages[interval]!!.downloads += bucket.rxBytes
                    usages[interval]!!.uploads += bucket.txBytes
                }
            }
        }

        stats.close()
        return usages.values.toList()
    }

    private fun checkBucketInterval(bucket: NetworkStats.Bucket, start: Long, end: Long): Boolean {
        return ((bucket.startTimeStamp in start..end) || (bucket.endTimeStamp in (start + 1) until end))
    }
}