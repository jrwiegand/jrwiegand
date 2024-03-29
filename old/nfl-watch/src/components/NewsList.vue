<template>
  <div class="newsList">
    <div class="container">
      <ul class="media-list">
        <li class="media" v-for="article in articles" v-bind:key="article.title">
          <div class="media-left">
            <a v-bind:href="article.url" target="_blank">
              <img class="media-object" v-bind:src="article.urlToImage">
            </a>
          </div>
          <div class="media-body">
            <h4 class="media-heading">
              <a v-bind:href="article.url" target="_blank">{{ article.title }}</a>
            </h4>
            <h5><i>by {{ article.author }}</i></h5>
            <p>{{ article.description }}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'newsList',
  data () {
    return {
      articles: []
    }
  },
  computed: {
    ...mapGetters({
      source: 'getSource'
    })
  },
  methods: {
    example: function () {
      if (this.source) {
        var key = '226135efd44c4df8a142d9eefff2377e'
        var url = 'https://newsapi.org/v2/top-headlines?sources=' + this.source.id + '&apiKey=' + key
        this.$http.get(url).then(response => {
          this.articles = response.data.articles
        })
      } else {
        this.articles = []
      }
    }
  },
  created: function () {
    this.example()
  },
  watch: {
    source () {
      this.example()
    }
  }
}
</script>

<style scoped>
  .media-object {
    width: 128px;
    padding: 10px;
  }
  .media {
    border-top: 1px solid lightgrey;
    padding-top: 20px;
  }
</style>
