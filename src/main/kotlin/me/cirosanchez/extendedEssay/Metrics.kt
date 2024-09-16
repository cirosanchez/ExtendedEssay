package me.cirosanchez.extendedEssay

import java.lang.management.ManagementFactory

class Metrics {
    var updates = 0
    var queries = 0
    var millis = 0L
    var totalCpuTime = 0L

    // Method to get the process CPU time (specific to your JVM)
    private fun getProcessCpuTime(): Long {
        val osBean = ManagementFactory.getOperatingSystemMXBean() as com.sun.management.OperatingSystemMXBean
        return osBean.processCpuTime
    }

    // Method to track CPU usage for a given block of code (e.g., plugin operations)
    fun trackCpuUsage(block: () -> Unit) {
        // Capture CPU time before running the block
        val startCpuTime = getProcessCpuTime()
        // Capture start time in milliseconds
        val startTime = System.currentTimeMillis()

        // Run the code block (this represents plugin code)
        block()

        // Capture the end CPU time and elapsed time
        val endCpuTime = getProcessCpuTime()
        val endTime = System.currentTimeMillis()

        // Calculate CPU time used and total time elapsed for this operation
        val cpuTimeUsed = endCpuTime - startCpuTime
        val elapsedMillis = endTime - startTime

        // Add to total CPU time and elapsed time
        totalCpuTime += cpuTimeUsed
        millis += elapsedMillis
    }

    // Method to print the metrics including CPU usage
    fun printMetrics() {
        println("--- Metrics ---")
        println("Updates: $updates")
        println("Queries: $queries")
        println("Total time: $millis ms")
        println("Total CPU time used: ${totalCpuTime / 1_000_000} ms") // Convert from nanoseconds to milliseconds
        println("CPU usage: ${getCpuUsagePercentage()}%")
        println("--- Metrics ---")
    }

    // Method to calculate CPU usage percentage based on tracked CPU time and total elapsed time
    private fun getCpuUsagePercentage(): Double {
        if (millis == 0L) return 0.0 // Avoid division by zero
        return (totalCpuTime / 1_000_000.0) / millis * 100
    }
}
