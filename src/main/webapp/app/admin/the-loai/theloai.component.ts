import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ToastEnumsConstant } from '../../config/ToastEnums.constant';
import { TheloaiService } from './theloai.service';

@Component({
  selector: 'jhi-firm-component',
  templateUrl: './theloai.component.html',
  styleUrls: ['./theloai.component.scss'],
  providers: [MessageService],
})
export class TheloaiComponent implements OnInit {
  displayAddDialog = false;
  firm: any;
  totalItems = 0;
  page = 1;
  formTest!: FormGroup;
  formUpdateNotification: any;
  dateFormat!: string;
  constructor(private messageService: MessageService, private formBuilder: FormBuilder, private theloaiService: TheloaiService) {}

  ngOnInit(): void {
    this.loadAll(this.page);
  }

  toast(type: string, message: string): void {
    this.messageService.add({ severity: type, detail: message });
  }

  public update(): void {
    this.theloaiService.updateCategory(this.formUpdateNotification.value).subscribe(data => {
      if (data.status === 200) {
        this.toast(ToastEnumsConstant._200, data.message);
        this.loadAll(this.page);
      } else {
        this.toast(ToastEnumsConstant._FAIL, data.message);
      }
    })
  }
  showDialog(): void {
    this.displayAddDialog = true;
  }

  private loadAll(page: number): void {
    this.formUpdateNotification = this.structureCreateFormNotification();
    this.theloaiService.getCategoryPaging(--page).subscribe(data => {
      if (data.status !== 200) {
        this.toast(ToastEnumsConstant._FAIL, data.message);
      }
    });
  }

  private structureCreateFormNotification(): FormGroup {
    return this.formBuilder.group({
      id: [5]
    });
  }


}
