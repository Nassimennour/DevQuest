import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersService } from '../../../services/users.service';
import { Ranking, User } from '../../../models/admin-models';
import { RankingService } from '../../../services/ranking.service';
import { Tooltip } from 'bootstrap';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css',
})
export class UserDetailsComponent implements OnInit {
  userId: string | null;
  userData: User = {
    id: 1,
    username: 'john_doe',
    email: 'john@gmail.com',
    fullName: 'John Doe',
    role: 'ADMIN',
    gender: 'MALE',
    bio: 'I am a software engineer',
    birthDate: '1990-01-01',
    profilePicture:
      'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.dribbble.com%2Fusers%2F5534%2Fscreenshots%2F14230133%2Fprofile_4x.jpg&f=1&nofb=1&ipt=6945238251e2748caa490eb3905703a5e19424685eec338a06b417d2e1f62116&ipo=images',
    isVerified: true,
    registrationDate: '2021-01-01',
    isDarkMode: false,
    skills: [
      {
        id: 1,
        name: 'Java',
        overview: 'Java is a high-level programming language.',
        logo: 'http://example.com/java-logo.png',
      },
      {
        id: 2,
        name: 'Spring Boot',
        overview: 'Spring Boot is an open source Java-based framework.',
        logo: 'http://example.com/spring-boot-logo.png',
      },
    ],
    quizzHistory: [
      {
        id: 1,
        score: 85.0,
        compltedAt: '2023-09-01T00:00:00Z',
        quizz: {
          id: 1,
          title: 'Java Basics',
          overview: 'Test your knowledge of Java basics.',
          difficulty: 'EASY',
          timesTaken: 10,
        },
      },
      {
        id: 2,
        score: 90.0,
        compltedAt: '2023-09-01T00:00:00Z',
        quizz: {
          id: 2,
          title: 'Java OOP',
          overview: 'Test your knowledge of Java OOP.',
          difficulty: 'MEDIUM',
          timesTaken: 5,
        },
      },
    ],
    userProgressList: [
      {
        id: 1,
        completedChallenges: 10,
        completedQuizzes: 5,
        progressPercentage: 0.5,
        technology: {
          id: 1,
          name: 'Java',
          overview: 'Java is a high-level programming language.',
        },
      },
      {
        id: 2,
        completedChallenges: 5,
        completedQuizzes: 2,
        progressPercentage: 0.2,
        technology: {
          id: 2,
          name: 'Spring Boot',
          overview: 'Spring Boot is an open source Java-based framework.',
        },
      },
    ],
    codingChallengeHistory: [
      {
        id: 1,
        title: 'Java Basics',
        description: 'Solve the Java Basics challenge.',
        difficulty: 'EASY',
      },
      {
        id: 2,
        title: 'Java OOP',
        description: 'Solve the Java OOP challenge.',
        difficulty: 'MEDIUM',
      },
    ],
  };
  userRanking: Ranking = {
    id: 1,
    score: 100,
    position: 5,
    level: 1,
  };
  constructor(
    private activatedRoute: ActivatedRoute,
    private usersService: UsersService,
    private rankingService: RankingService
  ) {
    this.userId = this.activatedRoute.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    // this.usersService.getUserById(Number(this.userId)).subscribe(
    //   (data) => {
    //     this.userData = data;
    //   },
    //   (error) => {
    //     console.error(error);
    //   }
    // );
    // Get user ranking
    // this.rankingService.getRankingByUserId(Number(this.userId)).subscribe(
    //   (ranking) => {
    //     this.userRanking = ranking;
    //     console.log(this.userRanking, 'userRanking#');
    //   },
    //   (error) => {
    //     console.error('Error fetching user ranking', error);
    //   }
    // );
    const toolTipTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="tooltip"]')
    );
    toolTipTriggerList.forEach(function (tooltipTriggerEl) {
      new Tooltip(tooltipTriggerEl);
    });
  }
}
