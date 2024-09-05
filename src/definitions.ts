export interface ImagePickerMiuiPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
