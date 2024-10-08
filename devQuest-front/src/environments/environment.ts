export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',
  endpoints: {
    register: '/register',
    login: '/login',
    getProfileById: '/user/profile/',
    getMyProfile: '/user/my-profile',
    getAdminStats: '/admin/users/statistics',
    getQuizzCompletionStats: '/admin/quizzes/completion-stats',
    getTechnologyPopularity: '/admin/technologies/popular',
    getRecentActivities: '/admin/users/recent-activities',
    getUserActivity: '/admin/users/activity',
    getAllUsersAdmin: '/admin/users',
    deleteUserAdmin: '/admin/users/',
    updateUserAdmin: '/admin/users',
    getUserByIdAdmin: '/admin/users/',
    getRankingByUserIdAdmin: '/admin/rankings/user/',
    createUserAdmin: '/admin/users',
    // Technologies
    getAllTechnologiesAdmin: '/admin/technologies',
    updateTechnologyAdmin: '/admin/technologies',
    deleteTechnologyAdmin: '/admin/technologies/',
    createTechnologyAdmin: '/admin/technologies',
    getTechnologyByIdAdmin: '/admin/technologies/',
    // Categories
    getAllCategoriesAdmin: '/admin/categories',
    getCategoryByIdAdmin: '/admin/categories/',
    createCategoryAdmin: '/admin/categories',
    updateCategoryAdmin: '/admin/categories',
    deleteCategoryAdmin: '/admin/categories/',
    // Quizzes / Questions
    createQuizzAdmin: '/admin/quizzes',
    addQuestionAdmin: '/admin/questions',
    deleteQuestionAdmin: '/admin/questions/',
    updateQuestionAdmin: '/admin/questions',
    getAllQuizzesAdmin: '/admin/quizzes',
    getQuizzByIdAdmin: '/admin/quizzes/',
    getQuizzesByTechnologyIdAdmin: '/admin/quizzes/technology/',
    getQuestionsByQuizIdAdmin: '/admin/questions/quizz/',
    deleteQuizzAdmin: '/admin/quizzes/',
    updateQuizzAdmin: '/admin/quizzes',
    getScoresByQuizzIdAdmin: '/admin/scores/quizz/',
    // Coding Challenges
    getCodingChallengesAdmin: '/admin/coding-challenges',
    createCodingChallengeAdmin: '/admin/coding-challenges',
    updateCodingChallengeAdmin: '/admin/coding-challenges',
    deleteCodingChallengeAdmin: '/admin/coding-challenges/',
    getCodingChallengeByIdAdmin: '/admin/coding-challenges/',
    getChallengesByTechnologyIdAdmin: '/admin/coding-challenges/technology/',
    // Solutions
    getSolutionsByChallengeIdAdmin: '/admin/solutions/challenge/',
    deleteSolutionAdmin: '/admin/solutions/',
    getSolutionByIdAdmin: '/admin/solutions/',
    updateSolutionAdmin: '/admin/solutions',
    // Resources
    getResourcesByTechnologyIdAdmin: '/admin/resources/technology/',
    createResourceAdmin: '/admin/resources',
    deleteResourceAdmin: '/admin/resources/',
    updateResourceAdmin: '/admin/resources',
    getResourceByIdAdmin: '/admin/resources/',
    getAllResourcesAdmin: '/admin/resources',
  },
  userEndpoints: {
    createProfile: '/user/profile',
    getUserDashboard: '/user/dashboards/my-dashboard',
    getUserRanking: '/user/rankings/my-ranking',
    getAllRankingsUser: '/user/rankings',
    getMyProfile: '/user/my-profile',
    changePassword: '/user/change-password',
    updateProfile: '/user/profile',
    addSkill: '/user/profile/add-skill/',
    getAllTechnologies: '/user/technologies',
    getAllQuizzes: '/user/quizzes',
    getQuizzById: '/user/quizzes/',
    getQuestionsByQuizzId: '/user/questions/quizz/',
    getQuestionById: '/user/questions/',
    getScoresByQuizzId: '/user/scores/quizz/',
    getScoreById: '/user/scores/',
    submitQuizz: '/user/scores/quizz/',
    getAllCodingChallenges: '/user/coding-challenges',
    getCodingChallengeById: '/user/coding-challenges/',
    getSolutionsByCodingChallengeId: '/user/solutions/challenge/',
    getMySolutions: '/user/solutions/my-solutions',
    getSolutionById: '/user/solutions/',
    saveSolution: '/user/solutions',
    updateSolution: '/user/solutions',
    getMyScores: '/user/scores/my-scores',
  },
};
