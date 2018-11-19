import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISeverity } from 'app/shared/model/Karma/severity.model';

@Component({
  selector: 'jhi-severity-detail',
  templateUrl: './severity-detail.component.html'
})
export class SeverityDetailComponent implements OnInit {
  severity: ISeverity;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ severity }) => {
      this.severity = severity;
    });
  }

  previousState() {
    window.history.back();
  }
}
