// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import vSelect from 'vue-select'
import axios from 'axios'
import VueAxios from 'vue-axios'
import app from './App'
import store from './store'
import router from './router'

Vue.config.productionTip = false
Vue.use(VueAxios, axios)
Vue.component('v-select', vSelect)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<app/>',
  components: { app }
})
