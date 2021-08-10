<template>
  <div class="content-container">
    <div class="columns">
      <div class="column is-8">
        <div class="section content-title-group">
          <h2 class="title">API's</h2>
          <button class="button refresh-button" @click="loadVillains()">
            <i class="fas fa-sync"></i>
          </button>
          <ul>
            <li v-for="villain in villains" :key="villain.id">
              <div class="card">
                <div class="card-content">
                  <div class="content">
                    <div :key="villain.id" class="name">
                      {{ villain.type}}
                    </div>
                    <div class="description">{{ villain.description }}</div>
                  </div>
                </div>
                <footer class="card-footer">
                  <router-link
                    tag="button"
                    class="link card-footer-item"
                    :to="{ name: 'api-details', params: { id: villain.id } }"
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
      @handleYes="deleteVillain"
    >
    </Modal>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Modal from '@/components/modal';

export default {
  name: 'Villains',
  data() {
    return {
      villainToDelete: null,
      message: '',
      showModal: false,
    };
  },
  components: {
    Modal,
  },
  async created() {
    await this.loadVillains();
  },
  methods: {
    ...mapActions(['getVillainsAction', 'deleteVillainAction']),
    askToDelete(villain) {
      this.villainToDelete = villain;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async deleteVillain() {
      this.closeModal();
      if (this.villainToDelete) {
        await this.deleteVillainAction(this.villainToDelete);
      }
      await this.loadVillains();
    },
    async loadVillains() {
      this.message = 'getting the villains, please be patient';
      await this.getVillainsAction();
      this.message = '';
    },
  },
  computed: {
    ...mapState(['villains']),
    modalMessage() {
      const name =
        this.villainToDelete && this.villainToDelete.fullName
          ? this.villainToDelete.fullName
          : '';
      return `Would you like to delete ${name} ?`;
    },
  },
};
</script>
