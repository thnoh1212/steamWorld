import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import '../main.js'
import { api_url } from '../main.js'
// import { SERVER_URL, LOCALHOST_URL } from '../main.js'
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
const LOCALHOST_URL = process.env.VUE_APP_LOCALHOST_URL;

Vue.use(Vuex)

export default new Vuex.Store({
    //data
    state: {
        userid: '',
        nickname: '',
        result: '',
        page: '',
        loginStatus: {
            token: localStorage.getItem("token"),
            userid: localStorage.getItem("userid"),
            nickname: localStorage.getItem("nickname"),
        }
        
    },
    getters: {
        getUserid: state => {
            return state.userid
        },
        getNickname: state => {
            return state.nickname
        },
        getResult: state => {
            return state.result
        },
        getPage: state => {
            return state.page
        }
    },
    mutations: {
        login: (state, payload) => {
            state.userid = payload.data.userid
            state.nickname = payload.data.nickname

            const userData = {
                userid: state.userid,
                nickname: state.nickname
            }
        },
        logout(state) {
            state.userid = ''
            state.nickname = ''
        },
        putE(state){
            state.result = state.result+'E'
            console.log(state.result)
        },
        putI(state){
            state.result = state.result+'I'
            console.log(state.result)
        },
        putN(state){
            state.result = state.result+'N'
            console.log(state.result)
        },
        putS(state){
            state.result = state.result+'S'
            console.log(state.result)
        },
        putT(state){
            state.result = state.result+'T'
            console.log(state.result)
        },
        putJ(state){
            state.result = state.result+'J'
            console.log(state.result)
        },
        putP(state){
            state.result = state.result+'P'
            console.log(state.result)
        },
        putF(state){
            state.result = state.result+'F'
            console.log(state.result)
        },
        slice(state,index){
            state.result=state.result.substring(0,index);
        },
        putPage(state, page){
            state.result = page;
            console.log(state.result);
        }

    },
    actions: {
        // login: (context, user) => {
        //     // return axios.post(api_url + `/a105/user/login`, user)
        //     return axios.post(`${SERVER_URL}/user/login`, user)
        //         .then(res => {
        //             console.log(SERVER_URL);
        //             localStorage.setItem('token', res.data['x-access-token']);
        //             localStorage.setItem('userid', res.data.data.userid);
        //             localStorage.setItem('nickname', res.data.data.nickname);
        //             window.location.reload();
                    
        //         })
        // },
        // logout: (context) => {
        //     context.commit('logout')
        //     localStorage.removeItem('token');
        //     localStorage.removeItem('userid');
        //     localStorage.removeItem('nickname');
        //     window.location.reload();
        // }
    }
})