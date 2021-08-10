<template>
  <div class="content-container">
    <div class="columns">
      <div class="column is-8">
        <div class="section content-title-group">
          <h2 class="title">Equipments</h2>
          <button class="button refresh-button" @click="loadHeroes()">
            <i class="fas fa-sync"></i>
          </button>
          <ul>
            <li v-for="hero in heroes" :key="hero.id">
              <div class="card">
                <div class="card-content">
                  <div class="content">
                    <div :key="hero.id" class="name">
                      {{ hero.domain }}
                    </div>
                    <div class="description">Account: {{ hero.account }} User name: {{ hero.userName }}</div>
                  </div>
                </div>
                <footer class="card-footer">
                  <router-link
                    tag="button"
                    class="link card-footer-item"
                    :to="{ name: 'customer-details', params: { id: hero.id } }"
                  >
                    <i class="fas fa-check"></i>
                    <span>Select</span>
                  </router-link>
                </footer>
              </div>
            </li>
          </ul>
        </div>
        <div class="notification is-info" v-show="message">{{ message }}</div>
      </div>
    </div>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deleteHero"
    >
    </Modal>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Modal from '@/components/modal';

export default {
  name: 'Heroes',
  data() {
    return {
      heroToDelete: null,
      message: '',
      showModal: false,
    };
  },
  components: {
    Modal,
  },
  async created() {
    await this.loadHeroes();
  },
  methods: {
    ...mapActions(['getHeroesAction', 'deleteHeroAction']),
    askToDelete(hero) {
      this.heroToDelete = hero;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async deleteHero() {
      this.closeModal();
      if (this.heroToDelete) {
        await this.deleteHeroAction(this.heroToDelete);
      }
      await this.loadHeroes();
    },
    async loadHeroes() {
      this.message = 'getting the heroes, please be patient';
      await this.getHeroesAction();
      this.message = '';
    },
  },
  computed: {
    ...mapState(['heroes']),
    modalMessage() {
      const name =
        this.heroToDelete && this.heroToDelete.fullName
          ? this.heroToDelete.fullName
          : '';
      return `Would you like to delete ${name} ?`;
    },
  },
};
</script>
