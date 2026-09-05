# Developer Brief — DMAstraX (redacted)

Hi, here are the finalized details, Firebase config (redacted), and Unity Ads setup for DMAstraX. I have redacted sensitive fields — please add the real Firebase JSON into GitHub Actions secrets as instructed below before running the automated build.

1) App Display Name: DMAstraX
2) Package ID: com.dm.astrax
3) Firebase Setup: Firebase project created on Spark (Free tier).
   - google-services.json content has been redacted in this repo. Do NOT commit the raw google-services.json to the public repo.
   - Auth: Google Sign-In and Email/Password only (No SMS OTP).

4) TURN/STUN Server: Use free STUN: stun:stun.l.google.com:19302. For TURN use free/open relay providers (Metered.ca / OpenRelay) if required for higher reliability.

5) CDN / Storage & Player: Use no paid CDN. Player must support both direct MP4 URLs and external HLS (.m3u8) streams. Use public sample HLS / mock data for testing.

6) Keystore: Debug APK only required for now (no release keystore needed).

7) Access & Architecture: You have full permission to work on the repo. Keep architecture modular and scalable for future infra upgrades.

Ad Monetization Setup (Unity Ads Configured):
  * Unity Android Game ID: (use secret UNITY_ANDROID_GAME_ID)
  * Do NOT include App Open Ads.
  * Video Player Ads: Pre-roll, Mid-roll, and Rewarded Ads for 1080p content.
  * Browse Screen: Native Ads between movie/show cards.
  * Chat & Secret Vault Ads:
    - Native Ad in chat list.
    - Interstitial Ad when returning from a chat room.
    - Rewarded/Interstitial Ad to unlock Secret Vault / Hidden Chat.

-------------------------
Redacted google-services.json
-------------------------
{ /* REDACTED - DO NOT STORE PLAIN IN REPO */
  "project_info": {
    "project_number": "406894020255",
    "project_id": "dmastrax-d9a74",
    "storage_bucket": "dmastrax-d9a74.firebasestorage.app"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "REDACTED",
        "android_client_info": { "package_name": "com.dm.astrax" }
      },
      "oauth_client": [],
      "api_key": [ { "current_key": "<REDACTED>" } ],
      "services": { "appinvite_service": { "other_platform_oauth_client": [] } }
    }
  ],
  "configuration_version": "1"
}


HOW TO ADD SECRETS (required for automated build)
1) Encode your real google-services.json as base64 on your machine:
   - Linux / macOS: base64 -w 0 google-services.json > google-services.json.base64
   - Windows (PowerShell): [Convert]::ToBase64String([IO.File]::ReadAllBytes("google-services.json")) > google-services.json.base64

2) In GitHub repository: Settings → Secrets and variables → Actions → New repository secret
   - Name: GOOGLE_SERVICES_JSON
   - Value: (the contents of google-services.json.base64 file)

   - Name: UNITY_ANDROID_GAME_ID
   - Value: 6185422

3) (Optional) If you want iOS testing too, add GOOGLE_SERVICE_INFO_PLIST as base64 of GoogleService-Info.plist and the workflow can write it to ios/Runner/.

4) After adding the secrets, the GitHub Action created at .github/workflows/android-build.yml will decode the google-services.json, write it to flutter/android/app/google-services.json, inject UNITY_ANDROID_GAME_ID into an Android resource, run flutter pub get and build a debug APK, then upload the APK as a workflow artifact.

Where the debug APK will appear:
- GitHub Actions → selected workflow run → Artifacts → astrax-debug-apk (app-debug.apk)

Security notes:
- Do not commit plaintext google-services.json or API keys to the repo. Use GitHub Actions secrets as instructed above.
- Do not share the base64 secret publicly.

Requested next steps I will perform now (after you confirm secrets exist in the repo):
- Kick off the build workflow on feat/arch-doc (it will run automatically on push of this branch if secrets are present).
- When build finishes, I will fetch the artifact and provide the debug APK download link (or upload it where you prefer).

