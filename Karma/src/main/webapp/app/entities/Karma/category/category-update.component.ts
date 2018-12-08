import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICategory } from 'app/shared/model/Karma/category.model';
import { CategoryService } from './category.service';
import { INeed } from 'app/shared/model/Karma/need.model';
import { NeedService } from 'app/entities/Karma/need';

@Component({
  selector: 'jhi-category-update',
  templateUrl: './category-update.component.html'
})
export class CategoryUpdateComponent implements OnInit {
  category: ICategory;
  isSaving: boolean;

  needs: INeed[];

  constructor(
    private jhiAlertService: JhiAlertService,
    private categoryService: CategoryService,
    private needService: NeedService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ category }) => {
      this.category = category;
    });
    this.needService.query().subscribe(
      (res: HttpResponse<INeed[]>) => {
        this.needs = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(this.category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(this.category));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<ICategory>>) {
    result.subscribe((res: HttpResponse<ICategory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }

  private onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackNeedById(index: number, item: INeed) {
    return item.id;
  }

  getSelected(selectedVals: Array<any>, option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
