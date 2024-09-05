import { registerPlugin } from '@capacitor/core';

import type { ImagePickerMiuiPlugin } from './definitions';

const ImagePickerMiui = registerPlugin<ImagePickerMiuiPlugin>(
  'ImagePickerMiui',
  {
    web: () => import('./web').then(m => new m.ImagePickerMiuiWeb()),
  },
);

export * from './definitions';
export { ImagePickerMiui };
