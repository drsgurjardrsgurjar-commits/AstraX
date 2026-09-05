package com.dm.astrax

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import com.unity3d.ads.UnityAds

class MainActivity: FlutterActivity() {
  private val CHANNEL = "com.astrax/ads"
  private val UNITY_GAME_ID = "6185422"
  private val INTERSTITIAL_PLACEMENT = "video"
  private val REWARDED_PLACEMENT = "rewardedVideo"

  override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
    super.configureFlutterEngine(flutterEngine)

    // Initialize Unity Ads
    try {
      UnityAds.initialize(this, UNITY_GAME_ID, false)
    } catch (e: Exception) {
      // log or ignore in demo
    }

    MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
      when (call.method) {
        "showInterstitial" -> {
          try {
            if (UnityAds.isReady(INTERSTITIAL_PLACEMENT)) {
              UnityAds.show(this, INTERSTITIAL_PLACEMENT)
              result.success(true)
            } else {
              result.success(false)
            }
          } catch (e: Exception) {
            result.error("ad_error", e.localizedMessage, null)
          }
        }
        "showRewarded" -> {
          try {
            if (UnityAds.isReady(REWARDED_PLACEMENT)) {
              UnityAds.show(this, REWARDED_PLACEMENT)
              result.success(true)
            } else {
              result.success(false)
            }
          } catch (e: Exception) {
            result.error("ad_error", e.localizedMessage, null)
          }
        }
        else -> result.notImplemented()
      }
    }
  }
}
