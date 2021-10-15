import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { MessageService } from 'primeng/api';
import { ToastEnumsConstant } from '../../config/ToastEnums.constant';
import { Calendar } from 'primeng/calendar';

@Component({
  selector: 'jhi-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
  providers: [MessageService],
})
export class UserProfileComponent implements OnInit {
  profile: any;
  formTest!: FormGroup;
  formUpdateProfile: any;
  url!: SafeResourceUrl;
  dateFormat!: string;
  constructor(
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.loadAll();
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/XclpWJEeA2w?list=RDXclpWJEeA2w');
  }

  toast(type: string, message: string): void {
    this.messageService.add({ severity: type, detail: message });
  }

  public update(): void {
    this.userService.updateUserProfile(this.formUpdateProfile.value).subscribe(data => {
      if (data.status === 200) {
        this.toast(ToastEnumsConstant._200, data.message);
        this.loadAll();
      } else {
        this.toast(ToastEnumsConstant._FAIL, data.message);
      }
    });
  }

  private loadAll(): void {
    this.userService.getUserProfile().subscribe(data => {
      this.profile = data.response;
      this.formUpdateProfile = this.structureCreateFromProfile(data.response);
      this.setDataDatetime();
    });
  }

  private structureCreateFromProfile(profile: any): FormGroup {
    return this.formBuilder.group({
      id: [profile.id, Validators.required],
      firstName: [
        profile.firstName,
        [
          Validators.required,
          Validators.pattern(
            '^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9- ]+$'
          ),
          Validators.maxLength(32),
        ],
      ],
      lastName: [
        profile.lastName,
        [
          Validators.required,
          Validators.pattern(
            '^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9- ]+$'
          ),
          Validators.maxLength(32),
        ],
      ],
      login: [profile.login, Validators.required],
      imageUrl: profile.imageUrl,
      introduce: profile.introduce,
      birthday: profile.birthday,
      phoneNumber: profile.phoneNumber,
    });
  }
  private setDataDatetime(): void {
    const date = new Date(this.formUpdateProfile.value.birthday);
    this.formUpdateProfile.get('birthday').setValue(date);
  }
}
