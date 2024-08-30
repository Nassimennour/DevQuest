import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Technology } from '../../../models/admin-models';
import { TechnologyService } from '../../../services/technology.service';
import { QuizzService } from '../../../services/quizz.service';
import { AuthService } from '../../../../../shared/services/auth.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-quizz',
  templateUrl: './add-quizz.component.html',
  styleUrl: './add-quizz.component.css',
})
export class AddQuizzComponent implements OnInit {
  quizzForm!: FormGroup;
  technologyOptions: Technology[] = [];
  createdQuizzId = '';
  successMessage: string = '';
  errorMessage: string = '';
  questionsForm!: FormGroup;
  userId!: number;

  @ViewChild('questionsFormSection') questionsFormSection!: ElementRef;

  constructor(
    private technologyService: TechnologyService,
    private quizzService: QuizzService,
    private fb: FormBuilder,
    private authService: AuthService,
    private location: Location
  ) {
    this.initializeForm();
    this.questionsForm = this.fb.group({
      questions: this.fb.array([]),
    });
    this.addQuestion();
    this.authService.getUserProfile().subscribe((user) => {
      this.userId = user.id;
    });
  }

  initializeForm() {
    this.quizzForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      overview: new FormControl('', [Validators.required]),
      difficulty: new FormControl('EASY', [Validators.required]),
      duration: new FormControl('', [Validators.required]),
      durationUnit: new FormControl('minutes', [Validators.required]),
      technologyId: new FormControl('', [Validators.required]),
    });
  }

  get questions(): FormArray {
    return this.questionsForm.get('questions') as FormArray;
  }

  getOptions(index: number): FormArray {
    return this.questions.at(index).get('options') as FormArray;
  }

  addQuestion(): void {
    const questionGroup = this.fb.group({
      id: [null],
      question: ['', Validators.required],
      options: this.fb.array([this.createOption(), this.createOption()]),
      correctAnswer: ['', Validators.required],
      difficulty: ['EASY', Validators.required],
      editMode: 'CREATE',
    });
    this.questions.push(questionGroup);
  }

  onSaveQuestion(index: number): void {
    const questionData = this.questions.at(index).value;
    console.log('onSaveQuestion called :', questionData);
    if (questionData.editMode === 'EDIT') {
      const transformedOptions = questionData.options.map(
        (option: { option: string }) => option.option
      );
      questionData.options = transformedOptions;
      // CALL SERVICE TO UPDATE QUESTION
      this.quizzService.updateQuestion(questionData).subscribe(
        (res) => {
          console.log('Question updated successfully', res);
          this.questions.at(index).patchValue({ editMode: 'VIEW' });
          this.errorMessage = '';
          this.successMessage = 'Question updated successfully';
        },
        (error) => {
          console.error('Failed to update question', error);
          this.successMessage = '';
          this.errorMessage = 'Failed to update question';
        }
      );
    } else {
      // Add new question
      this.onAddQuestion();
    }
  }

  createOption(): FormGroup {
    return this.fb.group({
      option: ['', Validators.required],
    });
  }

  addOption(index: number): void {
    this.getOptions(index).push(this.createOption());
  }

  removeOption(questionIndex: number, optionIndex: number): void {
    this.getOptions(questionIndex).removeAt(optionIndex);
  }

  removeQuestion(index: number): void {
    // CALL SERVICE TO DELETE QUESTION
    const questionId = this.questions.at(index).value.id;
    console.log('Question ID:', questionId);
    if (questionId) {
      this.quizzService.deleteQuestion(questionId).subscribe(
        (res: any) => {
          this.questions.removeAt(index);
          this.errorMessage = '';
          this.successMessage = 'Question deleted successfully';
        },
        (error) => {
          console.error('Failed to delete question', error);
          this.successMessage = '';
          this.errorMessage = 'Failed to delete question';
        }
      );
    } else {
      this.questions.removeAt(index);
    }
  }

  onAddQuestion(): void {
    if (this.questionsForm.valid) {
      const questionsArray = this.questionsForm.get('questions') as FormArray;
      const lastQuestionIndex = questionsArray.length - 1;
      const lastQuestion = questionsArray.at(lastQuestionIndex).value;
      // Transform options array to an array of strings
      const transformedOptions = lastQuestion.options.map(
        (option: { option: string }) => option.option
      );
      const questionData = {
        ...lastQuestion,
        options: transformedOptions,
        quizzId: this.createdQuizzId,
        viewMode: 'CREATE', // Initially set to view mode
      };
      console.log('Question data:', questionData);
      this.quizzService.addQuestion(questionData).subscribe(
        (res: any) => {
          console.log('Question added successfully', res);
          this.addQuestion(); // add another question
          questionsArray
            .at(lastQuestionIndex)
            .patchValue({ editMode: 'VIEW', id: res.id });
          console.log(
            'Question after adding:',
            questionsArray.at(lastQuestionIndex).value
          );
        },
        (error) => {
          console.error('Failed to add question', error);
        }
      );
    }
  }

  getTechnologyOptions() {
    this.technologyService.getAllTechnologies().subscribe(
      (technologies) => {
        this.technologyOptions = technologies;
        console.log('Technology options:', this.technologyOptions);
      },
      (error) => {
        console.error('Error getting technology options...', error);
      }
    );
  }

  ngOnInit(): void {
    this.getTechnologyOptions();
  }

  // Submit the quizz details form
  onSubmit(event: Event): void {
    event.preventDefault();
    if (this.quizzForm.valid) {
      // Convert duration to minutes
      const duration = this.quizzForm.get('duration')?.value;
      const durationUnit = this.quizzForm.get('durationUnit')?.value;
      const durationInMinutes = this.convertToMinutes(duration, durationUnit);
      const quizzData = {
        ...this.quizzForm.value,
        duration: durationInMinutes,
        creatorId: this.userId,
        creationDate: new Date().toISOString(),
      };
      console.log('Quizz data:', quizzData);
      // call the service to add the quizz
      this.quizzService.createQuizz(quizzData).subscribe(
        (res: any) => {
          try {
            console.log('Create quizz response: ', res);
            this.createdQuizzId = res.id;
            this.errorMessage = '';
            this.successMessage = 'Quizz added successfully';
            this.scrollToQuestionsForm();
          } catch (e) {
            console.error('Failed to parse response:', e);
            this.successMessage = '';
            this.errorMessage = 'Failed to parse backend response';
          }
        },
        (error) => {
          console.error(error);
          this.successMessage = '';
          this.errorMessage = 'Failed to add quizz';
        }
      );
    }
  }

  // Convert duration to minutes
  convertToMinutes(duration: number, unit: string): number {
    switch (unit) {
      case 'seconds':
        return duration / 60;
      case 'hours':
        return duration * 60;
      default:
        return duration;
    }
  }

  // Scroll to the questions form section
  scrollToQuestionsForm() {
    this.questionsFormSection.nativeElement.scrollIntoView({
      behavior: 'smooth',
    });
  }

  onEditQuestion(index: number): void {
    const questionsArray = this.questionsForm.get('questions') as FormArray;
    questionsArray.at(index).patchValue({ editMode: 'EDIT' });
  }

  goBack() {
    this.location.back();
  }
}
