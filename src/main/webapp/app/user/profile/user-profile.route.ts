import { Route } from '@angular/router';

import { UserProfileComponent } from './user-profile.component';

export const userProfileRoute: Route = {
  path: '',
  component: UserProfileComponent,
  data: {
    pageTitle: 'global.menu.account.main',
  },
};
