import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    source: null
  },
  getters: {
    getSource (state) {
      return state.source
    }
  },
  mutations: {
    SET_SOURCE (state, source) {
      state.source = source
    }
  },
  actions: {
    setSource (state, source) {
      store.commit('SET_SOURCE', source)
    }
  }
})

export default store
