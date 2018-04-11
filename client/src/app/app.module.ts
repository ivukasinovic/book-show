import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppComponent} from './app.component';
import {FanpageComponent} from './fanpage/fanpage.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {UsedPropListComponent} from './fanpage/used-prop-list/used-prop-list.component';
import {NewPropListComponent} from './fanpage/new-prop-list/new-prop-list.component';
import {NewPropDetailComponent} from './fanpage/new-prop-detail/new-prop-detail.component';
import {UsedPropDetailComponent} from './fanpage/used-prop-detail/used-prop-detail.component';
import {PropService} from './fanpage/prop.service';
import {CreateAdComponent} from './fanpage/create-ad/create-ad.component';
import {BidListComponent} from './fanpage/bid-list/bid-list.component';
import {FormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {TokenInterceptor} from './token-interceptor';
import {NavbarComponent} from './navbar/navbar.component';
import {AuthGuardService} from './auth-guard.service';
import {AuthService} from './auth.service';
import {AdminFanProfileComponent} from './fanpage/admin-fan-profile/admin-fan-profile.component';
import {RoleGuardService} from './role-guard.service';
import { ShowsTheatreComponent } from './shows/shows-theatre/shows-theatre.component';
import { ShowsCinemaComponent } from './shows/shows-cinema/shows-cinema.component';
import { CinemaRepertoireComponent } from './shows/shows-cinema/cinema-repertoire/cinema-repertoire.component';
import { NewMovieComponent } from './shows/shows-cinema/cinema-repertoire/new-movie/new-movie.component';
import {MyAdsComponent} from './fanpage/my-ads/my-ads.component';
import {SharedService} from './fanpage/shared.service';
import { NewPropsConfigComponent } from './fanpage/admin-fan-profile/new-props-config/new-props-config.component';
import { UsedPropsConfigComponent } from './fanpage/admin-fan-profile/used-props-config/used-props-config.component';
import { CreateNewPropComponent } from './fanpage/admin-fan-profile/create-new-prop/create-new-prop.component';
import { EditNewPropComponent } from './fanpage/admin-fan-profile/edit-new-prop/edit-new-prop.component';
import { PersonalEditComponent } from './fanpage/admin-fan-profile/personal-edit/personal-edit.component';
import { ChangePasswordComponent } from './fanpage/admin-fan-profile/change-password/change-password.component';
import { EditMovieComponent } from './shows/shows-cinema/cinema-repertoire/edit-movie/edit-movie.component';
import { ReservationComponent } from './reservations/reservation/reservation.component';


@NgModule({
  declarations: [
    AppComponent,
    FanpageComponent,
    UsedPropListComponent,
    NewPropListComponent,
    NewPropDetailComponent,
    UsedPropDetailComponent,
    CreateAdComponent,
    BidListComponent,
    LoginComponent,
    NavbarComponent,
    AdminFanProfileComponent,
    ShowsTheatreComponent,
    ShowsCinemaComponent,
    CinemaRepertoireComponent,
    NewMovieComponent,
    MyAdsComponent,
    NewPropsConfigComponent,
    UsedPropsConfigComponent,
    CreateNewPropComponent,
    EditNewPropComponent,
    PersonalEditComponent,
    ChangePasswordComponent,
    EditMovieComponent,
    ReservationComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    PropService, {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthGuardService,
    AuthService,
    RoleGuardService,
    SharedService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
