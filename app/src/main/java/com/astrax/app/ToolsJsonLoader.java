package com.astrax.app;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class ToolsJsonLoader {
    
    public static List<ToolModel> load(Context context) {
        List<ToolModel> tools = new ArrayList<>();
        
        // AI Studio Tools
        tools.add(new ToolModel(1, "ChatGPT Clone", "AI powered chat assistant", "AI", 
            "Ask anything...", "Response generated"));
        tools.add(new ToolModel(2, "Image Generator", "Generate images with AI", "AI", 
            "Describe image...", "Image generated"));
        tools.add(new ToolModel(3, "Text Summarizer", "Summarize long texts", "AI", 
            "Paste text...", "Summary created"));
        
        // Offline Tools
        tools.add(new ToolModel(4, "Calculator", "Advanced calculator", "Offline", 
            "Enter expression...", "Result: 0"));
        tools.add(new ToolModel(5, "Unit Converter", "Convert units", "Offline", 
            "Enter value...", "Converted"));
        tools.add(new ToolModel(6, "QR Code Generator", "Generate QR codes", "Offline", 
            "Enter text...", "QR Generated"));
        
        // Privacy Shield Tools
        tools.add(new ToolModel(7, "Password Generator", "Generate strong passwords", "Privacy", 
            "Length...", "Password: ****"));
        tools.add(new ToolModel(8, "Text Encryptor", "Encrypt/Decrypt text", "Privacy", 
            "Enter text...", "Encrypted"));
        tools.add(new ToolModel(9, "VPN Info", "VPN connection status", "Privacy", 
            "Check...", "Status: Active"));
        
        // Social Savers Tools
        tools.add(new ToolModel(10, "Instagram Downloader", "Download Instagram posts", "Savers", 
            "Paste link...", "Downloaded"));
        tools.add(new ToolModel(11, "TikTok Downloader", "Download TikTok videos", "Savers", 
            "Paste link...", "Downloaded"));
        tools.add(new ToolModel(12, "YouTube Downloader", "Download YouTube videos", "Savers", 
            "Paste link...", "Downloaded"));
        
        // Office & PDF Tools
        tools.add(new ToolModel(13, "PDF Merger", "Merge multiple PDFs", "Office", 
            "Select files...", "Merged"));
        tools.add(new ToolModel(14, "PDF Splitter", "Split PDF files", "Office", 
            "Select range...", "Split"));
        tools.add(new ToolModel(15, "Word to PDF", "Convert Word to PDF", "Office", 
            "Select file...", "Converted"));
        
        // Desi Life Tools
        tools.add(new ToolModel(16, "Gold Price Tracker", "Check gold prices", "Desi", 
            "City...", "Price: ₹5000/gm"));
        tools.add(new ToolModel(17, "Railway PNR Status", "Check train booking status", "Desi", 
            "PNR number...", "Status: Confirmed"));
        tools.add(new ToolModel(18, "Electricity Bill Calculator", "Calculate electricity bill", "Desi", 
            "Units used...", "Bill: ₹1200"));
        
        // Media Studio Tools
        tools.add(new ToolModel(19, "Audio Converter", "Convert audio formats", "Media", 
            "Select audio...", "Converted"));
        tools.add(new ToolModel(20, "Video Compressor", "Compress video files", "Media", 
            "Select video...", "Compressed"));
        tools.add(new ToolModel(21, "Image Resizer", "Resize images", "Media", 
            "Select image...", "Resized"));
        
        // System Boost Tools
        tools.add(new ToolModel(22, "RAM Cleaner", "Clean device memory", "Boost", 
            "Optimize...", "Cleaned: 512MB"));
        tools.add(new ToolModel(23, "Battery Saver", "Optimize battery usage", "Boost", 
            "Enable...", "Battery: 85%"));
        tools.add(new ToolModel(24, "Storage Cleaner", "Free up storage", "Boost", 
            "Scan...", "Cleaned: 1GB"));
        
        // Pro Utilities
        tools.add(new ToolModel(25, "Network Speed Test", "Test internet speed", "Pro", 
            "Start test...", "Speed: 50 Mbps"));
        tools.add(new ToolModel(26, "Device Info", "Show device details", "Pro", 
            "Check...", "Model: XYZ"));
        tools.add(new ToolModel(27, "App Manager", "Manage installed apps", "Pro", 
            "Select app...", "Managed"));
        
        return tools;
    }
}
