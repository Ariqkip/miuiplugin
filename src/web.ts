import { WebPlugin } from '@capacitor/core';
import type { ImagePickerMiuiPlugin } from './definitions';

export class ImagePickerMiuiWeb extends WebPlugin implements ImagePickerMiuiPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('Echo:', options.value);
    return options;
  }

  async imagePickerMiui(): Promise<{ files: Array<{ filePath: string; fileName: string; mimeType: string; fileSize: number; width?: number; height?: number }> }> {
    console.log('ImagePickerMiui not implemented on the web:');
    return { files: [] };
  }
}
