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
  BirthDate: string;
  gender: string;
  isVerified: boolean;
  registrationDate: string;
  isDarkMode: boolean;
}
