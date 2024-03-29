<template>
  <div class="container" id="app">
    <SourceSelection @sourceChanged="sourceChanged"></SourceSelection>
    <NewsList v-bind:source="source"></NewsList>
  </div>
</template>

<script>
import SourceSelection from '@/components/SourceSelection'
import NewsList from '@/components/NewsList'

export default {
  name: 'home',
  components: {
    SourceSelection,
    NewsList
  },
  data () {
    return {
      source: null
    }
  },
  methods: {
    sourceChanged: function (source) {
      console.log('source changed:' + source)
      this.source = source
    }
  }
}
</script>

<style scoped>

</style>
