package com.deveric.plugins.imagepickermiui;

import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import com.getcapacitor.PluginCall;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImagePickerMiui {

    private static final int IMAGE_PICK_REQUEST = 2; // Request code for image picking
    private Activity activity;

    public ImagePickerMiui(Activity activity) {
        this.activity = activity;
    }

    // Echo method for testing
    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    // Method to launch the image picker
    public void launchImagePicker(PluginCall call) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Enable multiple image selection
        activity.startActivityForResult(intent, IMAGE_PICK_REQUEST);
    }


    // Handle the result from the image picker
    public List<Map<String, Object>> handleImagePickerResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<Map<String, Object>> fileDetailsList = new ArrayList<>();

        if (requestCode == IMAGE_PICK_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            GetFileDetails fileDetailsHelper = new GetFileDetails(activity);

            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    Map<String, Object> details = fileDetailsHelper.getFileDetails(imageUri);
                    if (details != null) {
                        fileDetailsList.add(details);
                    }
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();

                Map<String, Object> details = fileDetailsHelper.getFileDetails(imageUri);
                if (details != null) {
                    fileDetailsList.add(details);

                }
            }
        }
        return fileDetailsList;
    }

}
