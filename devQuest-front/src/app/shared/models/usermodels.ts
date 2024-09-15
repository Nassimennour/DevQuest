export interface RegistrationRequest {
  username: string;
  email: string;
  password: string;
  role: string;
}

export interface AuthenticationRequest {
  username: string;
  password: string;
}

export interface UserProfile {
  id: number;
  username: string;
  email: string;
  role: string;
  fullName: string;
  profilePicture: string;
  birthDate: string;
  gender: string;
  isVerified: boolean;
  registrationDate: string;
  isDarkMode: boolean;
  skills?: Technology[];
  quizzHistory?: QuizzHistory[];
  codingChallengeHistory?: CodingChallenge[];
  userProgressList?: UserProgress[];
}

export interface UserProgress {
  id?: number;
  user?: any;
  technology?: any;
  completedQuizzes?: number;
  completedCodingChallenges?: number;
  progressPercentage?: number;
  totalScore?: number;
  lastActivityDate?: string;
  lastQuizz?: any;
  lastCodingChallenge?: any;
}

export interface Dashboard {
  id?: number;
  user?: any;
  progressPercentage?: number;
  suggestedQuizzes?: Quizz[];
  suggestedCodingChallenges?: CodingChallenge[];
}

export interface Ranking {
  id?: number;
  user?: any;
  score?: number;
  position?: number;
  level?: number;
}

export interface QuizzHistory {
  id?: number;
  user?: any;
  quizz?: any;
  score?: number;
  compltedAt?: string;
}

export interface CodingChallenge {
  id?: number;
  title?: string;
  description?: string;
  creator?: any;
  difficulty?: string;
  technology?: any; // has the field 'name'
  duration?: number;
  timesTaken?: string;
  creationDate?: string;
}

export interface Technology {
  id?: number;
  name?: string;
  overview?: string;
  logo?: string;
  category?: any;
}

export interface ChangePasswordDTO {
  username?: string;
  currentPassword?: string;
  newPassword?: string;
}

export interface Quizz {
  id?: number;
  title?: string;
  overview?: string;
  difficulty?: string;
  questions?: Question[];
  technology?: Technology;
  duration?: number;
  creationDate?: string;
  timesTaken?: number;
  creator?: any;
  averageScore?: number;
}

export interface Score {
  id?: number;
  score?: number;
  user?: any;
  quizz?: any;
  answers?: AnswerDTO[];
}

export interface Answer {
  id?: number;
  questionId?: number;
  answer?: string;
  isCorrect?: boolean;
}

export interface AnswerDTO {
  id?: number;
  question?: Question;
  answer?: string;
  isCorrect?: boolean;
}

export interface Question {
  id?: number;
  question?: string;
  options?: string[];
  correctAnswer?: string;
  difficulty?: string;
}

export interface Solution {
  id?: number;
  code?: string;
  codingChallenge?: CodingChallenge;
  user?: any;
  isCorrect?: boolean;
  submissionDate?: string;
}

export interface SolutionDTO {
  code?: string;
  codingChallengeId?: number;
  userId?: number;
  isCorrect?: boolean;
  submissionDate?: string;
}

export interface UpdateSolutionDTO {
  id?: number;
  code?: string;
  isCorrect?: boolean;
  submissionDate?: string;
}
