<template>
  <div>
    <div class="section content-title-group">
      <h2 class="title">Api Details</h2>
      <div class="card">
        <header class="card-header">
          <p class="card-header-title">{{ villain.fullName }}</p>
        </header>
        <div class="card-content">
          <div class="content">
            <div class="field">
              <label class="label">Id</label>
              <label class="input" readonly>{{ villain.id }}</label>
            </div>
            <div class="field">
              <label class="label">Type</label>
              <label class="input" readonly>{{ villain.type }}</label>
            </div>
            <div class="field">
              <label class="label">Description</label>
              <label class="input" readonly>{{ villain.description }}</label>
            </div>
            <div class="field">
              <label class="label">Api Count</label>
              <label class="input" readonly>{{ villain.count }}</label>
            </div>
            <div class="field">
              <label class="label">State</label>
              <label class="input" readonly>{{ villain.state }}</label>
            </div>
            <div class="field">
              <label class="label">Date</label>
              <label class="input" readonly>{{ villain.created }}</label>
            </div>
            <div class="field">
              <label class="label">Level</label>
              <label class="input" readonly>{{ villain.level }}</label>
            </div>
            <div class="field">
              <label class="label">Domain</label>
              <label class="input" readonly>{{ villain.domain }}</label>
            </div>
            <div class="field">
              <label class="label">Domain Id</label>
              <label class="input" readonly>{{ villain.domainid }}</label>
            </div>
            <div class="field">
              <label class="label">Account</label>
              <label class="input" readonly>{{ villain.account }}</label>
            </div>
            <div class="field" v-show="villain.username">
              <label class="label">Username</label>
              <label class="input" readonly>{{ villain.username }}</label>
            </div>
            <div class="field" v-show="villain.eventid">
              <label class="label">Event Id</label>
              <label class="input" readonly>{{ villain.eventid }}</label>
            </div>
          </div>
        </div>
        <footer class="card-footer">
          <button
            class="link card-footer-item cancel-button"
            @click="cancelVillain()"
          >
            <i class="fas fa-undo"></i>
            <span>Back</span>
          </button>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'VillainDetail',
  props: {
    id: {
      type: String,
    },
  },
  data() {
    return {
      villain: {},
    };
  },
  async created() {
    console.log('in api details')
    if (this.isAddMode) {
      this.villain = {
        id: undefined,
        firstName: '',
        lastName: '',
        description: '',
      };
    } else {
      this.villain = {...this.getVillainById(this.id)};
      if (!Object.keys(this.villain).length) {
        console.log('villain is null..so fetching all villains')
        await this.loadVillains()
        console.log('did i fetch it?')
        this.villain = {...this.getVillainById(this.id)};
        if (!Object.keys(this.villain).length) {
          console.log('its stilllll null')
        }
      }
      console.log('api is ' + Object.values(this.villain))
    }
  },
  computed: {
    ...mapGetters(['getVillainById']),
    isAddMode() {
      return !this.id;
    },
  },
  methods: {
    ...mapActions(['getVillainsAction', 'updateVillainAction', 'addVillainAction']),
    cancelVillain() {
      this.$router.back();
    },
    async loadVillains() {
      this.message = 'getting the villains, please be patient';
      await this.getVillainsAction();
      this.message = '';
    },
    async saveVillain() {
      this.villain.id
        ? await this.updateVillainAction(this.villain)
        : await this.addVillainAction(this.villain);
      this.$router.push({ name: 'apis' });
    },
  },
};
</script>
