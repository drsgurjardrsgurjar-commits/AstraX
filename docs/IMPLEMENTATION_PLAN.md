# AstraX — Implementation Plan & Milestones

This file lists a phased plan with deliverables, approximate tasks and PR sizing. We'll implement features in small, reviewable PRs.

Phase 0 — Repo & scaffold (CURRENT PR)
- Create Flutter scaffold under /flutter/
- Add docs (ARCHITECTURE + IMPLEMENTATION_PLAN)
- Add GitHub Actions for Flutter CI

Phase 1 — Core auth & home
- Firebase integration (placeholders) or pluggable auth service
- Auth screens: Google Sign-In & Phone OTP
- Owner registration flow (secure device registration)

Phase 2 — Movies Player (basic)
- Player screen with HLS playback (player controls stub)
- Suggested movies list UI (mock data)
- Comments & likes UI wired to Firestore (mockable)

Phase 3 — Player advanced features
- Fullscreen toggle & orientation handling
- Double-tap seek (+10/-10)
- Prev/Next, playlist support
- Quality selector (hook to different HLS variants)
- PiP / background playback (Android manifest + iOS entitlements instructions)

Phase 4 — Chat & WebRTC calls
- Chat UI with attachments (upload flow to Storage)
- Signaling server + flutter_webrtc integration
- TURN server setup instructions
- Voice & Video call flows (1:1 and group stub)

Phase 5 — Offline Mesh prototype
- Platform adapters for Nearby / MultipeerConnectivity
- Basic text & small media transfer over mesh with discovery
- UX messaging about range limitations

Phase 6 — Secret Vault & Panic Switch
- Encrypted local DB for vault + PIN/Fingerprint gating
- Panic switch implementation (preloaded study content & fast switch animation)

Phase 7 — Admin / Owner controls
- Hidden admin panel (7-tap unlock) + owner APIs
- Bulk uploads and moderation endpoints

Phase 8 — Testing, monitoring & release
- Add unit/integration tests, CI flows
- Crashlytics / Sentry integration
- Documentation & deployment runbook


Merge strategy & PR size
- Each Phase broken into 4–8 PRs of manageable size.
- Keep PRs focused: UI + mocks first, then wiring to services.


