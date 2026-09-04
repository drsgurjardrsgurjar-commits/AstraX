# AstraX Core - Skeleton Implementation (White-label)

Purpose
- Provide skeleton files to implement AstraX-native media, reels, AI core, chat and secure vault.
- All UI strings and logs must show AstraX branding only — no third-party names.

Files added
- AppDatabase.java, MediaEntities.java
- OwnerInjectorDialog.java
- AstraCinemaPlayer.java
- AstraReelsAdapter.java
- AstraNeuralEngine.java
- AstraChatManager.java
- AstraSecretVault.java

Integration
1. Copy Java files into your app module (`app/src/main/java/com/astrax/...`).
2. Add required layouts (dialog_owner_injector.xml, item_reel.xml) in res/layout — placeholders used in code.
3. Provide Room database singleton in Application class and supply DAO implementations.
4. Wire AstraNeuralEngine to your private model/service endpoint.
5. Implement actual KeyStore encryption logic inside AstraSecretVault following Android docs.
6. Test on device (camera/network features require real device).

Push instructions
- Create branch: `git checkout -b feature/astrax-core`
- Add files, commit, push and open PR.

If you want me to push these into your GitHub repo:
- Make repo public OR grant me permission (explain how you will invite CI/bot).
- Tell me branch name to create (or I will create `feature/astrax-core`).

Questions / Next work
- Do you want full implementation of Wi-Fi Direct mesh & WebSocket server code?
- Do you want the OwnerInjector UI XMLs and sample layouts? I can add those next.
