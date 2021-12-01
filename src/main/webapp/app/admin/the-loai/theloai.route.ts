import { Route } from '@angular/router';

import { TheloaiComponent } from './theloai.component';

export const theloaiRoute: Route = {
  path: '',
  component: TheloaiComponent,
  data: {
    pageTitle: 'global.menu.account.main',
  },
};
