import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KarmaSharedModule } from 'app/shared';
import {
  ApprovalStatusComponent,
  ApprovalStatusDetailComponent,
  ApprovalStatusUpdateComponent,
  ApprovalStatusDeletePopupComponent,
  ApprovalStatusDeleteDialogComponent,
  approvalStatusRoute,
  approvalStatusPopupRoute
} from './';

const ENTITY_STATES = [...approvalStatusRoute, ...approvalStatusPopupRoute];

@NgModule({
  imports: [KarmaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ApprovalStatusComponent,
    ApprovalStatusDetailComponent,
    ApprovalStatusUpdateComponent,
    ApprovalStatusDeleteDialogComponent,
    ApprovalStatusDeletePopupComponent
  ],
  entryComponents: [
    ApprovalStatusComponent,
    ApprovalStatusUpdateComponent,
    ApprovalStatusDeleteDialogComponent,
    ApprovalStatusDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KarmaApprovalStatusModule {}
