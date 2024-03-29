<template>
  <div class="sourceSelection">
    <div class="jumbotron">
      <h2>
        <span class="glyphicon glyphicon-list-alt"></span> News List
      </h2>
      <v-select
        label="name"
        placeholder="Select News Source..."
        v-model="source"
        v-bind:options="options"
        v-bind:value="$store.state.source"
      ></v-select>
      <div v-if="source">
        <h6>{{ source.description }}</h6>
        <a v-bind:href="source.url" class="btn btn-primary" target="_blank">Go to {{ source.name }} Website</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'sourceSelection',
  data () {
    return {
      options: []
    }
  },
  created: function () {
    var key = '226135efd44c4df8a142d9eefff2377e'
    var url = 'https://newsapi.org/v2/sources?language=en&apiKey=' + key
    this.$http.get(url).then(response => {
      this.options = response.data.sources
    })
  },
  computed: {
    source: {
      get () {
        return this.$store.state.source
      },
      set (source) {
        this.$store.dispatch('setSource', source)
      }
    }
  }
}
</script>

<style scoped>

</style>
