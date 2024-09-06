package com.deveric.plugins.imagepickermiui;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import android.content.Intent;
import androidx.annotation.Nullable;


import java.util.List;
import java.util.Map;


@CapacitorPlugin(name = "ImagePickerMiui")
public class ImagePickerMiuiPlugin extends Plugin {

    private ImagePickerMiui implementation;

    @Override
    public void load() {
        implementation = new ImagePickerMiui(getActivity());
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void imagePickerMiui(PluginCall call) {
        implementation.launchImagePicker(call); // Launch image picker
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<Map<String, Object>> fileDetails = implementation.handleImagePickerResult(requestCode, resultCode, data);

        JSObject result = new JSObject();
        result.put("files", fileDetails);
        PluginCall savedCall = getSavedCall(); // Get saved call to resolve the result
        if (savedCall != null) {
            savedCall.resolve(result);
        }
    }
}