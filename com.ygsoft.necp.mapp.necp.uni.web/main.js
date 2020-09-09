import App from './App'

import Vue from 'vue'

import store from './store'
Vue.prototype.$store = store

Vue.config.productionTip = false

App.mpType = 'app'

// 流程组件
import tflow from './node_modules/necp.tflow.mobile.components/src/index-mobile.js'
Vue.use(tflow)

import './src/assets/static/uni.css'
import './src/assets/css/xdeer.css'

const app = new Vue({
    ...App
})
app.$mount()
