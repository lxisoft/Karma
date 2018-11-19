import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISeverity } from 'app/shared/model/Karma/severity.model';
import { SeverityService } from './severity.service';

@Component({
  selector: 'jhi-severity-update',
  templateUrl: './severity-update.component.html'
})
export class SeverityUpdateComponent implements OnInit {
  severity: ISeverity;
  isSaving: boolean;

  constructor(private severityService: SeverityService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ severity }) => {
      this.severity = severity;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.severity.id !== undefined) {
      this.subscribeToSaveResponse(this.severityService.update(this.severity));
    } else {
      this.subscribeToSaveResponse(this.severityService.create(this.severity));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<ISeverity>>) {
    result.subscribe((res: HttpResponse<ISeverity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }
}
