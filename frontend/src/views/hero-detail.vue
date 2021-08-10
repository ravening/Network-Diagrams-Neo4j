<template>
  <div>
    <div class="section content-title-group">
      <h2 class="title">Customer Details</h2>
      <div class="card">
        <header class="card-header">
          <p class="card-header-title">{{ hero.domain}}</p>
        </header>
        <div class="card-content">
          <div class="content">
            <div class="field">
              <label class="label">id</label>
              <label class="input" readonly>{{ hero.id }}</label>
            </div>
            <div class="field">
              <label class="label">Account</label>
              <label class="input" readonly>{{ hero.account }}</label>
            </div>
            <div class="field">
              <label class="label">Username</label>
              <label class="input">{{ hero.userName }}</label>
            </div>
            <div class="field">
              <label class="label">Api Count</label>
              <label class="input">{{ hero.count }}</label>
            </div>
          </div>
          <div class="field">
            <label class="label">API's</label>
            <ul>
              <li v-for="api in hero.apiSet" :key="api.id">
                <div class="card">
                  <div class="card-content">
                    <div class="content">
                      <div :key="api.id" class="name">
                        {{ api.type }}
                      </div>
                      <div class="description">{{ api.description }}</div>
                    </div>
                  </div>
                  <footer class="card-footer">
                    <router-link
                        tag="button"
                        class="link card-footer-item"
                        :to="{ name: 'api-details', params: { id: api.id } }"
                    >
                      <i class="fas fa-check"></i>
                      <span>Select</span>
                    </router-link>
                  </footer>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <footer class="card-footer">
          <button
            class="link card-footer-item cancel-button"
            @click="cancelHero()"
          >
            <i class="fas fa-undo"></i>
            <span>Back</span>
          </button>
<!--          <button class="link card-footer-item" @click="saveHero()">-->
<!--            <i class="fas fa-save"></i>-->
<!--            <span>Save</span>-->
<!--          </button>-->
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'HeroDetail',
  props: {
    id: {
      type: String,
    },
  },
  data() {
    return {
      hero: {},
    };
  },
  created() {
    if (this.isAddMode) {
      this.hero = {
        id: undefined,
        firstName: '',
        lastName: '',
        description: '',
      };
    } else {
      this.hero = { ...this.getHeroById(this.id) };
    }
  },
  computed: {
    ...mapGetters(['getHeroById']),
    isAddMode() {
      return !this.id;
    },
  },
  methods: {
    ...mapActions(['updateHeroAction', 'addHeroAction']),
    cancelHero() {
      this.$router.push({ name: 'customers' });
    },
    async saveHero() {
      this.hero.id
        ? await this.updateHeroAction(this.hero)
        : await this.addHeroAction(this.hero);
      this.$router.push({ name: 'customers' });
    },
  },
};
</script>
