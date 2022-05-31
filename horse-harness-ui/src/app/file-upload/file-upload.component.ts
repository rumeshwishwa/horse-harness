import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Report} from 'notiflix';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  fileName: string;
  file: File;
  location = '';
  responses: any[] = [];
  isValid = true;
  insights: any;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getInsights();
  }

  getInsights(): void {
    this.http.get('/api/horse-harness-service/sectional-data/insights').subscribe(res => {
      this.insights = res;
    });
  }

  fileSelected(event: any): void {
    this.file = event.target.files[0];
    this.fileName = this.file.name;
    console.log(this.fileName);
  }

  submit(location: string): void {
    console.log(location);
    console.log(this.file);
    if (this.file) {
      this.location = location;
      const formData = new FormData();

      formData.append('file', this.file);
      formData.append('location', location);

      const upload$ = this.http.post('/api/horse-harness-service/sectional-data', formData);

      upload$.subscribe((res: any) => {
        console.log(res);
        const isInvalidRecords = res.filter(r => {
          return r.messages && r.messages.length > 0;
        });
        console.log(isInvalidRecords);
        this.isValid = isInvalidRecords.length === 0;
        this.responses = res;
        if (this.isValid) {
          Report.success('Success', 'file uploaded', 'Okay');
        } else {
          Report.failure('Error', 'failed to upload', 'Okay');
        }
      }, error => {
        console.log(error);
        Report.failure('Error', error.error, 'Okay');
      });
    }
  }

  clear(): void {
    this.file = undefined;
    this.location = '';
    this.responses = [];
    this.fileName = '';
    this.isValid = true;
  }
}
