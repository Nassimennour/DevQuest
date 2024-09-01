export interface AdminStats {
  users: number;
  quizzes: number;
  codingChallenges: number;
  technologies: number;
}

export interface QuizzCompletionStats {
  period: string;
  count: number;
}

export interface technologyPopularity {
  name: string;
  popularityCount: number;
}

export interface Activity {
  type: string;
  description: string;
  optional: string;
  date: string;
}

export interface UserActivity {
  date: string;
  activityCount: number;
}

export interface User {
  id: number;
  username: string;
  email: string;
  fullName: string;
  role: string;
  gender: string;
  bio?: string;
  birthDate: string;
  profilePicture: string;
  isVerified: boolean;
  registrationDate: string;
  skills?: any[];
  quizzHistory?: any[];
  codingChallengeHistory?: any[];
  isDarkMode?: boolean;
  userProgressList?: any[];
}

export interface Ranking {
  id: number;
  score: number;
  user?: User;
  position: number;
  level?: number;
}

export interface QuizzDTO {
  id?: number;
  title?: string;
  overview?: string;
  difficulty?: string;
  duration?: number;
  creationDate?: string;
  technologyId?: number;
  creatorId?: number;
  timesTaken?: number;
  averageScore?: number;
  questions?: any[];
  technology?: any;
  creator?: User;
}

//export interface QUizz

export interface Question {
  id?: number;
  question?: string;
  options?: string[];
  correctAnswer?: string;
  difficulty?: string;
  quizzId?: number;
}

export interface Technology {
  id: number;
  name: string;
  overview: string;
  logo: string;
  category: any;
}

// Score model
export interface Score {
  id: number;
  score: number;
  user: any;
  quizz: any;
  answers: any[];
}

// codingChallenge DTO
export interface CodingChallengeDTO {
  title?: string;
  decsription?: string;
  difficulty?: string;
  creatorId?: number;
  creationDate?: string;
  technologyId?: number;
  duration?: number;
}
// Coding Challenge model
export interface CodingChallenge {
  id: number;
  title?: string;
  description?: string;
  difficulty?: string;
  creator?: any;
  creationDate?: string;
  technology?: any;
  timesTaken?: number;
}

export interface Solution {
  id?: number;
  code?: string;
  codingChallengeId?: any;
  user?: any;
  isCorrect?: boolean;
  submissionDate?: string;
}
