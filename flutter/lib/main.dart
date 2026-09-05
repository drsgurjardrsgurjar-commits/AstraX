// Enhanced Flutter app: Home (movie list), Player with video_player, Vault and Chat stubs

import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'dart:async';
import 'package:flutter/services.dart';

void main() {
  runApp(AstraXApp());
}

class AstraXApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'DMAstraX',
      theme: ThemeData(primarySwatch: Colors.deepPurple),
      initialRoute: '/',
      routes: {
        '/': (c) => SplashScreen(),
        '/home': (c) => HomeScreen(),
        '/player': (c) => PlayerScreen(),
        '/vault': (c) => VaultScreen(),
        '/chat': (c) => ChatListScreen(),
      },
    );
  }
}

class SplashScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Future.delayed(Duration(milliseconds: 800), () {
      Navigator.pushReplacementNamed(context, '/home');
    });
    return Scaffold(body: Center(child: Text('DMAstraX — Loading...')));
  }
}

class HomeScreen extends StatelessWidget {
  final List<Map<String, String>> movies = [
    {
      'title': 'Big Buck Bunny (HLS)',
      'poster': 'https://peach.blender.org/wp-content/uploads/title_anouncement.jpg?x11217',
      'url': 'https://mnmedias.api.telequebec.tv/m3u8/29880.m3u8'
    },
    {
      'title': 'Sample MP4',
      'poster': 'https://flutter.dev/assets/homepage/carousel/slide_1-bg-455fb5f6b9a0e7f4c3a8a8f6ac2f4b9d547e7f6f7f2f1a4b6e4e6f7a8b9c0d1.png',
      'url': 'https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4'
    }
  ];

  void _showVault(BuildContext context) {
    Navigator.pushNamed(context, '/vault');
  }

  void _showChat(BuildContext context) {
    Navigator.pushNamed(context, '/chat');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('DMAstraX')),
      body: ListView(
        padding: EdgeInsets.all(12),
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              ElevatedButton.icon(
                onPressed: () => _showChat(context),
                icon: Icon(Icons.chat),
                label: Text('Chat'),
              ),
              ElevatedButton.icon(
                onPressed: () => _showVault(context),
                icon: Icon(Icons.lock),
                label: Text('Secret Vault'),
              ),
            ],
          ),
          SizedBox(height: 12),
          Text('Movies & Shows', style: Theme.of(context).textTheme.headline6),
          SizedBox(height: 8),
          ...movies.map((m) => MovieCard(title: m['title']!, poster: m['poster']!, url: m['url']!)),
        ],
      ),
    );
  }
}

class MovieCard extends StatelessWidget {
  final String title;
  final String poster;
  final String url;
  final MethodChannel _adsChannel = MethodChannel('com.astrax/ads');

  MovieCard({required this.title, required this.poster, required this.url});

  Future<void> _openPlayer(BuildContext context) async {
    // Request native ad show (interstitial) before playback — best-effort. Native may no-op if not present.
    try {
      await _adsChannel.invokeMethod('showInterstitial');
    } catch (e) {
      // ignore if native channel not implemented
    }

    Navigator.pushNamed(context, '/player', arguments: {'title': title, 'url': url});
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.symmetric(vertical: 8),
      child: InkWell(
        onTap: () => _openPlayer(context),
        child: Row(
          children: [
            Image.network(poster, width: 120, height: 70, fit: BoxFit.cover, errorBuilder: (c, e, s) => Container(width:120,height:70,color:Colors.grey)),
            SizedBox(width: 12),
            Expanded(child: Text(title)),
            Icon(Icons.play_arrow)
          ],
        ),
      ),
    );
  }
}

class PlayerScreen extends StatefulWidget {
  @override
  _PlayerScreenState createState() => _PlayerScreenState();
}

class _PlayerScreenState extends State<PlayerScreen> {
  VideoPlayerController? _controller;
  bool _isInitialized = false;
  final MethodChannel _adsChannel = MethodChannel('com.astrax/ads');

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    final args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>?;
    final url = args != null ? args['url'] as String? : null;
    if (url != null) {
      _controller = url.endsWith('.m3u8') ? VideoPlayerController.network(url) : VideoPlayerController.network(url);
      _controller!.initialize().then((_) {
        setState(() { _isInitialized = true; });
        _controller!.play();
      });
    }
  }

  @override
  void dispose() {
    _controller?.dispose();
    super.dispose();
  }

  Future<void> _showRewarded() async {
    try {
      final res = await _adsChannel.invokeMethod('showRewarded');
      // handle reward
    } catch (e) {
      // no-op
    }
  }

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>?;
    final title = args != null ? args['title'] as String? : 'Player';

    return Scaffold(
      appBar: AppBar(title: Text(title ?? 'Player')),
      body: Center(
        child: _isInitialized && _controller != null
            ? AspectRatio(
                aspectRatio: _controller!.value.aspectRatio,
                child: Stack(
                  children: [
                    VideoPlayer(_controller!),
                    Positioned(
                      right: 12,
                      bottom: 12,
                      child: ElevatedButton(
                        child: Text('Rewarded (1080p)'),
                        onPressed: () async {
                          await _showRewarded();
                        },
                      ),
                    )
                  ],
                ),
              )
            : CircularProgressIndicator(),
      ),
      floatingActionButton: _controller == null ? null : FloatingActionButton(
        onPressed: () => setState(() { _controller!.value.isPlaying ? _controller!.pause() : _controller!.play(); }),
        child: Icon(_controller!.value.isPlaying ? Icons.pause : Icons.play_arrow),
      ),
    );
  }
}

class VaultScreen extends StatefulWidget {
  @override
  _VaultScreenState createState() => _VaultScreenState();
}

class _VaultScreenState extends State<VaultScreen> {
  final TextEditingController _pinController = TextEditingController();
  bool _unlocked = false;

  void _unlock() {
    // insecure demo PIN: 1234 — replace with secure local encryption in production
    if (_pinController.text.trim() == '1234') {
      setState(() { _unlocked = true; });
    } else {
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Invalid PIN')));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Secret Vault')),
      body: Center(
        child: _unlocked
            ? Column(mainAxisSize: MainAxisSize.min, children: [Text('Vault Unlocked — secure content here'), SizedBox(height:12), ElevatedButton(child: Text('Lock'), onPressed: () => setState(() => _unlocked = false))])
            : Column(mainAxisSize: MainAxisSize.min, children: [Text('Enter PIN to unlock'), SizedBox(height:8), SizedBox(width:200, child: TextField(controller: _pinController, obscureText: true, decoration: InputDecoration(hintText: 'PIN'))), SizedBox(height:12), ElevatedButton(child: Text('Unlock'), onPressed: _unlock)]),
      ),
    );
  }
}

class ChatListScreen extends StatelessWidget {
  final List<String> chats = ['Alice', 'Bob', 'Cinema Group'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Chats')),
      body: ListView.builder(
        itemCount: chats.length,
        itemBuilder: (c, i) => ListTile(
          title: Text(chats[i]),
          onTap: () => Navigator.push(context, MaterialPageRoute(builder: (_) => ChatRoomScreen(name: chats[i]))),
        ),
      ),
    );
  }
}

class ChatRoomScreen extends StatelessWidget {
  final String name;
  ChatRoomScreen({required this.name});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(name)),
      body: Column(children: [Expanded(child: ListView()), Padding(padding: EdgeInsets.all(8), child: Row(children: [Expanded(child: TextField()), IconButton(icon: Icon(Icons.send), onPressed: () {})]))]),
    );
  }
}
