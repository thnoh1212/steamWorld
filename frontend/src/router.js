import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from './components/Main.vue'
import Login from './components/Login.vue'
import FindPassword from './components/FindPassword.vue'
import ResetPassword from './components/ResetPassword.vue' 
import SignUp from './components/SignUp.vue'
import Recommend from './components/Recommend.vue'
import MyPage from './components/MyPage.vue'
import Survey from './components/Survey.vue'
import Result from './components/surveyResult/Result.vue'
import Q1 from './components/surveyQ/Q1.vue'
import Q2 from './components/surveyQ/Q2.vue'
import Q3 from './components/surveyQ/Q3.vue'
import Q4 from './components/surveyQ/Q4.vue'
import Q5 from './components/surveyQ/Q5.vue'
import Q6 from './components/surveyQ/Q6.vue'
import Q7 from './components/surveyQ/Q7.vue'
import Q8 from './components/surveyQ/Q8.vue'
import Q9 from './components/surveyQ/Q9.vue'
import Q10 from './components/surveyQ/Q10.vue'
import Q11 from './components/surveyQ/Q11.vue'
import Q12 from './components/surveyQ/Q12.vue'
import MyGame from './components/myPage/MyGame.vue'
import Detail from './components/Detail.vue'
import INTP from './components/surveyResult/INTP.vue'
import INTJ from './components/surveyResult/INTJ.vue'
import INFP from './components/surveyResult/INFP.vue'
import INFJ from './components/surveyResult/INFJ.vue'
import ISTP from './components/surveyResult/ISTP.vue'
import ISTJ from './components/surveyResult/ISTJ.vue'
import ISFP from './components/surveyResult/ISFP.vue'
import ISFJ from './components/surveyResult/ISFJ.vue'
import ENTP from './components/surveyResult/ENTP.vue'
import ENTJ from './components/surveyResult/ENTJ.vue'
import ENFP from './components/surveyResult/ENFP.vue'
import ENFJ from './components/surveyResult/ENFJ.vue'
import ESTP from './components/surveyResult/ESTP.vue'
import ESTJ from './components/surveyResult/ESTJ.vue'
import ESFP from './components/surveyResult/ESFP.vue'
import ESFJ from './components/surveyResult/ESFJ.vue'
import LikeGame from './components/myPage/LikeGame.vue'
import Popular from './components/recommend/Popular.vue'
import RecommendGame from './components/recommend/RecommendGame.vue'

Vue.use(VueRouter);


export const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: Main
        },
        {
            path: '/user/login',
            component: Login
        },
        {
            path: '/user/findpassword',
            component: FindPassword
        },
        {
            path: '/user/resetpassword',
            component: ResetPassword
        },
        {
            path: '/user/signup',
            component: SignUp
        },
        {
            path: '/survey',
            component: Survey
        },
        {
            path: '/recommend',
            component: Recommend
        },
        {
            path: '/mypage',
            component: MyPage
        },
        {
            path: '/result',
            component: Result
        },
        {
            path: '/q1',
            component: Q1
        },
        {
            path: '/q2',
            component: Q2
        },
        {
            path: '/q3',
            component: Q3
        },
        {
            path: '/q4',
            component: Q4
        },
        {
            path: '/q5',
            component: Q5
        },
        {
            path: '/q6',
            component: Q6
        },
        {
            path: '/q7',
            component: Q7
        },
        {
            path: '/q8',
            component: Q8
        },
        {
            path: '/q9',
            component: Q9
        },
        {
            path: '/q10',
            component: Q10
        },
        {
            path: '/q11',
            component: Q11
        },
        {
            path: '/q12',
            component: Q12
        },
        {
            path: '/result',
            component: Result
        },
        {
            path: 'mypage/mygame',
            component: MyGame

        },
        {
            path: '/detail',
            component: Detail
        },
        {
            path: '/result/INTP',
            component: INTP
        },
        {
            path: '/result/INTJ',
            component: INTJ
        },
        {
            path: '/result/INFJ',
            component: INFJ
        },
        {
            path: '/result/INFP',
            component: INFP
        },
        {
            path: '/result/ISTP',
            component: ISTP
        },
        {
            path: '/result/ISTJ',
            component: ISTJ
        },
        {
            path: '/result/ISFJ',
            component: ISFJ
        },
        {
            path: '/result/ISFP',
            component: ISFP
        },
        {
            path: '/result/ENTP',
            component: ENTP
        },
        {
            path: '/result/ENTJ',
            component: ENTJ
        },
        {
            path: '/result/ENFJ',
            component: ENFJ
        },
        {
            path: '/result/ENFP',
            component: ENFP
        },
        {
            path: '/result/ESTP',
            component: ESTP
        },
        {
            path: '/result/ESTJ',
            component: ESTJ
        },
        {
            path: '/result/ESFJ',
            component: ESFJ
        },
        {
            path: '/result/ESFP',
            component: ESFP
        },        
        {
            path: 'mypage/likeGame',
            component: LikeGame
        },
        {
            path: 'recommend/popular',
            component: Popular
        },
        {
            path: 'recommend/recommendGame',
            component: RecommendGame
        },
        
    ]
})  