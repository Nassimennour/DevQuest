import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.css',
})
export class LandingComponent {
  @ViewChild('moreInfo') moreInfoSection!: ElementRef;

  scrollToMoreInfo() {
    this.moreInfoSection.nativeElement.scrollIntoView({ behavior: 'smooth' });
  }
}
