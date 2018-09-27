import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { LibraryBookModule } from './book/book.module';
import { LibraryAuthorModule } from './author/author.module';
import { LibraryPublisherModule } from './publisher/publisher.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        LibraryBookModule,
        LibraryAuthorModule,
        LibraryPublisherModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LibraryEntityModule {}
