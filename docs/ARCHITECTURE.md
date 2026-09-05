# AstraX — Architecture Overview

This document describes the architecture and components for the AstraX app (Flutter) to implement the Movies & Cinema Engine, 3‑Way Chat Engine (WebRTC + Offline mesh), Secret Vault, Panic Switch and Owner Control Room features.

NOTE: This is a living document. Implementation details and chosen services (Firebase, CDN, TURN, ffmpeg) are included as recommended defaults to speed up prototyping and make the system functional.

1) High-level architecture

- Mobile client: Flutter (Android + iOS). Single codebase.
  - Modules: Auth, Player, Chat, WebRTC, Mesh, Vault, Panic, Admin.

- Backend & Infra (recommended):
  - Firebase: Auth (Google, Phone OTP), Firestore (comments, likes, metadata), Firebase Storage (uploads with signed URLs), Cloud Functions (server logic), FCM (push notifications).
  - Media pipeline: Transcoding service (ffmpeg in Cloud Functions or managed transcoding) → generate HLS (m3u8) with multiple renditions (144p..1080p) → CDN (CloudFront / Firebase Hosting) to serve segments.
  - Signalling server (WebRTC): Node.js + Socket.io (Cloud Run / Cloud Run/VM) or use Firebase RTDB for prototype signalling. Use coturn TURN server for NAT traversal.
  - Optional: Dedicated signaling + media server (Jitsi/Janus) for multi‑party mixing/MCU if needed.

- Offline mesh: platform-specific implementations:
  - Android: Nearby Connections API / Wi‑Fi Aware / Wi‑Fi Direct fallback + BLE for discovery
  - iOS: MultipeerConnectivity
  - Note: 1000m range is unrealistic on stock phones — document realistic expectations to user.

2) Data model (short)

- movies collection (Firestore): { id, title, posterUrl, hlsManifestUrl, category, duration, views, likes }
- comments subcollection under movies/{movieId}/comments: { userId, text, createdAt }
- users collection: { uid, displayName, photoUrl, isOwner, registeredDevices: [...] }
- chats collection (for cloud): { chatId, participants, lastMessage, updatedAt }

3) Core client responsibilities

- Playback: Expose player controls (fullscreen, double‑tap seek, prev/next, quality selector). Use video_player / better_player. Use native PiP support for Android; request entitlements on iOS.
- Chat: Text + media attachments (upload to Storage) + Firestore messages + WebRTC signaling for calls.
- WebRTC: Use flutter_webrtc plugin; signaling socket to exchange offers/answers; use TURN server.
- Mesh: Provide discovery and opportunistic P2P transfer API (platform adapter). Fallback to cloud when not in range.
- Vault: Local encrypted store for secret chats and media using device Keystore / Secure Enclave + SQLCipher.
- Panic: Local preloaded study content; fast screen switch; mask/hide sensitive resources.

4) Security & privacy notes

- Owner bypass must be carefully implemented with secure device registration and KeyStore/Keychain usage; avoid hardcoding.
- Private vault content should be client‑side encrypted — server never has plaintext unless user opts in.
- Media access & copyright: require proper rights.

5) Scalability & cost considerations

- HLS storage & CDN bandwidth may be main cost when serving many videos. Use adaptive bitrate and caching.
- TURN servers incur bandwidth cost — estimate for concurrent calls.

6) Limitations

- PiP: Full support on Android; iOS PiP requires entitlements and is not guaranteed across all devices.
- Offline mesh range: 1000m unrealistic on default mobile radios.
- Panic 1ms switch: perceptually instant possible but true 1ms impossible; preloading minimizes delay.


