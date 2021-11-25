import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';

import { FirmComponent } from './firm.component';
import { firmRoute } from './firm.route';
import { ToastModule } from 'primeng/toast';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CalendarModule } from 'primeng/calendar';
import { InputMaskModule } from 'primeng/inputmask';
import { ImageModule } from 'primeng/image';

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([firmRoute]),
    ToastModule,
    InputTextareaModule,
    CalendarModule,
    InputMaskModule,
    ImageModule,
  ],
  declarations: [FirmComponent],
})
export class FirmModule {}
