import { WebPlugin } from '@capacitor/core';

import type { ImagePickerMiuiPlugin } from './definitions';

export class ImagePickerMiuiWeb
  extends WebPlugin
  implements ImagePickerMiuiPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
