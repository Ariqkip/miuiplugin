declare module '@capacitor/core' {
  interface Plugins {
    ImagePickerMiui: ImagePickerMiuiPlugin;
  }
}

export interface ImagePickerMiuiPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;

  imagePickerMiui(): Promise<{
    files: Array<{
      filePath: string;
      fileName: string;
      mimeType: string;
      fileSize: number;
      width?: number;
      height?: number;
    }>;
  }>;
}
