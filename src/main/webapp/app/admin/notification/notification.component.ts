import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ToastEnumsConstant } from '../../config/ToastEnums.constant';
import { NotificationService } from './notification.service';

@Component({
  selector: 'jhi-user-profile',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss'],
  providers: [MessageService],
})
export class NotificationComponent implements OnInit {
  notificationa: any;
  formTest!: FormGroup;
  formUpdateNotification: any;
  dateFormat!: string;
  constructor(private messageService: MessageService, private formBuilder: FormBuilder, private notificationService: NotificationService) {}

  ngOnInit(): void {
    this.loadAll();
  }

  toast(type: string, message: string): void {
    this.messageService.add({ severity: type, detail: message });
  }

  public update(): void {
    this.notificationService.updateNotification(this.formUpdateNotification.value).subscribe(data => {
      if (data.status === 200) {
        this.toast(ToastEnumsConstant._200, data.message);
        this.loadAll();
      } else {
        this.toast(ToastEnumsConstant._FAIL, data.message);
      }
    });
  }

  private loadAll(): void {
    this.notificationService.getNotification().subscribe(data => {
      this.notificationa = data.response;
      this.formUpdateNotification = this.structureCreateFormNotification(data.response);
    });
  }

  private structureCreateFormNotification(response: any): FormGroup {
    return this.formBuilder.group({
      notification: [response?.notification.value],
      notificationImage: [response?.notification_image.value],
      publicNotification: [response?.public_notification.value],
      publicNotificationImage: [response?.public_notification_image.value],
    });
  }
}
