import { Route } from '@angular/router';

import { NotificationComponent } from './notification.component';

export const notificationRoute: Route = {
  path: '',
  component: NotificationComponent,
  data: {
    pageTitle: 'global.menu.account.main',
  },
};
