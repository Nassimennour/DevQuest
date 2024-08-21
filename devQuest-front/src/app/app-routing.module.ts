import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './shared/components/login-page/login-page.component';
import { RegisterComponent } from './shared/components/register/register.component';
import { AdminDashboardComponent } from './features/admin/components/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './features/user/components/user-dashboard/user-dashboard.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginPageComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'admin',
    loadChildren: () => {
      return import('./features/admin/admin.module').then((m) => m.AdminModule);
    },
  },
  {
    path: 'user',
    loadChildren: () => {
      return import('./features/user/user.module').then((m) => m.UserModule);
    },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
