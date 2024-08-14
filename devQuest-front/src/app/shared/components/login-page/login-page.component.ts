import { Component } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css',
})
export class LoginPageComponent {
  togglePassword($event: MouseEvent) {
    $event.preventDefault();
    const passwordField = document.querySelector('#password');
    if (passwordField?.getAttribute('type') === 'password') {
      passwordField.setAttribute('type', 'text');
    } else {
      passwordField?.setAttribute('type', 'password');
    }
  }
}
