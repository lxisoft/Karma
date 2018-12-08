import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  FeedComponent,
  FeedDetailComponent,
  FeedUpdateComponent,
  FeedDeletePopupComponent,
  FeedDeleteDialogComponent,
  feedRoute,
  feedPopupRoute
} from './';

const ENTITY_STATES = [...feedRoute, ...feedPopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [FeedComponent, FeedDetailComponent, FeedUpdateComponent, FeedDeleteDialogComponent, FeedDeletePopupComponent],
  entryComponents: [FeedComponent, FeedUpdateComponent, FeedDeleteDialogComponent, FeedDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaFeedModule {}
