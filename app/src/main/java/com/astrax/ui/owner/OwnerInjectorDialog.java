package com.astrax.ui.owner;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.astrax.core.data.MediaEntities;
import com.astrax.core.data.MediaDao;
import com.astrax.R;

/**
 * OwnerInjectorDialog - Master dialog for owner-only bulk imports.
 * Trigger method: 7 taps on AstraX logo -> show this dialog (integrate in host app).
 */
public class OwnerInjectorDialog extends DialogFragment {

    private MediaDao mediaDao; // set via constructor or obtain from AppDatabase singleton

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = requireActivity().getLayoutInflater();
        View view = li.inflate(R.layout.dialog_owner_injector, null);
        EditText input = view.findViewById(R.id.bulk_input);
        Button importBtn = view.findViewById(R.id.import_btn);

        importBtn.setOnClickListener(v -> {
            String text = input.getText().toString();
            parseAndSave(text);
            dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("AstraX Owner Importer")
               .setView(view)
               .setNegativeButton("Cancel", (d, w) -> dismiss());
        return builder.create();
    }

    private void parseAndSave(String raw) {
        // Expected line format:
        // Title | StreamURL/ID | Category | PosterURL | Quality
        String[] lines = raw.split("\\r?\\n");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 2) {
                MediaEntities.Movie m = new MediaEntities.Movie();
                m.title = parts[0].trim();
                m.streamUrl = parts[1].trim();
                if (parts.length > 2) m.category = parts[2].trim();
                if (parts.length > 3) m.posterUrl = parts[3].trim();
                if (parts.length > 4) m.quality = parts[4].trim();
                // Insert async
                new Thread(() -> mediaDao.insertMovie(m)).start();
            }
        }
    }
}
