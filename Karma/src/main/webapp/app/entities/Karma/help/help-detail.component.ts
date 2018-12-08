import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHelp } from 'app/shared/model/Karma/help.model';

@Component({
  selector: 'jhi-help-detail',
  templateUrl: './help-detail.component.html'
})
export class HelpDetailComponent implements OnInit {
  help: IHelp;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ help }) => {
      this.help = help;
    });
  }

  previousState() {
    window.history.back();
  }
}
