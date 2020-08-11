import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ScientificfieldsComponent } from './scientificfields/scientificfields.component';
import { FormsModule }   from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MagazineComponent } from './magazine/magazine.component';
import { LoginService } from './services/login.service';
import { Admin } from './IsLoggedIn/Admin';
import { Author } from './IsLoggedIn/Author';
import { Editor } from './IsLoggedIn/Editor';
import { Reviewer } from './IsLoggedIn/Reviewer';
import { Someone } from './IsLoggedIn/Someone';
import { TokenInterceptor } from './interceptors/interceptor';
import { ArticleComponent } from './article/article.component';
import { TasksComponent } from './tasks/tasks.component';
import { ReviewTextComponent } from './review-text/review-text.component';
import { ReviewPDFComponent } from './review-pdf/review-pdf.component';
import { CorrectingPdfComponent } from './correcting-pdf/correcting-pdf.component';
import { NumberOfReviewersComponent } from './number-of-reviewers/number-of-reviewers.component';
import { ChooseReviewersComponent } from './choose-reviewers/choose-reviewers.component';
import { ReviewersReviewsComponent } from './reviewers-reviews/reviewers-reviews.component';
import { EditingEditorComponent } from './editing-editor/editing-editor.component';
import { AuthorCorrectionComponent } from './author-correction/author-correction.component';
import { EditorReviewsCorrectionComponent } from './editor-reviews-correction/editor-reviews-correction.component';


const Routes = [
  {
    path: "registration",
    component: RegistrationComponent
  },
  {
    path: "scientificfields/:Id/:IdFields",
    component: ScientificfieldsComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "magazine/:Id",
    component: MagazineComponent
  },
  {
    path: "article/:IdProcess/:id",
    component: ArticleComponent
  },
  {
    path: 'tasks',
    component: TasksComponent
  },
  {
    path: 'reviewtext/:Id',
    component: ReviewTextComponent
  },
  {
    path: 'reviewpdf/:Id',
    component: ReviewPDFComponent
  },
  {
    path: 'correctingpdf/:Id',
    component: CorrectingPdfComponent
  },
  {
    path: 'numberofreviewers/:Id',
    component: NumberOfReviewersComponent
  },
  {
    path: 'choosereviewers/:Id/:num',
    component: ChooseReviewersComponent
  },
  {
    path: 'reviewersdoreviews/:Id',
    component: ReviewersReviewsComponent
  },
  {
    path: 'editingeditors/:Id',
    component: EditingEditorComponent
  },
  {
    path: 'editorreviewscorrection/:Id',
    component: EditorReviewsCorrectionComponent
  },
  {
    path: 'authorcorrection/:Id',
    component: AuthorCorrectionComponent
  },

]

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    ScientificfieldsComponent,
    LoginComponent,
    NavbarComponent,
    MagazineComponent,
    ArticleComponent,
    TasksComponent,
    ReviewTextComponent,
    ReviewPDFComponent,
    CorrectingPdfComponent,
    NumberOfReviewersComponent,
    ChooseReviewersComponent,
    ReviewersReviewsComponent,
    EditingEditorComponent,
    AuthorCorrectionComponent,
    EditorReviewsCorrectionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    RouterModule.forRoot(Routes),
    HttpClientModule, 
    HttpModule
  ],
  providers: [
    LoginService,
    Admin,
    Author,
    Editor,
    Reviewer,
    Someone,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: 'IsSomeLogged',
      useValue: () => {
        return true;
      } 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
