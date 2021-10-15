import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';

import { UserProfileComponent } from './user-profile.component';
import { userProfileRoute } from './user-profile.route';
import { ToastModule } from 'primeng/toast';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CalendarModule } from 'primeng/calendar';
import { InputMaskModule } from 'primeng/inputmask';
import { ImageModule } from 'primeng/image';

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([userProfileRoute]),
    ToastModule,
    InputTextareaModule,
    CalendarModule,
    InputMaskModule,
    ImageModule,
  ],
  declarations: [UserProfileComponent],
})
export class UserProfileModule {}
