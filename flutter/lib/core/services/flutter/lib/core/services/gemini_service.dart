import 'dart:convert';
import 'package:http/http.dart' as http;

class GeminiService {
  final String apiKey;
  GeminiService(this.apiKey);

  Future<String> generateText(String prompt) async {
    final url = Uri.parse(
      'https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$apiKey',
    );
    final resp = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'contents': [
          {
            'parts': [
              {'text': prompt}
            ]
          }
        ]
      }),
    );
    if (resp.statusCode != 200) {
      throw Exception('Gemini API Error: ${resp.body}');
    }
    final data = jsonDecode(resp.body);
    return data['candidates'][0]['content']['parts'][0]['text'] ?? '';
  }
}

