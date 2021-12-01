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
    this.formUpdateNotification = this.createFormNotification();
    this.notificationService.getNotification().subscribe(data => {
      this.notificationa = data.response;
      this.structureCreateFormNotification(data.response);
    });
  }

  private structureCreateFormNotification(response: any): void {
    this.formUpdateNotification.get('notification')?.setValue(response?.notification.value);
    this.formUpdateNotification.get('notificationImage')?.setValue(response?.notification_image.value);
    this.formUpdateNotification.get('publicNotification')?.setValue(response?.public_notification.value);
    this.formUpdateNotification.get('publicNotificationImage')?.setValue(response?.public_notification_image.value);
  }
  private createFormNotification(): FormGroup {
    return this.formBuilder.group({
      notification: "",
      notificationImage: "",
      publicNotification: "",
      publicNotificationImage: "",
    });
  }
}
