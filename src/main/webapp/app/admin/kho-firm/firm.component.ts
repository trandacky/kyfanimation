import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ToastEnumsConstant } from '../../config/ToastEnums.constant';
import { FirmService } from './firm.service';

@Component({
  selector: 'jhi-firm-component',
  templateUrl: './firm.component.html',
  styleUrls: ['./firm.component.scss'],
  providers: [MessageService],
})
export class FirmComponent implements OnInit {
  firm: any;
  totalItems = 0;
  page = 1;
  formTest!: FormGroup;
  formUpdateNotification: any;
  dateFormat!: string;
  constructor(private messageService: MessageService, private formBuilder: FormBuilder, private firmService: FirmService) {}

  ngOnInit(): void {
    this.loadAll(this.page);
  }

  toast(type: string, message: string): void {
    this.messageService.add({ severity: type, detail: message });
  }

  // public update(): void {
  //
  // }

  private loadAll(page: number): void {
    this.formUpdateNotification = this.structureCreateFormNotification();
    this.firmService.getFirm(--page).subscribe(data => {
      if (data.status !== 200) {
        this.toast(ToastEnumsConstant._FAIL, data.message);
      }
    });
  }

  private structureCreateFormNotification(): FormGroup {
    return this.formBuilder.group({

      publicNotificationImage: [1],
    });
  }
}
