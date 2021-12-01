import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';

import { TheloaiComponent } from './theloai.component';
import { theloaiRoute } from './theloai.route';
import { ToastModule } from 'primeng/toast';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CalendarModule } from 'primeng/calendar';
import { InputMaskModule } from 'primeng/inputmask';
import { ImageModule } from 'primeng/image';
import {DialogModule} from "primeng/dialog";

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([theloaiRoute]),
    ToastModule,
    InputTextareaModule,
    CalendarModule,
    InputMaskModule,
    ImageModule,
    DialogModule,
  ],
  declarations: [TheloaiComponent],
})
export class TheloaiModule {}
