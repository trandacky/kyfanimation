import { Route } from '@angular/router';

import { FirmComponent } from './firm.component';

export const firmRoute: Route = {
  path: '',
  component: FirmComponent,
  data: {
    pageTitle: 'global.menu.account.main',
  },
};
