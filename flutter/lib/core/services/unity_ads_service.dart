import 'package:flutter/services.dart';

class UnityAdsService {
  static const MethodChannel _channel = MethodChannel('com.astrax/ads');

  Future<bool> showInterstitial() async {
    try {
      final res = await _channel.invokeMethod('showInterstitial');
      return res == true;
    } catch (_) {
      return false;
    }
  }

  Future<bool> showRewarded() async {
    try {
      final res = await _channel.invokeMethod('showRewarded');
      return res == true;
    } catch (_) {
      return false;
    }
  }
}

