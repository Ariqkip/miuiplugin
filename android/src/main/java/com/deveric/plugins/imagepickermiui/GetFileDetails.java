package com.deveric.plugins.imagepickermiui;


import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;




public class GetFileDetails {
    private Activity activity;

    public GetFileDetails(Activity activity) {
        this.activity = activity;
    }

    // Method to retrieve file details for a given Uri
    public Map<String, Object> getFileDetails(Uri uri) {
        Map<String, Object> fileDetails = new HashMap<>();

        // Get file path
        String filePath = getRealPathFromURI(uri);
        if (filePath == null) {
            return null;
        }
        fileDetails.put("filePath", filePath);

        // Get file name
        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
        fileDetails.put("fileName", fileName);

        // Get MIME type
        String mimeType = activity.getContentResolver().getType(uri);
        if (mimeType == null) {
            mimeType = MimeTypeMap.getFileExtensionFromUrl(filePath);
        }
        fileDetails.put("mimeType", mimeType);

        // Get file size
        Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int sizeIndex = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);
            if (sizeIndex != -1) {
                long fileSize = cursor.getLong(sizeIndex);
                fileDetails.put("fileSize", fileSize);
            }
            cursor.close();
        }

        // Get image dimensions (if applicable)
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int width = options.outWidth;
        int height = options.outHeight;

        if (width > 0 && height > 0) {
            fileDetails.put("width", width);
            fileDetails.put("height", height);
        }
        return fileDetails;
    }

    // Helper method to get the real file path from a Uri
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return null;
    }
}
